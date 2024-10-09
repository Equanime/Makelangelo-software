package com.marginallyclever.makelangelo.makeart.io;

import java.lang.reflect.Method;
import java.awt.Color;
import com.marginallyclever.convenience.W3CColorNames;

import com.marginallyclever.makelangelo.turtle.Turtle;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static com.marginallyclever.makelangelo.makeart.io.LoadHelper.loadAndTestFiles;
import static com.marginallyclever.makelangelo.makeart.io.LoadHelper.readFile;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

public class LoadSVGTest {

    @Test
    public void canLoad() {
        // given
        TurtleLoader loader = new LoadSVG();

        // then
        assertTrue(loader.canLoad("file.svg"));
        assertFalse(loader.canLoad("file.txt"));
    }

    @Test
    public void throwExceptionWhenStreamIsNull() {
        // given
        TurtleLoader loader = new LoadSVG();

        // then
        assertThrows(NullPointerException.class, () -> {
            loader.load(LoadSVGTest.class.getResourceAsStream("/doesNotExist"));
        }, "Input stream is null");
    }

    @TestFactory
    public Stream<DynamicTest> testAllSVG() {
        return loadAndTestFiles(of("line.svg",
                "circle.svg",
                "ellipse.svg",
                "eule.svg",
                "multi_shapes_ignatus1.svg",
                "multi_shapes_ignatus2.svg",
                "multi_shapes_path-circle-line-rect.svg",
                "multi_shapes_ying-yang.svg",
                "rect.svg"),
                "/svg",
                this::verifyLoadSvg);
    }

