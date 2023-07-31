package cs3500.pa02.studysession;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a study session driver that is responsible for configuring all the flashcards.
 */
public class StudySessionDriver {
  private Path flashcardBank;
  private ArrayList<Flashcard> flashcards;

  /**
   * Constructs a StudySessionDriver object with the given path.
   *
   * @param flashcardBank the path to the flashcard bank
   */
  public StudySessionDriver(Path flashcardBank) {
    this.flashcardBank = flashcardBank;
    this.flashcards = new ArrayList<>();
  }

  /**
   * Configures the flashcards for the study session. Reads the flashcards
   * from the flashcard bank, shuffles them, and sorts them by difficulty.
   */
  public void configureFlashcards() {
    GenerateFlashcards gf = new GenerateFlashcards(flashcardBank);
    gf.fileToFlashcards();
    this.flashcards = gf.getAllFlashcards();
    Collections.shuffle(flashcards);
    Collections.sort(flashcards, new SortByDifficulty());
  }

  /**
   * Returns all the flashcards configured for the study session.
   *
   * @return the list of flashcards
   */
  public ArrayList<Flashcard> getAllFlashcards() {
    return this.flashcards;
  }
}