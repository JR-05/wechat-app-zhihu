package com.jr.zhihu.service;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.entity.QuestionEntity;

import java.util.List;

public interface IQuesionService {
    void addQuestion(QuestionEntity questionEntity);

    void addQuestion(List<QuestionEntity> questionEntities);

    ResultBean<QuestionEntity> pagingFindAll(int offset);
}
