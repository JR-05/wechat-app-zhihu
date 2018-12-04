package com.jr.zhihu.service;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.entity.UserEntity;

public interface IUserService {

    ResultBean<UserEntity> addUser(UserEntity userEntity);

    ResultBean<UserEntity> login(String userName, Integer password);

    ResultBean<Boolean> isExist(String userName);
}
