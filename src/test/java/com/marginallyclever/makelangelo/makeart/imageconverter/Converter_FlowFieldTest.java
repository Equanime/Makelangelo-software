package com.marginallyclever.makelangelo.makeart.imageconverter;
import com.marginallyclever.convenience.noise.Noise;
import com.marginallyclever.convenience.noise.NoiseFactory;
import com.marginallyclever.convenience.noise.PerlinNoise;
import com.marginallyclever.makelangelo.Translator;
import com.marginallyclever.makelangelo.makeart.TransformedImage;
import com.marginallyclever.makelangelo.makeart.imagefilter.FilterDesaturate;
import com.marginallyclever.makelangelo.paper.Paper;
import com.marginallyclever.makelangelo.turtle.Turtle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class Converter_FlowFieldTest {

    private Converter_FlowField converter;

    @BeforeEach
    public void setUp() {
        converter = new Converter_FlowField();
    }

    @Test
    public void testSetAndGetScaleX() {
        Converter_FlowField.setScaleX(0.02);

        double result = Converter_FlowField.getScaleX();

        assertEquals(0.02, result);
    }

    @Test
    public void testSetAndGetScaleY() {
        Converter_FlowField.setScaleY(0.02);
        
        double result = Converter_FlowField.getScaleY();

        assertEquals(0.02, result);
    }

    @Test
    public void testSetAndGetOffsetX() {
        Converter_FlowField.setOffsetX(1.0);

        double result = Converter_FlowField.getOffsetX();

        assertEquals(1.0, result);
    }

    @Test
    public void testSetAndGetOffsetY() {
        Converter_FlowField.setOffsetY(1.0);
        
        double result = Converter_FlowField.getOffsetY();

        assertEquals(1.0, result);
    }

    @Test
    public void testSetAndGetStepSize() {
        Converter_FlowField.setStepSize(15);
        
        int result = Converter_FlowField.getStepSize();

        assertEquals(15, result);
    }

    @Test
    public void testSetAndGetRightAngle() {
        Converter_FlowField.setRightAngle(true);
        
        boolean result = Converter_FlowField.getRightAngle();

        assertTrue(result);
    }

    @Test
    public void testName() {
        String name = converter.getName();

        assertEquals("Flow field", name);
    }

}
