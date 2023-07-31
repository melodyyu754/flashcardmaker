package cs3500.pa02.studyguide;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A collection of MdFiles that can be sorted either by filename, creation date,
 * or their last modification date.
 */
public class SortedCollection {
  private ArrayList<MdFile> fileCollection;
  private SortFlag sortFlag;

  /**
   * Constructs a new cs3500.pa02.StudyGuide.SortedCollection object with the given list of MdFiles
   * and sort flag.
   *
   * @param fileCollection the list of MdFiles to sort.
   * @param sortFlag       a String indicating how to sort the MdFiles; must be one of
   *                       "filename", "created", or "modified"
   */

  public SortedCollection(ArrayList<MdFile> fileCollection, SortFlag sortFlag) {
    this.fileCollection = fileCollection;
    this.sortFlag = sortFlag;
  }

  /**
   * Sorts the file collection based on the sortFlag.
   *
   * @throws IllegalArgumentException if sortFlag is not one of the three valid options
   */
  public void sortBy() {
    if (sortFlag == SortFlag.FILENAME) {
      Collections.sort(fileCollection, new SortByName());
    } else if (sortFlag == SortFlag.CREATED) {
      Collections.sort(fileCollection, new SortByCreated());
    } else {
      Collections.sort(fileCollection, new SortByModified());
    }
  }

  /**
   * Gets the sorted list of MdFiles
   *
   * @return an ArrayList containing the sorted MdFiles
   */
  public ArrayList<MdFile> getSortedFiles() {
    return this.fileCollection;
  }

}
