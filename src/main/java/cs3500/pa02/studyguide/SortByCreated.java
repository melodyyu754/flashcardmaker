package cs3500.pa02.studyguide;

import java.util.Comparator;

/**
 * A comparator that sorts a list of MdFiles based on their creation date
 * in ascending order.
 */
public class SortByCreated implements Comparator<MdFile> {

  /**
   * Compares two MdFiles by their creation date
   *
   * @param o1 the first cs3500.pa02.StudyGuide.MdFile to be compared.
   * @param o2 the second cs3500.pa02.StudyGuide.MdFile to be compared.
   * @return a negative integer if the creation date of o1 is earlier than
   *     the creation date of o2, zero if the creation dates of o1 and o2 are
   *     the same, and a positive integer if the creation date of o1 is later
   *     than the creation date of o2.
   */
  @Override
  public int compare(MdFile o1, MdFile o2) {
    return o1.getCreated().compareTo(o2.getCreated());
  }
}
