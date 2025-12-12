package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.entity.Question;
import com.demo.entity.Response;
import com.demo.service.QuestionService;

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;

    @Test
    void getAllQuestionsTestSuccess() throws Exception {
        Question q = new Question();
        q.setId(1);
        q.setCategory("ComputerScience");

        Mockito.when(questionService.getAllQuestions())
               .thenReturn(ResponseEntity.ok(List.of(q)));

        mockMvc.perform(get("/question/allQuestions"))
               .andExpect(status().isOk());
    }

    @Test
    void getQuestionsByCategoryTestSuccess() throws Exception {
        Question q = new Question();
        q.setId(2);
        q.setCategory("Geography");

        Mockito.when(questionService.getQuestionsByCategory("Geography"))
               .thenReturn(ResponseEntity.ok(List.of(q)));

        mockMvc.perform(get("/question/category/{category}", "Geography"))
               .andExpect(status().isOk());
    }

    @Test
    void addQuestionTestSuccess() throws Exception {
        Question q = new Question();
        q.setId(3);
        q.setCategory("Math");

        Mockito.when(questionService.addQuestion(Mockito.any(Question.class)))
               .thenReturn(ResponseEntity.ok("Question added"));

        mockMvc.perform(post("/question/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"category\":\"Math\"}"))
               .andExpect(status().isOk());
    }

    @Test
    void getQuestionsForQuizTestSuccess() throws Exception {
        Mockito.when(questionService.getQuestionsForQuiz("Science", 2))
               .thenReturn(List.of(1, 2));

        mockMvc.perform(get("/question/generate")
                .param("categoryName", "Science")
                .param("numQuestions", "2"))
               .andExpect(status().isOk());
    }

    @Test
    void getQuestionsFromIdTestFailure() throws Exception {
        Mockito.when(questionService.getQuestionsFromId(List.of(1)))
               .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        mockMvc.perform(post("/question/getQuestions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1]"))
               .andExpect(status().isNotFound());
    }

    @Test
    void getScoreTestSuccess() throws Exception {
        Response r = new Response();
        r.setId(1);
        r.setResponse("Answer");

        Mockito.when(questionService.getScore(Mockito.anyList()))
               .thenReturn(ResponseEntity.ok(10));

        mockMvc.perform(post("/question/getScore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":1,\"response\":\"Answer\"}]"))
               .andExpect(status().isOk());
    }
}