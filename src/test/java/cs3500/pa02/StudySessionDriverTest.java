package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.StudySessionDriver;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the StudySessionDriver
 */
public class StudySessionDriverTest {
  StudySessionDriver ssd;
  Flashcard f1;
  Flashcard f2;
  Flashcard f3;


  /**
   * Initializes a studySessionDriver with a test path and 3 flashcards
   */
  @BeforeEach
  public void setup() {
    ssd = new StudySessionDriver(
        Path.of("./src/main/SampleOutput/fileToFlashcardsOutputTestFile.sr"));
    f1 = new Flashcard("QUESTION1", "ANSWER1", DifficultyLevel.HARD);
    f2 = new Flashcard("QUESTION2", "ANSWER2", DifficultyLevel.EASY);
    f3 = new Flashcard("QUESTION3", "ANSWER3", DifficultyLevel.EASY);
  }

  /**
   * Tests that when configureFlashcards is run, getAllFlashcards contains the desired flashcards.
   */
  @Test
  public void configureFlashcardsTest() {
    assertFalse(ssd.getAllFlashcards().contains(f1));
    assertFalse(ssd.getAllFlashcards().contains(f2));
    assertFalse(ssd.getAllFlashcards().contains(f3));
    ssd.configureFlashcards();
    assertEquals(3, ssd.getAllFlashcards().size());
    assertTrue(ssd.getAllFlashcards().contains(f1));
    assertTrue(ssd.getAllFlashcards().contains(f2));
    assertTrue(ssd.getAllFlashcards().contains(f3));
  }
}