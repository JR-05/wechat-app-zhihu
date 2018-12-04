package com.jr.zhihu.configure;

import com.jr.zhihu.interceptor.RegisterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigure implements WebMvcConfigurer {
    @Bean
    RegisterInterceptor localInterceptor() {
        return new RegisterInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        RegisterInterceptor registerInterceptor = localInterceptor();
        registry.addInterceptor(registerInterceptor).addPathPatterns("/user/register");
    }
}
