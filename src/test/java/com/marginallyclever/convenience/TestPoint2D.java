package com.marginallyclever.convenience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.javafaker.Faker;

public class TestPoint2D {
    Faker faker;
    Point2D instance;
    double fakeX, fakeY;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        fakeX = faker.number().randomDouble(5, 0, 5);
        fakeY = faker.number().randomDouble(5, 0, 5);
    }

    @Test
    public void testSet() {
        instance = new Point2D();
        
        instance.set(fakeX, fakeY);

        assertEquals(fakeX, instance.x, 0.0001);
        assertEquals(fakeY, instance.y, 0.0001);
    }

    @Test
    public void testCopyConstructor() {
        Point2D p = new Point2D(fakeX, fakeY);

        instance = new Point2D(p);

        assertEquals(p.x, instance.x, 0.0001);
        assertEquals(p.y, instance.y, 0.0001);
    }

    @Test
    public void testNormalize() {
        instance = new Point2D(fakeX, fakeY);
        double len = Math.sqrt(fakeX * fakeX + fakeY * fakeY);
        double ilen = 1.0 / len;
        double expectedX = fakeX * ilen;
        double expectedY = fakeY * ilen;

        instance.normalize();

        assertEquals(expectedX, instance.x, 0.0001);
        assertEquals(expectedY, instance.y, 0.0001);
    }

    @Test
    public void testLength() {
        instance = new Point2D(fakeX, fakeY);
        double expected = Math.sqrt(fakeX * fakeX + fakeY * fakeY);

        double result = instance.length();

        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testLengthSquared() {
        instance = new Point2D(fakeX, fakeY);
        double expected = fakeX * fakeX + fakeY * fakeY;

        double result = instance.lengthSquared();

        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDistance() {
        instance = new Point2D(fakeX, fakeY);
        Point2D p = new Point2D(faker.number().randomDouble(5, 0, 5), faker.number().randomDouble(5, 0, 5));
        double dx = fakeX - p.x;
        double dy = fakeY - p.y;
        double expected = Math.sqrt(dx * dx + dy * dy);

        double result = instance.distance(p);

        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDistanceSquared() {
        instance = new Point2D(fakeX, fakeY);
        Point2D p = new Point2D(faker.number().randomDouble(5, 0, 5), faker.number().randomDouble(5, 0, 5));
        double dx = fakeX - p.x;
        double dy = fakeY - p.y;
        double expected = dx * dx + dy * dy;

        double result = instance.distanceSquared(p);

        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testEqualsEpsilon() {
        instance = new Point2D(fakeX, fakeY);
        Point2D p = new Point2D(faker.number().randomDouble(5, 0, 5), faker.number().randomDouble(5, 0, 5));
        double epsilon = faker.number().randomDouble(5, 0, 5);
        double dx = fakeX - p.x;
        double dy = fakeY - p.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        boolean expected = distance < epsilon;

        boolean result = instance.equalsEpsilon(p, epsilon);

        assertEquals(expected, result);
    }

    @Test
    public void testScale() {
        instance = new Point2D(fakeX, fakeY);
        double scale = faker.number().randomDouble(5, 0, 5);
        double expectedX = fakeX * scale;
        double expectedY = fakeY * scale;

        instance.scale(scale);

        assertEquals(expectedX, instance.x, 0.0001);
        assertEquals(expectedY, instance.y, 0.0001);
    }

    @Test
    public void testSetPoint2D() {
        instance = new Point2D(fakeX, fakeY);
        Point2D p = new Point2D(faker.number().randomDouble(5, 0, 5), faker.number().randomDouble(5, 0, 5));

        instance.set(p);

        assertEquals(p.x, instance.x, 0.0001);
        assertEquals(p.y, instance.y, 0.0001);
    }

    @Test
    public void testSetDoubleDouble() {
        instance = new Point2D();

        instance.set(fakeX, fakeY);

        assertEquals(fakeX, instance.x, 0.0001);
        assertEquals(fakeY, instance.y, 0.0001);
    }

    @Test
    public void testAdd() {
        instance = new Point2D(fakeX, fakeY);
        Point2D p = new Point2D(faker.number().randomDouble(5, 0, 5), faker.number().randomDouble(5, 0, 5));
        double expectedX = fakeX + p.x;
        double expectedY = fakeY + p.y;

        instance.add(p);

        assertEquals(expectedX, instance.x, 0.0001);
        assertEquals(expectedY, instance.y, 0.0001);
    }
}
