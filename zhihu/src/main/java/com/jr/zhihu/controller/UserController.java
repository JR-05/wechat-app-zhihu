package com.jr.zhihu.controller;

import com.jr.zhihu.bean.ResultBean;
import com.jr.zhihu.entity.UserEntity;
import com.jr.zhihu.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserServiceImp userService;

    /**
     * 保存小程序发送的图片和form表单注册信息
     *
     * @param request
     * @return
     */
    @PostMapping("/user/register")
    public ResultBean register(MultipartHttpServletRequest request, @Validated UserEntity user, BindingResult result) throws IOException {
        //数据校验
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                String errorMessge = "";
                if (fieldError.getField().equals("brithDay")) {
                    errorMessge = "请输入正确的时间格式";
                } else {
                    errorMessge = fieldError.getDefaultMessage();
                }
                throw new ValidationException(errorMessge);
            }
        }
        //保存头像图片
        MultipartFile multipartFile = request.getFile("file");
        String contextPath = request.getSession().getServletContext().getRealPath("/") + "/Favicon";
        File file = new File(contextPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String imageName = user.getUserName() + "-" + System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        File savePath = new File(contextPath, imageName);
        multipartFile.transferTo(savePath);
        //保存用户信息
        user.setImagePath("http://localhost:8080/Favicon/"+imageName);
        return userService.addUser(user);
    }

    @GetMapping(value = "/user/login")
    public ResultBean login(@RequestParam(required = true) String userName, @RequestParam(required = true) Integer password) {
        return userService.login(userName, password);
    }
}
