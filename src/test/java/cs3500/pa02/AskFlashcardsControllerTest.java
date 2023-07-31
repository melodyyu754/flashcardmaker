package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.studysession.AskFlashcardsController;
import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.SessionStatistics;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for AskFlashcardsController
 */
public class AskFlashcardsControllerTest {
  ArrayList<Flashcard> flashcards;
  SessionStatistics stats;
  Appendable output;

  /**
   * Initializes an arraylist of flashcards, statistics and an Appendable output.
   */
  @BeforeEach
  public void setup() {
    flashcards = new ArrayList<>();
    flashcards.add(new Flashcard("Question 1", "Answer 1", DifficultyLevel.EASY));
    flashcards.add(new Flashcard("Question 2", "Answer 2", DifficultyLevel.EASY));
    flashcards.add(new Flashcard("Question 3", "Answer 3", DifficultyLevel.HARD));

    stats = new SessionStatistics(flashcards);
    output = new StringBuilder();
  }

  /**
   * Tests the output when EXIT is inputted on the first flashcard
   */
  @Test
  public void testRunAllFlashcards_ExitInput() {
    Scanner scanner = new Scanner("EXIT\n");

    AskFlashcardsController controller = new AskFlashcardsController(
        flashcards, 3, stats, scanner, output);

    controller.runAllFlashcards("output.sr");

    String outputString = output.toString();
    String expectedOutput = "Question 1 (Input options: HARD/EASY/SHOWANSWER/EXIT)\n";
    assertEquals(expectedOutput, outputString.substring(0, expectedOutput.length()));

    // Verify that the flashcard file was not written
    assertEquals("Flashcards reviewed: 0\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 0\n"
        + "Total easy: 2\n"
        + "Total hard: 1", stats.statsAsString());
  }

  /**
   * Tests the output when SHOWANSWER, HARD, and EXIT are inputted.
   */
  @Test
  public void testRunAllFlashcards_ShowAnswerHardExitInput() {
    Scanner scanner = new Scanner("SHOWANSWER\nHARD\nEXIT\n");

    AskFlashcardsController controller = new AskFlashcardsController(
        flashcards, 3, stats, scanner, output);

    controller.runAllFlashcards("output.sr");

    // Verify the individual lines of output
    String outputString = output.toString();
    String[] lines = outputString.split("\n");

    assertEquals("Question 1 (Input options: HARD/EASY/SHOWANSWER/EXIT)", lines[0]);
    assertEquals("Answer 1 (Input options: HARD/EASY/EXIT)", lines[1]);
    assertEquals("Question 2 (Input options: HARD/EASY/SHOWANSWER/EXIT)", lines[2]);

    // Verify that the flashcard file was written
    assertEquals("Flashcards reviewed: 1\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 1\n"
        + "Total easy: 1\n"
        + "Total hard: 2", stats.statsAsString());
  }

  /**
   * Tests the output when INVALID, SHOWANSWER, HARD, and EXIT are inputted.
   */
  @Test
  public void testRunAllFlashcardsInvalidInput() {
    Scanner scanner = new Scanner("INVALID\nSHOWANSWER\nINVALID\nHARD\nEXIT\n");
    AskFlashcardsController controller = new AskFlashcardsController(
        flashcards, 10, stats, scanner, output);

    controller.runAllFlashcards("output.sr");

    // Verify the individual lines of output
    String outputString = output.toString();
    String[] lines = outputString.split("\n");

    assertEquals("Question 1 (Input options: HARD/EASY/SHOWANSWER/EXIT)", lines[0]);
    assertEquals("Invalid input. (Input options: HARD/EASY/SHOWANSWER/EXIT)", lines[1]);
    assertEquals("Answer 1 (Input options: HARD/EASY/EXIT)", lines[2]);
    assertEquals("Invalid input. (Input options: HARD/EASY/EXIT)", lines[3]);
    assertEquals("Question 2 (Input options: HARD/EASY/SHOWANSWER/EXIT)", lines[4]);

    // Verify that the flashcard file was written
    assertEquals("Flashcards reviewed: 1\n"
        + "Hard to Easy: 0\n"
        + "Easy to Hard: 1\n"
        + "Total easy: 1\n"
        + "Total hard: 2", stats.statsAsString());
  }
}