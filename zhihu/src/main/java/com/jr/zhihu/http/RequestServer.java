package com.jr.zhihu.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jr.zhihu.entity.QuestionEntity;
import com.jr.zhihu.service.IQuesionService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RequestServer {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IQuesionService quesionService;

    private OkHttpClient okHttpClient = new OkHttpClient();
    private Logger logger = LoggerFactory.getLogger(RequestServer.class);
    private String url = new String("https://www.zhihu.com/api/v4/topics/19845181/feeds/top_activity?include=data%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Danswer)%5D.target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Danswer)%5D.target.is_normal%2Ccomment_count%2Cvoteup_count%2Ccontent%2Crelevant_info%2Cexcerpt.author.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Darticle)%5D.target.content%2Cvoteup_count%2Ccomment_count%2Cvoting%2Cauthor.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Dpeople)%5D.target.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Danswer)%5D.target.annotation_detail%2Ccontent%2Chermes_label%2Cis_labeled%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%3F(target.type%3Danswer)%5D.target.author.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Darticle)%5D.target.annotation_detail%2Ccontent%2Chermes_label%2Cis_labeled%2Cauthor.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dquestion)%5D.target.annotation_detail%2Ccomment_count%3B&offset=5&limit=10");
    private Integer count;//用于标识递归请求次数

    //定时爬去知乎数据
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void scheduledRequest() {
        count = 0;
        request(url.toString());
        logger.info("爬取数据成功!!!");
    }

    //网路请求数据
    public void request(String requestUri) {
        Request request = new Request.Builder()
                .url(requestUri)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);

        //异步处理数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析Json
                if (response.body().contentType().subtype().equals("json")) {
                    String nextUri = resolve(response.body().string());
                    //获取下一页数据
                    if (count < 15) {
                        request(nextUri);
                    }
                }
            }
        });
    }

    @Transactional
    public String resolve(String responseText) {
        JSONObject responseObject = JSON.parseObject(responseText);
        JSONArray dataArray = responseObject.getJSONArray("data");
        List<QuestionEntity> questionEntities = new ArrayList<>();
        if (!dataArray.isEmpty()) {
            for (int index = 0; index < dataArray.size(); index++) {
                JSONObject indexObject = dataArray.getJSONObject(index);
                JSONObject targetObject = indexObject.getJSONObject("target");

                String title = "";
                Long createdTime = System.currentTimeMillis();

                String content = targetObject.getString("content");
                Integer questionId = targetObject.getInteger("id");

                Integer commentCount = targetObject.getInteger("comment_count");
                Integer voteupCount = targetObject.getInteger("voteup_count");


                JSONObject authorObject = targetObject.getJSONObject("author");
                if (authorObject == null) {
                    continue;
                }
                String authorName = authorObject.getString("name");
                String authorImageUri = authorObject.getString("avatar_url");

                String type = targetObject.getString("type");
                if (type.equals("answer")) {
                    JSONObject questionObject = targetObject.getJSONObject("question");
                    title = questionObject.getString("title");
                    createdTime = targetObject.getLong("created_time");
                } else if (type.equals("article")) {
                    title = targetObject.getString("title");
                    createdTime = targetObject.getLong("created");
                }
                //封装数据
                QuestionEntity questionEntity = new QuestionEntity(questionId, title, content, authorName, authorImageUri, commentCount, voteupCount, new Date(createdTime * 1000));
                questionEntities.add(questionEntity);
            }
            //持久化对象
            quesionService.addQuestion(questionEntities);
        }
        //下页数据
        JSONObject pagingObject = responseObject.getJSONObject("paging");
        String nextUri = pagingObject.getString("next");
        count++;
        return nextUri;
    }
}
