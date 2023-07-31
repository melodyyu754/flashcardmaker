package cs3500.pa02.studysession;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a class for mutating flashcards and statistics.
 */
public class MutateFlashcard {
  private ArrayList<Flashcard> allFlashcards;
  private SessionStatistics stats;

  /**
   * Constructs a MutateFlashcard object with the specified flashcards and statistics.
   *
   * @param allFlashcards the list of all flashcards
   * @param stats         the current stats, to be mutated if necessary
   */
  public MutateFlashcard(ArrayList<Flashcard> allFlashcards, SessionStatistics stats) {
    this.allFlashcards = allFlashcards;
    this.stats = stats;
  }

  /**
   * Mutates a flashcard based on the given input and index
   *
   * @param input the input option
   * @param index the index of the flashcard
   */
  public void mutateFlashcard(Input input, int index) {
    stats.add1ToFlashcardsReviewed();
    if (input.equals(Input.HARD)
        && (allFlashcards.get(index).getDiffLevel().equals(DifficultyLevel.EASY))) {
      allFlashcards.get(index).easyToHard();
      stats.mutateEasyToHard();
    }
    if (input.equals(Input.EASY)
        && (allFlashcards.get(index).getDiffLevel().equals(DifficultyLevel.HARD))) {
      allFlashcards.get(index).hardToEasy();
      stats.mutateHardToEasy();
    }
  }

  /**
   * Returns the flashcard list as a formatted string for writing to a file.
   *
   * @return the flashcard list as a formatted string.
   */
  public String getFlashcardFileString() {
    StringBuilder fcFileStringBuilder = new StringBuilder();
    for (Flashcard flashcard : allFlashcards) {
      String flashcardString = flashcard.getDiffLevel().toString() + " "
          + flashcard.getQuestion() + ":::" + flashcard.getAnswer();
      fcFileStringBuilder.append(flashcardString).append("\n");
    }
    return fcFileStringBuilder.toString();
  }

  /**
   * Writes the final flashcard string to a file at the specified target path.
   *
   * @param finalFlashcardString the final flashcard string
   * @param target               the target path of the file
   */
  public void writeFile(String finalFlashcardString, String target) {
    try (FileWriter writer = new FileWriter(target)) {
      writer.write(finalFlashcardString);
    } catch (IOException e) {
      System.out.println("Error writing to file: " + target);
      e.printStackTrace();
    }
  }
}
