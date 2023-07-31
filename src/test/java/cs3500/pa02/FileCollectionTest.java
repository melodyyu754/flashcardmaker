package cs3500.pa02;

import static java.nio.file.FileVisitResult.CONTINUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.studyguide.FileCollection;
import cs3500.pa02.studyguide.MdFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the cs3500.pa02.StudyGuide.FileCollection class.
 */
public class FileCollectionTest {
  Path arraysPath;
  MdFile arraysMdFile;
  BasicFileAttributes arraysAttrs;

  MdFile vectorsMdFile;
  BasicFileAttributes vectorsAtts;

  FileCollection fc;
  Path sampleInputPath;

  /**
   * Sets up the necessary variables for testing.
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

    sampleInputPath = Path.of("./src/main/SampleInput");
    fc = new FileCollection(sampleInputPath);
  }

  /**
   * Tests that the visitFile method throws a NullPointerException when given an
   * invalid path.
   */
  @Test
  public void visitFileInvalidTest() {
    Path invalidPath = Path.of("/invalid/path/to/file.md");
    BasicFileAttributes invalidAttrs = null;
    assertThrows(NullPointerException.class, () -> fc.visitFile(invalidPath, invalidAttrs));
  }

  // probably need to fix this

  /**
   * Tests that the visitFileFailed method returns CONTINUE.
   */
  @Test
  public void visitFileFailedTest() {
    try {
      assertEquals(CONTINUE, fc.visitFileFailed(arraysPath, new IOException()));
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests that the visitFile method returns CONTINUE when given a non-regular file.
   */
  @Test
  public void visitNonRegularFileTest() {
    // create a directory
    Path dir = null;
    try {
      dir = Files.createTempDirectory("testDirectory");
    } catch (IOException e) {
      fail();
    }

    // create a symbolic link pointing to the directory
    Path link = null;
    try {
      link = Files.createTempFile("testLink", "");
    } catch (IOException e) {
      fail();
    }
    try {
      Files.deleteIfExists(link);
    } catch (IOException e) {
      fail();
    }
    try {
      Files.createSymbolicLink(link, dir);
    } catch (IOException e) {
      fail();
    }

    // check that the symbolic link is not a regular file
    assertFalse(Files.isRegularFile(link));

    // Pass the non-regular file to the visitFile method
    FileVisitResult result = null;
    try {
      result = fc.visitFile(link, Files.readAttributes(link, BasicFileAttributes.class));
    } catch (IOException e) {
      fail();
    }

    // Assert that the method returns CONTINUE
    assertEquals(FileVisitResult.CONTINUE, result);
  }


  /**
   * Tests the "getFiles" method by comparing expectedFiles and the actual files.
   */
  @Test
  public void getFiles() {
    ArrayList<MdFile> expectedFiles = new ArrayList<>();
    expectedFiles.add(arraysMdFile);
    expectedFiles.add(vectorsMdFile);
    try {
      Files.walkFileTree(sampleInputPath, fc);
    } catch (IOException e) {
      fail();
    }
    assertEquals(expectedFiles.get(0).getName(), fc.getFiles().get(0).getName());
    assertEquals(expectedFiles.get(1).getName(), fc.getFiles().get(1).getName());
  }

}
