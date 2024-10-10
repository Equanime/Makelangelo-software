package com.marginallyclever.convenience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.javafaker.Faker;

public class TestLineInterpolator {
    public Faker faker;
    public LineInterpolator instance;
    public Point2D start, end;

    @BeforeEach
    public void setUp() {
        faker = new Faker();

        start = new Point2D(
            faker.number().randomDouble(5, 0, 5),
            faker.number().randomDouble(5, 0,5)
        );
        end = new Point2D(
            faker.number().randomDouble(5, 0, 5),
            faker.number().randomDouble(5, 0,5)
        );
        instance = new LineInterpolator(start, end);
    }

    @Test
    public void testGetPoint() {
        Point2D resultat = new Point2D();
        double t = faker.number().randomDouble(5, 0, 1);

        double expectedX = (end.x - start.x) * t + start.x;
        double expectedY = (end.y - start.y) * t + start.y;

        instance.getPoint(t, resultat);

        assertEquals(expectedX, resultat.x, 0.0001);
        assertEquals(expectedY, resultat.y, 0.0001);
    }

    @Test
    public void testGetTangent() {
        Point2D resultat = new Point2D();
        double t = faker.number().randomDouble(5, 0, 1);

        double expectedX = (end.x - start.x);
        double expectedY = (end.y - start.y);
        // Normalization
        double len = Math.sqrt(expectedX * expectedX + expectedY * expectedY);
        if (len != 0) {
            expectedX /= len;
            expectedY /= len;
        }

        instance.getTangent(t, resultat);

        assertEquals(expectedX, resultat.x, 0.0001);
        assertEquals(expectedY, resultat.y, 0.0001);
    }

    @Test
    public void testGetTangentWithTneg() {
        Point2D resultat = new Point2D();
        double t = -faker.number().randomDouble(5, 0, 1);

        double expectedX = (end.x - start.x);
        double expectedY = (end.y - start.y);
        // Normalization
        double len = Math.sqrt(expectedX * expectedX + expectedY * expectedY);
        if (len != 0) {
            expectedX /= len;
            expectedY /= len;
        }

        instance.getTangent(t, resultat);

        assertEquals(expectedX, resultat.x, 0.0001);
        assertEquals(expectedY, resultat.y, 0.0001);
    }

    @Test
    public void testGetTangentWithTtooBig() {
        Point2D resultat = new Point2D();
        double t = faker.number().randomDouble(5, 1, 4);

        double expectedX = (end.x - start.x);
        double expectedY = (end.y - start.y);
        // Normalization
        double len = Math.sqrt(expectedX * expectedX + expectedY * expectedY);
        if (len != 0) {
            expectedX /= len;
            expectedY /= len;
        }

        instance.getTangent(t, resultat);

        assertEquals(expectedX, resultat.x, 0.0001);
        assertEquals(expectedY, resultat.y, 0.0001);
    }

    @Test // Also tests getTangent
    public void testGetNormal() { 
        Point2D resultat = new Point2D();
        double t = faker.number().randomDouble(5, 0, 1);

        double expectedX = (end.y - start.y);
        double expectedY = -(end.x - start.x);
        // Normalization
        double len = Math.sqrt(expectedX * expectedX + expectedY * expectedY);
        if (len != 0) {
            expectedX /= len;
            expectedY /= len;
        }

        instance.getNormal(t, resultat);

        assertEquals(expectedX, resultat.x, 0.0001);
        assertEquals(expectedY, resultat.y, 0.0001);
    }
}
