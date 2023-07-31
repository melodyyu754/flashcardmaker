package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.SessionStatistics;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the SessionStatistics class.
 */
public class SessionStatisticsTest {
  SessionStatistics ss;

  /**
   * Initializes a set of flashcards and puts them into an arrayList, then initializes
   * stats for them.
   */
  @BeforeEach
  public void setup() {
    Flashcard fc1 = new Flashcard("QUESTION1", "ANSWER1", DifficultyLevel.EASY);
    Flashcard fc2 = new Flashcard("QUESTION2", "ANSWER2", DifficultyLevel.HARD);
    Flashcard fc3 = new Flashcard("QUESTION3", "ANSWER3", DifficultyLevel.HARD);

    ArrayList<Flashcard> flashcards = new ArrayList<>(Arrays.asList(fc1, fc2, fc3));

    ss = new SessionStatistics(flashcards);

  }

  /**
   * Tests the add1ToFlashcardsReviewed method by testing for the side effects of stats changing.
   */
  @Test
  public void add1ToFlashcardsReviewedTest() {
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 1\n"
        + "Total hard: 2", ss.statsAsString());
    ss.add1ToFlashcardsReviewed();
    assertEquals("Flashcards reviewed: 1\n"
            + "Hard to Easy: 0\n"
            + "Easy to Hard: 0\n"
            + "Total easy: 1\n"
            + "Total hard: 2", ss.statsAsString());
  }

  /**
   * Tests the mutateEasyToHard method by testing for the side effects of stats changing.
   */
  @Test
  public void mutateEasyToHardTest() {
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 1\n"
        + "Total hard: 2", ss.statsAsString());
    ss.mutateEasyToHard();
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 1\n"
        + "Total easy: 0\n"
        + "Total hard: 3", ss.statsAsString());
  }

  /**
   * Tests the mutateHardToEasy method by testing for the side effects of stats changing.
   */
  @Test
  public void mutateHardToEasyTest() {
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 1\n"
        + "Total hard: 2", ss.statsAsString());
    ss.mutateHardToEasy();
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 1\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 2\n"
        + "Total hard: 1", ss.statsAsString());
  }
}
