package cs3500.pa02.studyguide;

import java.io.File;
import java.nio.file.attribute.FileTime;

/**
 * The cs3500.pa02.StudyGuide.MdFile class represents a Markdown file. It contains the File object,
 * the file name, the creation time, and the modification time of the .md file.
 */
public class MdFile {
  private File file;
  private String name;
  private FileTime created;
  private FileTime modified;

  /**
   * Instantiates a new Markdown File.
   *
   * @param file     The file object for the Markdown file.
   * @param name     The name of the Markdown file.
   * @param created  The creation time of the Markdown file.
   * @param modified The last modified time of the Markdown file.
   */
  public MdFile(File file, String name, FileTime created, FileTime modified) {
    this.file = file;
    this.name = name;
    this.created = created;
    this.modified = modified;
  }

  /**
   * Gets the file object for the Markdown file.
   *
   * @return The file object of the Markdown file.
   */
  public File getFile() {
    return this.file;
  }

  /**
   * Gets the name for the Markdown file.
   *
   * @return The name of the Markdown file.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the creation time for the Markdown file.
   *
   * @return The creation time of the Markdown file.
   */
  public FileTime getCreated() {
    return this.created;
  }

  /**
   * Gets the last modified time for the Markdown file.
   *
   * @return The last modified time of the Markdown file.
   */
  public FileTime getModified() {
    return this.modified;
  }
}