package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.GenerateFlashcards;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the GenerateFlashcards class.
 */
public class GenerateFlashcardsTest {
  Path srFilePath;
  GenerateFlashcards gf;

  /**
   * Instantiates a GenerateFlashcards object with a testing srFilePath.
   */
  @BeforeEach
  public void setUp() {
    Path srFilePath = Path.of("./src/main/SampleOutput/fileToFlashcardsOutputTestFile.sr");
    // Instantiate cs3500.pa02.StudySession.GenerateFlashcards with the temporary file path
    gf = new GenerateFlashcards(srFilePath);
  }

  /**
   * Tests the fileToFlashcards() method. Asserts that the generated flashcards are correct based
   * on the file.
   */
  @Test
  public void testFileToFlashcards() {
    // Call the fileToFlashcards method
    gf.fileToFlashcards();

    // Assert the generated flashcards
    ArrayList<Flashcard> allFlashcards = gf.getAllFlashcards();
    assertEquals(3, allFlashcards.size());

    assertTrue(gf.getAllFlashcards().contains(new Flashcard("QUESTION1", "ANSWER1",
        DifficultyLevel.HARD)));
    assertTrue(gf.getAllFlashcards().contains(new Flashcard("QUESTION2", "ANSWER2",
        DifficultyLevel.EASY)));

  }
}