    private void verifyLoadSvg(String filenameToTest, String fileExpected) {
        try {

            // given
            TurtleLoader loader = new LoadSVG();

            // when
            Turtle turtle = loader.load(LoadSVGTest.class.getResourceAsStream(filenameToTest));

            // then
            assertNotNull(turtle);
            assertNotNull(turtle.history);
            assertEquals(readFile(fileExpected), turtle.history.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    // @Test
    // private void verifyWeirdColors() {
    // try {

    // // given
    // TurtleLoader loader = new LoadSVG();

    // // when
    // Turtle turtle =
    // loader.load(LoadSVGTest.class.getResourceAsStream(filenameToTest));

    // // then
    // assertNotNull(turtle);
    // assertNotNull(turtle.history);
    // assertEquals(readFile(fileExpected), turtle.history.toString());
    // } catch (Exception e) {
    // fail(e);
    // }
    // }

    // @Test
    // @Test
    // public void testEggsadecimal() throws NoSuchMethodException,
    // SecurityException, Exception {
    // // Arrange

    // // try {

    // Method sTCMethod = LoadSVG.class.getDeclaredMethod("stringToColor",
    // String.class);
    // sTCMethod.setAccessible(true);

    // // Act
    // Color color = (Color) sTCMethod.invoke("");
    // Color color1 = (Color) sTCMethod.invoke(new String("#"));
    // // Color color2 = (Color) sTCMethod.invoke(new String("#3%"));
    // // Color color3 = (Color) sTCMethod.invoke(new String("#z"));
    // Color color4 = (Color) sTCMethod.invoke(new String("#3"));
    // Color color5 = (Color) sTCMethod.invoke(new String("#33"));
    // Color color6 = (Color) sTCMethod.invoke(new String("#666"));
    // Color color7 = (Color) sTCMethod.invoke(new String("#666666"));
    // Color color8 = (Color) sTCMethod.invoke(new String("#66666666"));

    // assertNull(color);
    // assertNull(color1);
    // // assertThrows(color2);
    // // assertThrows(color3);
    // assertEquals(color4, new Color(3,3,3));
    // assertEquals(color5, new Color(51,51,51));
    // assertEquals(color6, new Color(6,6,6));
    // assertEquals(color7, new Color(102,102,102));
    // assertEquals(color8, new Color(102,102,102,102));
    // // } catch (Exception e) {
    // // // TODO: handle exception
    // // fail(e);
    // // }

    // }

    // @Test
    // public void testRGB(){
    // // Arrange

    // try {

    // Method sTCMethod = LoadSVG.class.getDeclaredMethod("stringToColor",
    // String.class);
    // sTCMethod.setAccessible(true);

    // // Act
    // Color color = (Color) sTCMethod.invoke(new String("#z"));
    // Color color1 = (Color) sTCMethod.invoke(new String("yed"));
    // Color color2 = (Color) sTCMethod.invoke(new String("blue"));

    // // testRGB()
    // // "rgb()"
    // // "rgb(,,)"
    // // "rgb(300%,0,0)"
    // // "rgb(-10,0,0)"
    // // "rgb(300,0,0)"
    // // "rgb(,,)"
    // assertNull(color);
    // assertNull(color1);
    // assertEquals(color2, W3CColorNames.get("blue"));
    // } catch (Exception e) {
    // // TODO: handle exception
    // fail(e);
    // }

    // }

    // @Test
    // public void testName() throws NoSuchMethodException, SecurityException,
    // Exception {
    // // Arrange

    // // try {

    // Method sTCMethod = LoadSVG.class.getDeclaredMethod("stringToColor",
    // String.class);
    // sTCMethod.setAccessible(true);

    // // Act
    // // Color color1 = (Color) sTCMethod.invoke(new String("yed"));
    // Color color2 = (Color) sTCMethod.invoke(new String("blue"));

    // // assertNull(color1);
    // assertEquals(color2, W3CColorNames.get("blue"));
    // // } catch (Exception e) {
    // // // TODO: handle exception
    // // fail(e);
    // // }

    // }
    @Test
    void testStringToColorUsingReflection() throws Exception {
        LoadSVG loader = new LoadSVG();

        Method method = LoadSVG.class.getDeclaredMethod("stringToColor", String.class);
        method.setAccessible(true);

        // Cas 1: Hex court, longueur 1
        Color expected = new Color(0x1, 0x1, 0x1);
        Color result = (Color) method.invoke(loader, "#1");
        assertEquals(expected, result);

        // Cas 2: Hex court, longueur 2
        expected = new Color(0x11, 0x11, 0x11);
        result = (Color) method.invoke(loader, "#11");
        assertEquals(expected, result);

        // Cas 3: Hex longueur 3
        expected = new Color(0x1, 0x2, 0x3);
        result = (Color) method.invoke(loader, "#123");
        assertEquals(expected, result);

        // Cas 4: Hex longueur 6
        expected = new Color(0x12, 0x34, 0x56);
        result = (Color) method.invoke(loader, "#123456");
        assertEquals(expected, result);

        // Cas 5: Hex longueur 8 (avec alpha)
        expected = new Color(0x12, 0x34, 0x56, 0x78);
        result = (Color) method.invoke(loader, "#12345678");
        assertEquals(expected, result);

        // Cas 6: RGB classique
        expected = new Color(255, 0, 0);
        result = (Color) method.invoke(loader, "rgb(255,0,0) ");
        assertEquals(expected, result);

        // Cas 7: RGB avec pourcentage
        expected = new Color(255, 127, 0);
        result = (Color) method.invoke(loader, "rgb(100%,50%,0%) ");
        assertEquals(expected, result);

        // Cas 8: Couleur nommée "red"
        expected = Color.RED;
        result = (Color) method.invoke(loader, "red");
        assertEquals(expected, result);

        // Cas 9: Couleur nommée "blue"
        expected = Color.BLUE;
        result = (Color) method.invoke(loader, "blue");
        assertEquals(expected, result);

        //!FAIL TO COMPILE
        // // Cas 10: Nom de couleur inconnu
        // exception = assertThrows(InvocationTargetException.class, () -> {
        //     method.invoke(loader, "rgb(xyz,255,255)");
        // });
        // assertTrue(exception.getCause() instanceof NumberFormatException);

        // // Cas 11: Hex invalide
        // assertThrows(NumberFormatException.class, () -> {
        //     method.invoke(loader, "#xyz");
        // });

        // // Cas 12: RGB invalide
        // assertThrows(NumberFormatException.class, () -> {
        //     method.invoke(loader, "rgb(xyz,255,255)");
        // });
    }
}
