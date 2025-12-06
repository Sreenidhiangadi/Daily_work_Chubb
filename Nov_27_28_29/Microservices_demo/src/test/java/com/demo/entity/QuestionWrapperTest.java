package com.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionWrapperTest {

    @Test
    void testParameterizedConstructor() {
        QuestionWrapper wrapper = new QuestionWrapper(
                1,
                "What is Java?",
                "Programming Language",
                "OS",
                "Database",
                "Browser"
        );

        assertEquals(1, wrapper.getId());
        assertEquals("What is Java?", wrapper.getQuestionTitle());
        assertEquals("Programming Language", wrapper.getOption1());
        assertEquals("OS", wrapper.getOption2());
        assertEquals("Database", wrapper.getOption3());
        assertEquals("Browser", wrapper.getOption4());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        QuestionWrapper wrapper = new QuestionWrapper();

        wrapper.setId(2);
        wrapper.setQuestionTitle("What is Python?");
        wrapper.setOption1("Programming Language");
        wrapper.setOption2("Snake");
        wrapper.setOption3("Game");
        wrapper.setOption4("Framework");

        assertEquals(2, wrapper.getId());
        assertEquals("What is Python?", wrapper.getQuestionTitle());
        assertEquals("Programming Language", wrapper.getOption1());
        assertEquals("Snake", wrapper.getOption2());
        assertEquals("Game", wrapper.getOption3());
        assertEquals("Framework", wrapper.getOption4());
    }

    @Test
    void testEqualsAndHashCode() {
        QuestionWrapper q1 = new QuestionWrapper(1, "Q1", "A", "B", "C", "D");
        QuestionWrapper q2 = new QuestionWrapper(1, "Q1", "A", "B", "C", "D");

        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testToString() {
        QuestionWrapper wrapper = new QuestionWrapper(
                10, "Sample", "1", "2", "3", "4"
        );

        String str = wrapper.toString();

        assertTrue(str.contains("id=10"));
        assertTrue(str.contains("questionTitle=Sample"));
        assertTrue(str.contains("option1=1"));
    }
}
