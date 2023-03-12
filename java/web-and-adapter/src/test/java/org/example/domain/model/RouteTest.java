package org.example.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RouteTest {
    @Test
    void isValid_SameLocation() {
        Route route = new Route("New York", "New York");
        assertFalse(route.isValid());
    }

    @Test
    void isValid_DifferentLocation() {
        Route route = new Route("New York", "Los Angeles");
        assertTrue(route.isValid());
    }

    @Test
    void isValid_NullLocation() {
        Route route = new Route("New York", null);
        assertFalse(route.isValid());
    }
}
