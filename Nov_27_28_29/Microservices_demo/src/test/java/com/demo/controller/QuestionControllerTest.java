package com.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.entity.Question;
import com.demo.entity.QuestionWrapper;
import com.demo.service.QuestionService;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private Environment environment;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions() throws Exception {
        Question q = new Question();
        q.setId(1);
        when(questionService.getAllQuestions()).thenReturn(
                new org.springframework.http.ResponseEntity<>(List.of(q), org.springframework.http.HttpStatus.OK)
        );

        mockMvc.perform(get("/question/allQuestions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetQuestionsByCategory() throws Exception {
        Question q = new Question();
        q.setId(2);

        when(questionService.getQuestionsByCategory("Java")).thenReturn(
                new org.springframework.http.ResponseEntity<>(List.of(q), org.springframework.http.HttpStatus.OK)
        );

        mockMvc.perform(get("/question/category/Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2));
    }

    @Test
    void testAddQuestion() throws Exception {
        Question q = new Question();
        q.setId(3);

        when(questionService.addQuestion(any(Question.class))).thenReturn(
                new org.springframework.http.ResponseEntity<>("success", org.springframework.http.HttpStatus.CREATED)
        );

        mockMvc.perform(post("/question/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionTitle\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("success"));
    }

    @Test
    void testGetQuestionsForQuiz() throws Exception {
        when(questionService.getQuestionsForQuiz("Java", 2)).thenReturn(List.of(10, 20));

        mockMvc.perform(get("/question/generate")
                        .param("categoryName", "Java")
                        .param("numQuestions", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(10));
    }

    @Test
    void testGetQuestionsFromId() throws Exception {
        QuestionWrapper w = new QuestionWrapper(1, "Q1", "A", "B", "C", "D");

        when(questionService.getQuestionsFromId(anyList())).thenReturn(
                new org.springframework.http.ResponseEntity<>(List.of(w), org.springframework.http.HttpStatus.OK)
        );

        mockMvc.perform(post("/question/getQuestions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetScore() throws Exception {
        when(questionService.getScore(anyList())).thenReturn(
                new org.springframework.http.ResponseEntity<>(1, org.springframework.http.HttpStatus.OK)
        );

        mockMvc.perform(post("/question/getScore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"response\":\"A\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
