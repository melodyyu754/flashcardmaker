package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studyguide.MdFile;
import cs3500.pa02.studyguide.SortByCreated;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the SortByCreated Comparator.
 */
public class SortByCreatedTest {
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;

  MdFile vectorsMdFile;

  BasicFileAttributes vectorsAtts;
  /**
   * Initializes arraysMdFile and vectorsMdFile to be compared using SortByCreated
   */

  @BeforeEach
  public void setUp() {
    Path arraysPath =
        Path.of("/Users/melodyyu/CS3500/pa01-melodyyu754/src/main/SampleInput/Arrays.md");
    File arraysFile = arraysPath.toFile();
    String arraysName = arraysPath.toFile().getName();
    FileTime arraysCreated = FileTime.from(Instant.parse("2023-05-14T12:02:00Z"));
    FileTime arraysModified = FileTime.from(Instant.parse("2023-05-14T12:07:00Z"));
    arraysMdFile = new MdFile(arraysFile, arraysName, arraysCreated, arraysModified);

    Path vectorsPath =
        Path.of("/Users/melodyyu/CS3500/pa01-melodyyu754/src/main/"
            + "SampleInput/Folder1/Vectors.md");
    File vectorsFile = vectorsPath.toFile();
    String vectorsName = vectorsPath.toFile().getName();
    FileTime vectorsCreated = FileTime.from(Instant.parse("2023-05-14T12:10:00Z"));
    FileTime vectorsModified = FileTime.from(Instant.parse("2023-05-14T12:12:00Z"));
    vectorsMdFile = new MdFile(vectorsFile, vectorsName, vectorsCreated, vectorsModified);
  }

  /**
   * Tests that the SortByCreated comparator correctly sorts MdFiles by
   * their created time.
   */
  @Test
  public void getNameTest() {
    Comparator<MdFile> sbn = new SortByCreated();
    assertTrue(sbn.compare(vectorsMdFile, arraysMdFile) > 0);
  }

}
