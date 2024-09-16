package com.marginallyclever.makelangelo.makeart.io;

import com.marginallyclever.makelangelo.Translator;
import com.marginallyclever.makelangelo.turtle.Turtle;
import com.marginallyclever.util.PreferencesHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.BeforeEach;

import java.util.stream.Stream;
import java.lang.reflect.Method;

import static com.marginallyclever.makelangelo.makeart.io.LoadHelper.loadAndTestFiles;
import static com.marginallyclever.makelangelo.makeart.io.LoadHelper.readFile;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

public class LoadGCodeTest {

    private LoadGCode loadGCode;

    @BeforeAll
    public static void beforeAll() {
        PreferencesHelper.start();
        Translator.start();
    }

    @BeforeEach
    public void setUp() {
        loadGCode = new LoadGCode();
    }

    @Test
    public void testAtan3() throws Exception {
      // Accéder à la méthode privée atan3 via la réflexion
      Method atan3Method = LoadGCode.class.getDeclaredMethod("atan3", double.class, double.class);
      atan3Method.setAccessible(true);
  
      // Cas de test 1: dx = 1, dy = 1 (angle 45°)
      double result = (double) atan3Method.invoke(loadGCode, 1.0, 1.0);
      assertEquals(Math.PI / 4, result, 0.0001);
  
      // Cas de test 2: dx = -1, dy = 1 (angle 135°)
      result = (double) atan3Method.invoke(loadGCode, 1.0, -1.0);
      assertEquals(3 * Math.PI / 4, result, 0.0001);
  
      // Cas de test 3: dx = -1, dy = -1 (angle 225°)
      result = (double) atan3Method.invoke(loadGCode, -1.0, -1.0);
      assertEquals(5 * Math.PI / 4, result, 0.0001);
  
      // Cas de test 4: dx = 1, dy = -1 (angle 315°)
      result = (double) atan3Method.invoke(loadGCode, -1.0, 1.0);
      assertEquals(7 * Math.PI / 4, result, 0.0001);
  
      // Cas de test 5: dx = 0, dy = 1 (angle 90°)
      result = (double) atan3Method.invoke(loadGCode, 1.0, 0.0);
      assertEquals(Math.PI / 2, result, 0.0001);
  
      // Cas de test 6: dx = 1, dy = 0 (angle 0°)
      result = (double) atan3Method.invoke(loadGCode, 0.0, 1.0);
      assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void canLoad() {
        // given
        TurtleLoader loader = new LoadGCode();

        // then
        assertTrue(loader.canLoad("file.gcode"));
        assertTrue(loader.canLoad("file.GCode"));
        assertFalse(loader.canLoad("file.txt"));
    }

    @Test
    public void throwExceptionWhenStreamIsNull() {
        // given
        TurtleLoader loader = new LoadGCode();

        // then
        assertThrows(NullPointerException.class, () -> {
            loader.load(LoadGCodeTest.class.getResourceAsStream("/doesNotExist"));
        }, "Input stream is null");
    }

    @TestFactory
    public Stream<DynamicTest> testAllFiles() {
        return loadAndTestFiles(of("multi_shapes_ignatus2.gcode",
                "multi_shapes_path-circle-line-rect.gcode"),
                "/gcode",
                this::verifyLoadGCode);
    }

    private void verifyLoadGCode(String filenameToTest, String fileExpected) {
        try {

            // given
            TurtleLoader loader = new LoadGCode();

            // when
            Turtle turtle = loader.load(LoadGCodeTest.class.getResourceAsStream(filenameToTest));

            // then
            assertNotNull(turtle);
            assertNotNull(turtle.history);
            assertEquals(readFile(fileExpected), turtle.history.toString());
        } catch( Exception e) {
            fail(e);
        }
    }
}