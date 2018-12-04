package com.jr.zhihu.controller;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.entity.QuestionEntity;
import com.jr.zhihu.service.IQuesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @Autowired
    IQuesionService quesionService;

    @GetMapping(value = "/question/{offset}")
    public ResultBean<QuestionEntity> getQuestionByPadding(@PathVariable(required = true) Integer offset) {
        System.out.println("对象名"+this.getClass().getName()+"对象地址：" + this.hashCode() + "线程ID：" + Thread.currentThread().getId());
        return quesionService.pagingFindAll(offset);
    }
}
