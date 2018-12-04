package com.jr.zhihu.dao;

import com.jr.zhihu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntitiesByUserNameAndPassword(String userName, Integer password);

    boolean existsByUserName(String userName);
}
