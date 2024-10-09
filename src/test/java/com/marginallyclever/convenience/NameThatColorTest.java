package com.marginallyclever.convenience;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import com.marginallyclever.convenience.ColorRGB;

import static org.junit.jupiter.api.Assertions.*;

public class NameThatColorTest {
    public NameThatColor instance = new NameThatColor();

    @Test
    public void RGBExtractorTest() {
        long color = 0x4682B4;

        float red = this.instance.red(color);
        float green = this.instance.green(color);
        float blue = this.instance.blue(color);

        assertEquals(0x46, red);
        assertEquals(0x82, green);
        assertEquals(0xB4, blue);
    }

    @Test
    public void colorFinderTest() {
        float r = 0x46;
        float g = 0x82;
        float b = 0xB4;

        String name = instance.find(r, g, b);

        assertEquals("steel blue", name);
    }

    @Test
    public void colorFinderTest2() {
        long color = 0x4682B5;

        String name = instance.find(color);

        assertEquals("steel blue", name);
    }

    @Test
    public void colorFinderClass(){
        Color color = new Color(0x4682A4);

        String name = instance.find(color);

        assertEquals("steel blue", name);
    }

    @Test
    public void colorFinderClass2(){
        ColorRGB color = new ColorRGB(0x46,0x82,0xA4);

        String name = instance.find(color);

        assertEquals("steel blue", name);
    }
}
