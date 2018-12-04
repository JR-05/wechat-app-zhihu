package com.jr.zhihu.dao;

import com.jr.zhihu.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserDaoTest {

    @Autowired
    IUserDao userDao;

    @Test
    public void existsByUserName() {
        boolean isExists = userDao.existsByUserName("JR1997");
        System.out.println("================================================");
        System.out.println(isExists);
    }

    @Test
    public void findUserEntitiesByUserNameAndPassword() {
        UserEntity user = userDao.findUserEntitiesByUserNameAndPassword("JR1997", 123456);
        System.out.println("================================================");
        System.out.println(user);
    }
}
