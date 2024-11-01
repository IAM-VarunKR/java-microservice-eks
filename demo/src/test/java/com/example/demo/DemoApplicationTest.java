package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoApplicationTest {

    @Test
    public void testHello() {
        DemoApplication app = new DemoApplication();
        String response = app.hello();
        assertEquals("Hello, World!", response);
    }
}
