package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.studyguide.FormattedStringBuilder;
import cs3500.pa02.studyguide.MdFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the tests for the cs3500.pa02.StudyGuide.FormattedStringBuilder class.
 */
public class FormattedStringBuilderTest {
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;
  MdFile vectorsMdFile;
  BasicFileAttributes vectorsAtts;
  FormattedStringBuilder fsb;

  /**
   * Initializes arraysMdFile, vectorsMdFile, and a FormattedStringBuider object.
   */
  @BeforeEach
  public void setUp() {
    Path arraysPath =
        Path.of("./src/main/SampleInput/Arrays.md");
    File arraysFile = arraysPath.toFile();
    String arraysName = arraysPath.toFile().getName();
    FileTime arraysCreated = FileTime.from(Instant.parse("2023-05-14T12:02:00Z"));
    FileTime arraysModified = FileTime.from(Instant.parse("2023-05-14T12:07:00Z"));
    arraysMdFile = new MdFile(arraysFile, arraysName, arraysCreated, arraysModified);

    Path vectorsPath =
        Path.of("./src/main/"
            + "SampleInput/Folder1/Vectors.md");
    File vectorsFile = vectorsPath.toFile();
    String vectorsName = vectorsPath.toFile().getName();
    FileTime vectorsCreated = FileTime.from(Instant.parse("2023-05-14T12:10:00Z"));
    FileTime vectorsModified = FileTime.from(Instant.parse("2023-05-14T12:12:00Z"));
    vectorsMdFile = new MdFile(vectorsFile, vectorsName, vectorsCreated, vectorsModified);

    fsb = new FormattedStringBuilder(
        new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)));


  }

  /**
   * Tests "getStudyGuideString" method
   * Asserts that an IllegalStateException is thrown if "getStudyGuideString" is called
   * before "catStudyGuide".
   */
  @Test
  public void getStudyGuideStringTest() {
    assertThrows(IllegalStateException.class, () -> fsb.getStudyGuideString());
    fsb.catStudyGuide();
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
        fsb.getStudyGuideString());
  }

  /**
   * Tests the getFlashcardFileString method. Asserts that an IllegalStateException is thrown
   * if "getFlashcardFileString" is called before the arrayList of flashcards is built up.
   */
  @Test
  public void getFlashcardFileStringTest() {
    assertThrows(IllegalStateException.class, () -> fsb.getFlashcardFileString());
    fsb.catStudyGuide();
    assertEquals("HARD What color is the sky?:::Blue\n"
            + "HARD What color is the sun?:::Yellow\n"
            + "HARD What color is the grass?:::Green\n",
        fsb.getFlashcardFileString());
  }

  /**
   * Tests the writeFile method.
   * Writes the wanted study guide string out to the file, and asserts that it is equal to
   * what is now in the .md file. Writes the wanted flashcard file string out to the file,
   * and asserts that it is equal to what is now in the .sr file.
   */
  @Test
  public void writeFileTest() {
    fsb.writeFile("# Java Arrays\n"
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
        "HARD QUESTION1:::ANSWER1\n"
            + "HARD QUESTION2:::ANSWER2\n"
            + "HARD QUESTION3:::ANSWER3\n",
        "./src/main/SampleOutput/output.md");

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
      fail();
    }

    try {
      assertEquals("HARD QUESTION1:::ANSWER1\n"
              + "HARD QUESTION2:::ANSWER2\n"
              + "HARD QUESTION3:::ANSWER3\n",
          Files.readString(Path.of(
              "./src/main/SampleOutput/output.sr")));
    } catch (IOException e) {
      fail();
    }
  }
}
