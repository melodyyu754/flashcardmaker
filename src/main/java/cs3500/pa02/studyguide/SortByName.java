package cs3500.pa02.studyguide;

import java.util.Comparator;

/**
 * A comparator that sorts a list of MdFiles in lexicographic order.
 */
public class SortByName implements Comparator<MdFile> {

  /**
   * Compares two MdFiles by their name lexicographically.
   *
   * @param o1 the first cs3500.pa02.StudyGuide.MdFile to be compared.
   * @param o2 the second cs3500.pa02.StudyGuide.MdFile to be compared.
   * @return a negative integer if the name of o1 comes before the name of
   *     o2 alphabetically, zero if the names of o1 and o2 are the same, or a positive
   *     integer if the name of o1 comes after the name of o2 alphabetically.
   */
  @Override
  public int compare(MdFile o1, MdFile o2) {
    return o1.getName().compareTo(o2.getName());
  }
}
