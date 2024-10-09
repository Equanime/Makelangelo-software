package com.marginallyclever.convenience;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class ColorRGBTest {
    public ColorRGB instance = new ColorRGB();

    @Test
    public void testEquals() {
        // Arrange
        ColorRGB test = new ColorRGB(35, 75, 200);

        // Act
        instance.set(35, 75, 200);

        // Assert
        assertTrue(instance.equals(test));
    }

    @Test
    public void copyTest() {
        // Arrange
        instance.set(35, 75, 200);

        // Act
        ColorRGB test = new ColorRGB(instance);

        // Assert
        assertEquals(instance.getRed(), test.getRed());
        assertEquals(instance.getGreen(), test.getGreen());
        assertEquals(instance.getBlue(), test.getBlue());
    }

    @Test
    public void stringTest() {
        // Arrange
        instance.set(35, 75, 200);

        // Act
        String rgb = instance.toString();

        // Assert
        assertEquals("(35,75,200)", rgb);
    }

    @Test
    public void hexTest() {
        // Arrange
        instance.set(35, 75, 200);

        // Act
        String hex = instance.toHexString();

        // Assert
        assertEquals("#234BC8", hex);
    }

    @Test
    public void addTest() {
        // Arrange
        instance.set(35, 75, 200);
        ColorRGB test = new ColorRGB(10, 20, 30);

        // Act
        ColorRGB result = instance.add(test);

        // Assert
        assertEquals(45, result.getRed());
        assertEquals(95, result.getGreen());
        assertEquals(230, result.getBlue());
    }

    @Test
    public void subTest() {
        // Arrange
        instance.set(35, 75, 200);
        ColorRGB test = new ColorRGB(10, 20, 30);

        // Act
        ColorRGB result = instance.sub(test);

        // Assert
        assertEquals(25, result.getRed());
        assertEquals(55, result.getGreen());
        assertEquals(170, result.getBlue());
    }

    @Test
    public void mulTest() {
        // Arrange
        instance.set(35, 75, 200);

        // Act
        ColorRGB result = instance.mul(0.5);

        // Assert
        assertEquals(17, result.getRed());
        assertEquals(37, result.getGreen());
        assertEquals(100, result.getBlue());
    }

    @Test
    public void diffSquaredTest() {
        // Arrange
        instance.set(35, 75, 200);
        ColorRGB test = new ColorRGB(10, 20, 30);

        // Act
        float result = instance.diffSquared(test);

        // Assert
        assertEquals(32550, result);
    }

    @Test
    public void testSetHSB() {
        // Arrange
        instance.set(35, 75, 200);
        ColorHSB hsb = new ColorHSB(0.5f, 0.5f, 0.5f);

        // Act
        instance.set(hsb);

        // Assert
        assertEquals(64, instance.getRed());
        assertEquals(128, instance.getGreen());
        assertEquals(128, instance.getBlue());
    }

    @Test
    public void testSetInt() {
        // Arrange
        instance.set(35, 75, 200);

        // Act
        instance.set(0x234BC8);

        // Assert
        assertEquals(35, instance.getRed());
        assertEquals(75, instance.getGreen());
        assertEquals(200, instance.getBlue());
    }
}
