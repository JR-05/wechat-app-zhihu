package com.jr.zhihu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IQuestionDaoTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void test() {
        Query nativeQuery = entityManager.createNativeQuery("create table domatic_Create_Table(name varchar(5))");
        nativeQuery.executeUpdate();
    }
}
