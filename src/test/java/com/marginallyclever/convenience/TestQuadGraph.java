package com.marginallyclever.convenience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.javafaker.Faker;

public class TestQuadGraph {
    Faker faker;
    QuadGraph instance;
    double x, y, x2, y2;
    int ix, iy, ix2, iy2;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        x = faker.number().randomDouble(5, 0, 5);
        y = faker.number().randomDouble(5, 0, 5);
        x2 = faker.number().randomDouble(5, 6, 10);
        y2 = faker.number().randomDouble(5, 6, 10);
        
        ix = (int) x+1; //+1 pour être sûr qu'on est dans le rectangle
        iy = (int) y+1;
        ix2 = (int) x2-1; //-1 pour être sûr qu'on est dans le rectangle
        iy2 = (int) y2-1;

        instance = new QuadGraph(x, y, x2, y2);
    }

    @Test
    public void testSplit() {
        instance.split();

        assertNotNull(instance.children);
        assertEquals(0, instance.sites.size());
    }

    @Test
    public void testInsert() {
        Point2D e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));

        boolean result = instance.insert(e);

        assertTrue(result);
    }

    @Test
    public void testAddCellToOneQuadrant() {
        Point2D e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        instance.split();

        boolean result = instance.insert(e);

        assertTrue(result);
    }

    @Test
    public void testSearch() {
        Point2D p = new Point2D(
                faker.number().randomDouble(5, 5, 10),
                faker.number().randomDouble(5, 5, 10));

        Point2D result = instance.search(p);

        assertNull(result);
    }

    @Test
    public void testSearch3() {
        Point2D p = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));

        Point2D e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));

        instance.insert(e);

        Point2D result = instance.search(p);

        // Là on laisse plusieurs assert parce qu'ils précisent juste l'erreur si elle y est (nullVal ou diff)
        assertNotNull(result);
        assertEquals(e, result);
    }

    @Test
    public void testSearch4() {
        Point2D p = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        Point2D e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        Point2D e2 = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        
        instance.insert(e);
        instance.insert(e2);

        Point2D result = instance.search(p);

        assertNotNull(result);
        // Au mieux : les points sont comparés, au pire leurs adresses mémoires
        assertTrue(result == e || result == e2);
    }

    @Test
    public void testSearch5() {
        Point2D p = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        Point2D e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        Point2D e2 = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
        instance.insert(e);
        instance.insert(e2);
        instance.split();

        Point2D result = instance.search(p);

        assertTrue(result == e || result == e2);
    }

    @Test
    public void testCountPoints() {
        int result = instance.countPoints();

        assertEquals(0, result);
    }

    @Test
    public void testCountPoints2() {
        Point2D e;
        int number = faker.number().numberBetween(1, 5);
        for (int i = 0; i < number; i++) {
            e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
            instance.insert(e);
        }

        int result = instance.countPoints();

        assertEquals(number, result);
    }

    @Test
    public void testCountPoints3() {
        Point2D e;
        int number = faker.number().numberBetween(1, 5);
        for (int i = 0; i < number; i++) {
            e = new Point2D(
                faker.number().randomDouble(5, ix, ix2),
                faker.number().randomDouble(5, iy, iy2));
            instance.insert(e);
        }
        instance.split();

        int result = instance.countPoints();

        assertEquals(number, result);
    }

    // On ne peut évidemment pas tester render (en plus on a pas d'écran sur la machine qui run nos tests)

}
