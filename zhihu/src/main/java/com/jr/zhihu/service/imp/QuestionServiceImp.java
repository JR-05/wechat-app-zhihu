package com.jr.zhihu.service.imp;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.dao.IQuestionDao;
import com.jr.zhihu.entity.QuestionEntity;
import com.jr.zhihu.service.IQuesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImp implements IQuesionService {
    @Autowired
    IQuestionDao questionDao;

    @Transactional
    public void addQuestion(QuestionEntity questionEntity) {
        questionDao.save(questionEntity);
    }

    @Override
    public void addQuestion(List<QuestionEntity> questionEntities) {
        questionDao.saveAll(questionEntities);
    }

    @Override
    public ResultBean<QuestionEntity> pagingFindAll(int offset) {
        Pageable pageable = new PageRequest(offset, 10);
        Page<QuestionEntity> Pages = questionDao.findAll(pageable);
        List<QuestionEntity> questionEntities = Pages.getContent();
        ResultBean<QuestionEntity> resultBean=new ResultBean(questionEntities);
        return resultBean;
    }
}
