package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Driver class.
 */
public class MainTest {
  /**
   * Initializes a new main class for the project.
   */
  Driver driver = new Driver();

  /**
   * Tests the main class with valid arguments, asserting that no
   * arguments are thrown.
   */
  @Test
  void testValidMainUppercase() {
    String[] args1 = {"./src/main/SampleInput",
        "FILENAME", "./src/main/SampleOutput/output.md"};
    assertDoesNotThrow(() -> driver.main(args1));
  }

  /**
   * Tests the main class with valid arguments, asserting that no
   * arguments are thrown.
   */
  @Test
  void testValidMainLowercase() {
    String[] args1 = {"./src/main/SampleInput",
        "filename", "./src/main/SampleOutput/output.md"};
    assertDoesNotThrow(() -> driver.main(args1));
  }

  /**
   * Tests the main class with valid arguments (none), asserting that no
   * exceptions (other than a NoSuchElementException) are thrown.
   */
  @Test
  public void testMainWithZeroArguments() {
    try {
      Driver.main(new String[] {});
    } catch (IllegalArgumentException e) {
      fail("Exception was thrown: " + e.getMessage());
    } catch (NoSuchElementException e) {
      // Expected exception
    }
  }

  /**
   * Tests the main method of the Driver class with invalid number of arguments,
   * and asserts that an IllegalArgumentException is thrown with the expected
   * error message.
   */
  @Test
  public void testInvalidMain() {
    String[] args2 = {"./src/main/SampleInput"};
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> driver.main(args2));

    assertEquals("Driver method expects either 0 or 3 arguments.", exception.getMessage());
  }
}