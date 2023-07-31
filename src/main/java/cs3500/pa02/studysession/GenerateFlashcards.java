package cs3500.pa02.studysession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Generates flashcards from a file and provides access to the generated flashcards
 */
public class GenerateFlashcards {
  Path srFile;
  ArrayList<Flashcard> allFlashcards = new ArrayList<>();

  /**
   * Constructs a GenerateFlashcards object with the specified source file path.
   *
   * @param srFile the path of the source file containing the flashcards
   */
  public GenerateFlashcards(Path srFile) {
    this.srFile = srFile;
    this.allFlashcards = new ArrayList<>();
  }

  /**
   * Reads the file at the specified source file path and converts its contents
   * into flashcards.
   */
  public void fileToFlashcards() {
    try {
      Scanner s = new Scanner(new File(srFile.toFile().getAbsolutePath()));
      while (s.hasNextLine()) {
        String currentLine = s.nextLine();
        int splitIndex = currentLine.indexOf(":::");
        DifficultyLevel diffLevel = (currentLine.substring(0, 4).equals("HARD"))
            ? DifficultyLevel.HARD : DifficultyLevel.EASY;
        this.allFlashcards.add(new Flashcard((currentLine.substring(5, splitIndex)),
            currentLine.substring(splitIndex + 3), diffLevel));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns the list of all generated flashcards.
   *
   * @return the list of all generated flashcards
   */
  public ArrayList<Flashcard> getAllFlashcards() {
    return this.allFlashcards;
  }
}
