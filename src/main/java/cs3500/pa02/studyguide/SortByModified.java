package cs3500.pa02.studyguide;

import java.util.Comparator;

/**
 * A comparator that sorts a list of MdFiles based on their last modified date
 * in ascending order.
 */
public class SortByModified implements Comparator<MdFile> {

  /**
   * Compares two MdFiles by their last modified date.
   *
   * @param o1 the first MdFile to be compared.
   * @param o2 the second MdFile to be compared.
   * @return a negative integer if the last modified date of o1 is earlier than
   *     the last modified date of o2, zero if the last modified date of o1 and o2 are
   *     the same, and a positive integer if the last modified date of o1 is later
   *     than the last modified date of o2.
   */
  @Override
  public int compare(MdFile o1, MdFile o2) {
    return -1 * o1.getModified().compareTo(o2.getModified());
  }
}
