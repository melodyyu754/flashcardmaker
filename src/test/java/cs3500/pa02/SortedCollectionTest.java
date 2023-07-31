package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.studyguide.MdFile;
import cs3500.pa02.studyguide.SortFlag;
import cs3500.pa02.studyguide.SortedCollection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the cs3500.pa02.StudyGuide.SortedCollection class.
 */
public class SortedCollectionTest {
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;
  MdFile vectorsMdFile;
  BasicFileAttributes vectorsAtts;

  /**
   * Initializes arraysMdFile and vectorsMdFile to be sorted.
   *
   * @throws IOException if there is an issue with file IO.
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
   * Tests the sortByName method.
   */
  @Test
  public void sortByNameTest() {
    SortedCollection scFilename =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.FILENAME);

    scFilename.sortBy();
    assertEquals(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
        scFilename.getSortedFiles());
  }

  /**
   * Tests the sortByCreated method
   */
  @Test
  public void sortByCreatedTest() {
    SortedCollection scCreated =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.CREATED);
    scCreated.sortBy();
    assertEquals(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
        scCreated.getSortedFiles());
  }

  /**
   * Tests the sortByModified method
   */
  @Test
  public void sortByModifiedTest() {
    SortedCollection scModified =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.MODIFIED);
    assertEquals(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
        scModified.getSortedFiles());

    scModified.sortBy();

    assertEquals(new ArrayList<MdFile>(Arrays.asList(vectorsMdFile, arraysMdFile)),
        scModified.getSortedFiles());
  }

  /**
   * Tests an invalid sortflag to ensure an error is thrown.
   */
  @Test
  public void sortByInvalidExceptionTest() {
    assertThrows(IllegalArgumentException.class, () ->
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.valueOf("INVALID")));

    assertThrows(IllegalArgumentException.class, () ->
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.valueOf("INVALID")).sortBy());
  }

  /**
   * Tests the "getSortedFiles" method.
   * Creates three different SortedCollection instances that sort MdFiles by filename,
   * created time, and modified time.
   * Sort the MdFiles in each SortedCollection instance and check whether the resulting lists
   * match the expected results
   */
  @Test
  public void getSortedFilesTest() {
    SortedCollection scFilename =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.FILENAME);
    SortedCollection scCreated =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.CREATED);
    SortedCollection scModified =
        new SortedCollection(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
            SortFlag.MODIFIED);

    scFilename.sortBy();
    scCreated.sortBy();
    scModified.sortBy();

    assertEquals(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
        scFilename.getSortedFiles());
    assertEquals(new ArrayList<MdFile>(Arrays.asList(arraysMdFile, vectorsMdFile)),
        scCreated.getSortedFiles());
    assertEquals(new ArrayList<MdFile>(Arrays.asList(vectorsMdFile, arraysMdFile)),
        scModified.getSortedFiles());
  }
}
