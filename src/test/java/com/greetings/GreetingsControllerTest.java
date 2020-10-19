package com.greetings;

import com.greetings.controller.GreetingsController;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsController.class)
public class GreetingsControllerTest {

    private static final String REQUEST_URL = "/api/greetings/test";
    private static final String USER_ID_PARAM = "Mario";
    @Autowired
    MockMvc mvc;
    @Autowired
    GreetingsController controller;

    @Test
    @SneakyThrows
    public void callRegardsController_shouldExecute() {
        mvc.perform(get(REQUEST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("accept", "application/jason")
                .content("{\"" + controller.getGreetings() + "\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    public void callRegardsControllerWithUserId_shouldExecute() {
        mvc.perform(get(REQUEST_URL + File.separator + USER_ID_PARAM)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("accept", "application/jason")
                .param("userId", USER_ID_PARAM)
                .content("{\"" + controller.getGreetingsWithUserId(USER_ID_PARAM) + " " + USER_ID_PARAM + "\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
