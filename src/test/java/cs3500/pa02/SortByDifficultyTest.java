package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.SortByDifficulty;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the SortByDifficulty Comparator.
 */
public class SortByDifficultyTest {
  Flashcard fc1;
  Flashcard fc2;

  /**
   * Initializes two flashcards
   */
  @BeforeEach
  public void setup() {
    fc1 = new Flashcard("What color is the sky?", "Blue", DifficultyLevel.HARD);
    fc2 = new Flashcard("What color is the sun?", "Yellow", DifficultyLevel.EASY);
  }

  /**
   * Tests that the SortByDifficulty comparator correctly sorts flashcards by
   * their difficulty level
   */
  @Test
  public void sortByDifficultyLevelTest() {
    Comparator<Flashcard> sbd = new SortByDifficulty();
    assertTrue(sbd.compare(fc1, fc2) < 0);
  }
}
