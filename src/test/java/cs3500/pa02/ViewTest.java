package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.studysession.View;
import org.junit.jupiter.api.Test;

/**
 * Tests the view class
 */
public class ViewTest {
  /**
   * Tests that the StringBuilder in the constructor of View is built up as desired
   */
  @Test
  public void testPrint() {
    StringBuilder writer = new StringBuilder();
    View view = new View(writer);

    view.print("QUESTION1");

    String output = writer.toString();

    assertEquals("QUESTION1\n", output);
  }
}
