package cs3500.pa02.studyguide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The FileDriver class is responsible for driving the file processing program.
 * It walks the file tree starting from the root directory, adds all the files
 * to an ArrayList of MdFiles, and then sorts the ArrayList based on the given
 * sort flag and concatenates the contents of the sorted files and writes the
 * ouput to the target file.
 */
public class FileDriver {
  private Path root;
  private SortFlag sortFlag;
  private String target;

  /**
   * Instantiates the FileDriver object.
   *
   * @param root     The root directory for the program
   * @param sortFlag The sort flag to be used for sorting the files.
   * @param target   The target file name for writing the sorted output.
   */
  public FileDriver(Path root, SortFlag sortFlag, String target) {
    this.root = root;
    this.sortFlag = sortFlag;
    this.target = target;
    run();
  }

  /**
   * Runs the program. Walks the file tree adding .md files to an ArrayList
   * of MD Files, sorts the ArrayList, concatenates select content from the sorted
   * files, and then writes the output to the target file.
   *
   */
  public void run() {
    FileCollection fc = new FileCollection(root);
    try {
      Files.walkFileTree(root, fc);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    SortedCollection sc = new SortedCollection(fc.getFiles(), sortFlag);
    sc.sortBy();

    FormattedStringBuilder fsb = new FormattedStringBuilder(sc.getSortedFiles());
    fsb.catStudyGuide();
    fsb.writeFile(fsb.getStudyGuideString(), fsb.getFlashcardFileString(), target);
  }
}