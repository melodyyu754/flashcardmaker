package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.studyguide.FileDriver;
import cs3500.pa02.studyguide.SortFlag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the cs3500.pa02.StudyGuide.FileDriver object, which drives the program.
 */
public class FileDriverTest {
  /**
   * Initializes a fileDriver object.
   */
  FileDriver fd =
      new FileDriver(Path.of("./src/main/SampleInput"),
          SortFlag.FILENAME,
          "./src/main/SampleOutput/output.md");

  /**
   * Test case to check if the cs3500.pa02.StudyGuide.FileDriver correctly reads the input file
   * and writes its contents to the output file. This test case asserts that
   * the output file contains the expected string value.
   *
   * @throws IOException if there is an error reading or writing the files.
   */
  @Test
  public void runTest() {
    fd.run();

    try {
      assertEquals("# Java Arrays\n"
              + "- An **array** is a collection of variables of the same type\n"
              + "\n"
              + "## Declaring an Array\n"
              + "- General Form: type[] arrayName;\n"
              + "- only creates a reference\n"
              + "- no array has actually been created yet\n"
              + "\n"
              + "## Creating an Array (Instantiation)\n"
              + "- General form:  arrayName = new type[numberOfElements];\n"
              + "- numberOfElements must be a positive Integer.\n"
              + "- Gotcha: Array size is not modifiable once instantiated.\n"
              + "- Extra Important Info for Testing\n"
              + "\n"
              + "# Vectors\n"
              + "- Vectors act like resizable arrays\n"
              + "\n"
              + "## Declaring a vector\n"
              + "- General Form: Vector<type> v = new Vector();\n"
              + "- type needs to be a valid reference type\n"
              + "\n"
              + "## Adding an element to a vector\n"
              + "- v.add(object of type);\n",
          Files.readString(Path.of(
              "./src/main/SampleOutput/output.md")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
