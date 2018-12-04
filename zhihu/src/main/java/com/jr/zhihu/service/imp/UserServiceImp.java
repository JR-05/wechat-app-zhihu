package com.jr.zhihu.service.imp;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.dao.IUserDao;
import com.jr.zhihu.entity.UserEntity;
import com.jr.zhihu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    IUserDao userDao;

    public ResultBean<UserEntity> addUser(UserEntity user) {
        ResultBean<UserEntity> resultBean = new ResultBean(userDao.save(user));
        return resultBean;
    }

    @Override
    public ResultBean<UserEntity> login(String userName, Integer password) {
        UserEntity user = userDao.findUserEntitiesByUserNameAndPassword(userName, password);
        ResultBean<UserEntity> resultBean;
        if (user == null) {
            resultBean = new ResultBean(ResultBean.FATL, "登陆失败", null);
        } else {
            resultBean = new ResultBean(ResultBean.SUCCESS, "登陆成功", user);
        }
        return resultBean;
    }

    @Override
    public ResultBean<Boolean> isExist(String userName) {
        ResultBean<Boolean> resultBean;
        if (userDao.existsByUserName(userName)) {
            resultBean = new ResultBean(ResultBean.USER_IS_EXIST, "用户已存在", true);
        } else {
            resultBean = new ResultBean(ResultBean.SUCCESS, "用户不存在", false);
        }
        return resultBean;
    }
}
