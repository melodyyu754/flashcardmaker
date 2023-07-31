package cs3500.pa02.studysession;

import java.util.ArrayList;

/**
 * Represents the session statistics of a study session
 */
public class SessionStatistics {
  private int flashcardsReviewed;
  private int hardToEasy;
  private int easyToHard;
  private int totalEasy;
  private int totalHard;

  /**
   * Constructs a session statistics object with the initial statistics based on the
   * given flashcards
   *
   * @param flashcards the full list of flashcards
   */
  public SessionStatistics(ArrayList<Flashcard> flashcards) {
    this.flashcardsReviewed = 0;
    this.hardToEasy = 0;
    this.easyToHard = 0;
    this.totalEasy = 0;
    this.totalHard = 0;

    for (Flashcard fc : flashcards) {
      if (fc.getDiffLevel().equals(DifficultyLevel.EASY)) {
        this.totalEasy++;
      } else if (fc.getDiffLevel().equals(DifficultyLevel.HARD)) {
        this.totalHard++;
      }
    }
  }

  /**
   * Adds one to the number of flashcards reviewed.
   */
  public void add1ToFlashcardsReviewed() {
    this.flashcardsReviewed++;
  }

  /**
   * Increments the count of easy-to-hard mutations and updates the total counts
   * of easy and hard flashcards.
   */
  public void mutateEasyToHard() {
    this.easyToHard++;
    this.totalEasy--;
    this.totalHard++;
  }

  /**
   * Increments the count of hard-to-easy mutations and updates the total counts
   * of easy and hard flashcards.
   */
  public void mutateHardToEasy() {
    this.hardToEasy++;
    this.totalHard--;
    this.totalEasy++;
  }

  /**
   * Returns the stats as a formatted string
   *
   * @return the session statistics as a string
   */
  public String statsAsString() {
    return "Flashcards reviewed: "
        + this.flashcardsReviewed
        + "\nHard to Easy: "
        + this.hardToEasy
        + "\nEasy to Hard: "
        + this.easyToHard
        + "\nTotal easy: "
        + this.totalEasy
        + "\nTotal hard: "
        + this.totalHard;
  }
}
