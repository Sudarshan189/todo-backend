package com.sudarshan.todo.controller;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.dto.TodoDto;
import com.sudarshan.todo.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddTodo() throws Exception {
        String jsonRequestString="{\n" +
                "\"short_desc\":\"This is Short from Json\",\n" +
                "\"description\":\"This is description from Json\",\n" +
                "\"status\":\"COMPLETED\"\n" +
                "}";

        RequestBuilder builder = MockMvcRequestBuilders.post("/v1/todo/add")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequestString)
                .header("userId", "12345")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(mvcResult -> {
            Assertions.assertEquals(mvcResult.getResponse().getStatus(), 201);
        });
    }

    @Test
    public void testGetAllTodo() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/v1/todo/all/12345")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(mvcResult -> {
            Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
        });
    }

    @Test
    public void testUpdateTodoStatus() throws Exception {
        String jsonContent = "{\n" +
                "    \"status\":\"INCOMPLETE\"\n" +
                "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/todo/update/1")
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("user_id", "192jdnsjd");
        mockMvc.perform(requestBuilder).andExpect(mvcResult -> {
            Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        });
    }
}
