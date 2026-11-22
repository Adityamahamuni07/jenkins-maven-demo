package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testAddition() {
        App app = new App();

        assertEquals(10, app.add(5, 5));
    }
}
