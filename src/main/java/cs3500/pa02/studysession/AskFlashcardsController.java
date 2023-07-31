package cs3500.pa02.studysession;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AskFlashcardsController class handles the interaction between the user and the flashcards
 * during a study session. It prompts the user with flashcard questions and records their
 * responses.
 */
public class AskFlashcardsController {
  private ArrayList<Flashcard> allFlashcards;
  private int howManyQs;
  private SessionStatistics stats;
  private Scanner scanner;
  private final Appendable output;

  /**
   * Constructs an AskFlashcardsController object with the specified flashcards,
   * number of questions, session statistics, input scanner, and output appendable.
   *
   * @param allFlashcards the ArrayList of all flashcards in the original file
   * @param howManyQs     the number of flashcards to review
   * @param stats         the session statistics
   * @param scanner             the input scanner passed in from the InitController
   * @param output        the output appendable
   */
  public AskFlashcardsController(ArrayList<Flashcard> allFlashcards, int howManyQs,
                                 SessionStatistics stats, Scanner scanner, Appendable output) {
    this.allFlashcards = allFlashcards;
    this.howManyQs = howManyQs;
    this.stats = stats;
    this.scanner = scanner;
    this.output = output;
  }

  /**
   * Runs the study session with the specified target output. For every flashcard in
   * the list of flashcards, accept inputs to either exit the loop and mutate the existing
   * flashcard, or show the answer
   *
   * @param target the target ouput for the flashcard file after the session is run
   */
  public void runAllFlashcards(String target) {
    View v = new View(output);
    MutateFlashcard mf = new MutateFlashcard(this.allFlashcards, stats);
    int howManyQs = Math.min(this.howManyQs, allFlashcards.size());

    for (int i = 0; i < howManyQs; i++) {
      Flashcard flashcard = allFlashcards.get(i);
      v.print(flashcard.getQuestion() + " (Input options: HARD/EASY/SHOWANSWER/EXIT)");

      Input input;
      try {
        input = Input.valueOf(scanner.nextLine().toUpperCase());
      } catch (IllegalArgumentException e) {
        v.print("Invalid input. (Input options: HARD/EASY/SHOWANSWER/EXIT)");
        input = Input.valueOf(scanner.nextLine().toUpperCase());
      }

      while (true) {
        if (input.equals(Input.EXIT)) {
          mf.writeFile(mf.getFlashcardFileString(), target);
          v.print(stats.statsAsString());
          return; // Exit the method and program
        } else if (input.equals(Input.HARD) || input.equals(Input.EASY)) {
          mf.mutateFlashcard(input, i);
          break;
        } else if (input.equals(Input.SHOWANSWER)) {
          v.print(flashcard.getAnswer() + " (Input options: HARD/EASY/EXIT)");
          try {
            input = Input.valueOf(scanner.nextLine().toUpperCase());
          } catch (IllegalArgumentException e) {
            v.print("Invalid input. (Input options: HARD/EASY/EXIT)");
            input = Input.valueOf(scanner.nextLine().toUpperCase());
          }
        } else {
          v.print("Invalid input. (Input options: HARD/EASY/SHOWANSWER/EXIT)");
          input = Input.valueOf(scanner.nextLine().toUpperCase());
        }
      }
    }
    mf.writeFile(mf.getFlashcardFileString(), target);
    v.print(stats.statsAsString());
  }
}