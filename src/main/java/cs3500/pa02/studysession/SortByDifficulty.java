package cs3500.pa02.studysession;

import java.util.Comparator;

/**
 * A comparator that sorts a list of Flashcards based on their difficulty level (HARD is first)
 */
public class SortByDifficulty implements Comparator<Flashcard> {

  /**
   * Compares two flashcards by their difficulty level
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return a positive integer if the difficulty level of o1 is earlier than
   *     the difficulty level of o2, zero if the difficulty level of o1 and o2 are
   *     the same, and a negative integer if the difficulty level of o1 is later
   *     than the difficulty level of o2.
   */
  @Override
  public int compare(Flashcard o1, Flashcard o2) {
    return Integer.compare(o1.getDiffLevel().ordinal(), o2.getDiffLevel().ordinal());
  }
}