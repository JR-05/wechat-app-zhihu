package com.jr.zhihu.dao;

import com.jr.zhihu.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionDao extends JpaRepository<QuestionEntity, Integer> {

}



