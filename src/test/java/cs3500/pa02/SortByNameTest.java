package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studyguide.MdFile;
import cs3500.pa02.studyguide.SortByName;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the sortByName Comparator.
 */
public class SortByNameTest {
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;
  MdFile vectorsMdFile;
  BasicFileAttributes vectorsAtts;

  /**
   * Initializes arraysMdFile and vectorsMdFile to be compared using sortByName
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
  }

  /**
   * Tests that the cs3500.pa02.StudyGuide.SortByName comparator correctly sorts MdFiles
   * by their name.
   */
  @Test
  public void getNameTest() {
    Comparator<MdFile> sbn = new SortByName();
    assertTrue(sbn.compare(vectorsMdFile, arraysMdFile) > 0);
  }

}
