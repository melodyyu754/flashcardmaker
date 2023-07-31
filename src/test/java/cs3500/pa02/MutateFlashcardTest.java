package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.Input;
import cs3500.pa02.studysession.MutateFlashcard;
import cs3500.pa02.studysession.SessionStatistics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the MutateFlashcard class
 */
public class MutateFlashcardTest {
  MutateFlashcard mf;
  SessionStatistics stats;

  /**
   * Initializes a set of flashcards and puts them into an arrayList, then initializes stats
   * and a MutateFlashcard object for them.
   */
  @BeforeEach
  public void setup() {
    Flashcard fc1 = new Flashcard("QUESTION1", "ANSWER1", DifficultyLevel.EASY);
    Flashcard fc2 = new Flashcard("QUESTION2", "ANSWER2", DifficultyLevel.HARD);
    Flashcard fc3 = new Flashcard("QUESTION3", "ANSWER3", DifficultyLevel.HARD);

    ArrayList<Flashcard> flashcards = new ArrayList<>(Arrays.asList(fc1, fc2, fc3));

    stats = new SessionStatistics(flashcards);
    mf = new MutateFlashcard(flashcards, stats);
  }

  /**
   * Tests the mutateFlashcard method with input "HARD"
   */
  @Test
  public void mutateFlashcardHardTest() {
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 1\n"
        + "Total hard: 2", stats.statsAsString());
    mf.mutateFlashcard(Input.HARD, 0);
    assertEquals("Flashcards reviewed: 1\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 1\n"
        + "Total easy: 0\n"
        + "Total hard: 3", stats.statsAsString());
  }

  /**
   * Tests the mutateFlashcard method with input "EASY"
   */
  @Test
  public void mutateFlashcardEasyTest() {
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 1\n"
        + "Total hard: 2", stats.statsAsString());
    mf.mutateFlashcard(Input.EASY, 1);
    assertEquals("Flashcards reviewed: 1\n"
        + "Hard to Easy: 1\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 2\n"
        + "Total hard: 1", stats.statsAsString());
  }

  /**
   * Tests the getFlashcardFileString method.
   */
  @Test
  public void getFlashcardFileStringTest() {
    assertEquals("EASY QUESTION1:::ANSWER1\n"
            + "HARD QUESTION2:::ANSWER2\n"
            + "HARD QUESTION3:::ANSWER3\n",
        mf.getFlashcardFileString());
  }

  /**
   * Tests the writeFile method.
   */
  @Test
  public void writeFileTest() {
    mf.writeFile("EASY QUESTION1:::ANSWER1\n"
            + "HARD QUESTION2:::ANSWER2\n"
            + "HARD QUESTION3:::ANSWER3\n",
        String.valueOf(Path.of("./src/main/SampleOutput/output.sr")));
    try {
      assertEquals("EASY QUESTION1:::ANSWER1\n"
              + "HARD QUESTION2:::ANSWER2\n"
              + "HARD QUESTION3:::ANSWER3\n",
          Files.readString(Path.of(
              "./src/main/SampleOutput/output.sr")));
    } catch (IOException e) {
      System.out.println("Unable to print to path specified.");
      fail();
    }
  }
}
