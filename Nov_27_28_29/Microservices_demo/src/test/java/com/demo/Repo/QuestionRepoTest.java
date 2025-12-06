package com.demo.Repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.demo.entity.Question;

@DataJpaTest
class QuestionRepoTest {

    @Autowired
    private QuestionRepo questionRepo;

    @BeforeEach
    void setup() {
        Question q1 = new Question();
        q1.setQuestionTitle("Q1");
        q1.setCategory("Java");
        q1.setOption1("A");
        q1.setOption2("B");
        q1.setOption3("C");
        q1.setOption4("D");
        q1.setRightAnswer("A");
        q1.setDifficultylevel("Easy");

        Question q2 = new Question();
        q2.setQuestionTitle("Q2");
        q2.setCategory("Java");
        q2.setOption1("A1");
        q2.setOption2("B1");
        q2.setOption3("C1");
        q2.setOption4("D1");
        q2.setRightAnswer("A1");
        q2.setDifficultylevel("Medium");

        Question q3 = new Question();
        q3.setQuestionTitle("Q3");
        q3.setCategory("Python");
        q3.setOption1("X");
        q3.setOption2("Y");
        q3.setOption3("Z");
        q3.setOption4("W");
        q3.setRightAnswer("X");
        q3.setDifficultylevel("Hard");

        questionRepo.save(q1);
        questionRepo.save(q2);
        questionRepo.save(q3);
    }
    
 @Test
    void testFindByCategory() {
        List<Question> javaQuestions = questionRepo.findByCategory("Java");
        assertEquals(2, javaQuestions.size());
    }

    @Test
    void testFindRandomQuestionsByCategory() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        List<Question> randomList =
                questionRepo.findRandomQuestionsByCategory("Java", pageRequest);

        assertEquals(1, randomList.size());
        assertEquals("Java", randomList.get(0).getCategory());
    }
}
