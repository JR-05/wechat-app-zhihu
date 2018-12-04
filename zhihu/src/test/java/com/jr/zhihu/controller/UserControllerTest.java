package com.jr.zhihu.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registerTest() throws Exception {
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("http://localhost:8080/user/register");
        postRequest.param("userName", "JR1997");
        postRequest.param("password", "12");
        postRequest.param("brithDay", "1997-06-12");
        postRequest.contentType("multipart/form-data; charset=utf-8");
        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void loginTest() throws Exception {
        MockHttpServletRequestBuilder getReqeust = MockMvcRequestBuilders.get("http://localhost:8080/user/login");
        getReqeust.param("userName", "JR1997");
        getReqeust.param("password", "123456");
        getReqeust.contentType("multipart/form-data; charset=utf-8");
        MvcResult mvcResult = mockMvc.perform(getReqeust)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}
