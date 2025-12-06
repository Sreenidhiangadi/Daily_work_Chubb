package com.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResponseTest {

    @Test
    void testGettersAndSetters() {
        Response resp = new Response();

        resp.setId(1);
        resp.setResponse("Correct");

        assertEquals(1, resp.getId());
        assertEquals("Correct", resp.getResponse());
    }

    @Test
    void testEqualsAndHashCode() {
        Response r1 = new Response();
        r1.setId(1);
        r1.setResponse("Yes");

        Response r2 = new Response();
        r2.setId(1);
        r2.setResponse("Yes");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        Response resp = new Response();
        resp.setId(5);
        resp.setResponse("Answer");

        String str = resp.toString();

        assertTrue(str.contains("id=5"));
        assertTrue(str.contains("response=Answer"));
    }
}
