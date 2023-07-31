package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.studyguide.MdFile;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the cs3500.pa02.StudyGuide.MdFile class.
 */
public class MdFileTest {
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;

  /**
   * initializes arraysMdFile and sets its fields.
   */
  @BeforeEach
  public void initArrays() {
    Path arraysPath =
        Path.of("./src/main/SampleInput/Arrays.md");
    File arraysFile = arraysPath.toFile();
    String arraysName = arraysPath.toFile().getName();
    FileTime arraysCreated = FileTime.from(Instant.parse("2023-05-14T12:02:00Z"));
    FileTime arraysModified = FileTime.from(Instant.parse("2023-05-14T12:07:00Z"));
    arraysMdFile = new MdFile(arraysFile, arraysName, arraysCreated, arraysModified);
  }

  /**
   * Tests the "getFile" method
   */
  @Test
  public void getFileTest() {
    assertEquals(
        Path.of(
            "./src/main/SampleInput/Arrays.md").toFile(),
        arraysMdFile.getFile());
  }

  /**
   * Tests the "getName" method
   */
  @Test
  public void getNameTest() {
    assertEquals("Arrays.md", arraysMdFile.getName());
  }

  /**
   * Tests the "getCreated" method
   */
  @Test
  public void getCreatedTest() {
    assertEquals(FileTime.from(Instant.parse("2023-05-14T12:02:00Z")),
        arraysMdFile.getCreated());
  }

  /**
   * Tests the "getModified" method
   */
  @Test
  public void getModifiedTest() {
    assertEquals(FileTime.from(Instant.parse("2023-05-14T12:07:00Z")),
        arraysMdFile.getModified());
  }
}
