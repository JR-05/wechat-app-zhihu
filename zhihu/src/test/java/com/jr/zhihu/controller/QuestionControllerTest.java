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
public class QuestionControllerTest {
    @Autowired
    WebApplicationContext webApplicationContxt;

    MockMvc mockMvc;

    @Before
    public void init() {
//        mockMvc=MockMvcBuilders.standaloneSetup(QuestionController.class).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContxt).build();
    }

    @Test
    public void getQuestionByPadding() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/question/0/10");

        MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
