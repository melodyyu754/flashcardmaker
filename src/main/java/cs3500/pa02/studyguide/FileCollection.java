package cs3500.pa02.studyguide;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Implements the FileVisitor interface to collect a list of all Markdown files in a
 * directory and its subdirectories.
 */
public class FileCollection implements FileVisitor<Path> {
  Path root;
  ArrayList<MdFile> fileCollection;

  /**
   * Constructs a FileCollection object with the given path
   *
   * @param root the filepath to be walked
   */
  public FileCollection(Path root) {
    this.root = root;
    this.fileCollection = new ArrayList<>();
  }

  /**
   * Called before visiting a directory
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return CONTINUE to continue visiting
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * Called when visiting a file, adds regular ".md" files to an ArrayList.
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return CONTINUE to continue visiting.
   * @throws IOException if there is an error visiting the file.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    if (attrs.isRegularFile()) {
      if (file.toFile().getName().endsWith(".md")) {
        fileCollection.add(new MdFile(file.toFile(), file.toFile().getName(),
            attrs.creationTime(), attrs.lastModifiedTime()));
      }
    }
    return CONTINUE;
  }

  /**
   * Called when there is an error accecssing a file.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return CONTINUE to continue visiting.
   * @throws IOException if there is an error.
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    return CONTINUE;
  }

  /**
   * Called after visiting a directory.
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return CONTINUE to continue visiting
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return CONTINUE;
  }

  /**
   * Returns the ArrayList of .md files.
   *
   * @return the arraylist of files after walking the tree.
   */
  public ArrayList<MdFile> getFiles() {
    return this.fileCollection;
  }
}
