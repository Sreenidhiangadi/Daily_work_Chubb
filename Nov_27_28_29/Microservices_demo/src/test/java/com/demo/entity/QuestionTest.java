package com.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void testGetterSetter() {
        Question question = new Question();

        question.setId(1);
        question.setQuestionTitle("What is Java?");
        question.setOption1("Programming Language");
        question.setOption2("OS");
        question.setOption3("Database");
        question.setOption4("Browser");
        question.setRightAnswer("Programming Language");
        question.setDifficultylevel("Easy");
        question.setCategory("Technology");

        assertEquals(1, question.getId());
        assertEquals("What is Java?", question.getQuestionTitle());
        assertEquals("Programming Language", question.getOption1());
        assertEquals("OS", question.getOption2());
        assertEquals("Database", question.getOption3());
        assertEquals("Browser", question.getOption4());
        assertEquals("Programming Language", question.getRightAnswer());
        assertEquals("Easy", question.getDifficultylevel());
        assertEquals("Technology", question.getCategory());
    }

    @Test
    void testEqualsAndHashCode() {
        Question q1 = new Question();
        q1.setId(1);
        q1.setQuestionTitle("Sample Question");

        Question q2 = new Question();
        q2.setId(1);
        q2.setQuestionTitle("Sample Question");

        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testToString() {
        Question question = new Question();
        question.setId(10);
        question.setQuestionTitle("Example");

        String result = question.toString();

        assertTrue(result.contains("id=10"));
        assertTrue(result.contains("questionTitle=Example"));
    }
}
