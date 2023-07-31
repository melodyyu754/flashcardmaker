package cs3500.pa02.studysession;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the initialization controller for the study sesion.
 */
public class InitController implements Controller {
  private Path flashcardBank;
  private int howManyQs;
  private ArrayList<Flashcard> flashcards;
  private final Readable input;
  private final Appendable output;

  /**
   * Constructs an InitController object using the given readable and appendable
   *
   * @param input  the input source
   * @param output the output source
   */
  public InitController(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
    this.flashcardBank = null;
    this.howManyQs = 0;
    this.flashcards = new ArrayList<>();
  }

  String path;
  private Scanner scanner;

  /**
   * Initializes the flashcard bank path and the quantity of flashcards to review.
   * If input is invalid, prints a statement telling the user so.
   */
  public void initBankAndQuantity() {
    View v = new View(output);
    scanner = new Scanner(input);
    v.print("What is the path?");
    path = scanner.nextLine();
    if (Files.exists(Path.of(path))) {
      this.flashcardBank = Path.of(path);
    } else {
      v.print("Please enter valid path to .sr file.");
      this.flashcardBank = Path.of(scanner.nextLine());
    }

    v.print("How many questions would you like to study?");
    int quantity = Integer.parseInt(scanner.nextLine());
    this.howManyQs = quantity;
  }

  /**
   * Pulls everything together in a method. Makes the flashcards based on the
   * given file, initializes the statistics before the session, and iterates
   * through all the flashcards with user input.
   */
  @Override
  public void run() {
    // makes the flashcards based on the given file, initializes this class's flashcards to it
    StudySessionDriver ss = new StudySessionDriver(flashcardBank);
    ss.configureFlashcards();
    this.flashcards = ss.getAllFlashcards();

    // sets a variable: the flashcards before the session is run.
    ArrayList<Flashcard> flashcardsBeforeSession = ss.getAllFlashcards();

    // initializes the statistics for the flashcards before the session
    SessionStatistics stats = new SessionStatistics(flashcardsBeforeSession);

    // initializes the controller with the input and output passed through the initController
    // constructor
    AskFlashcardsController af = new AskFlashcardsController(flashcardsBeforeSession, howManyQs,
        stats, scanner, output);

    // runs through all the flashcards
    af.runAllFlashcards(path);
  }

  /**
   * Returns the path of the flashcard bank.
   *
   * @return the path of the flashcard bank
   */
  public Path getFlashcardBank() {
    return this.flashcardBank;
  }

  /**
   * Returns the quantity of questions to study.
   *
   * @return the quantity of questions to study
   */
  public int getHowManyQs() {
    return this.howManyQs;
  }

  /**
   * Returns the list of flashcards
   *
   * @return the list of flashcards
   */
  public ArrayList<Flashcard> getFlashcards() {
    return this.flashcards;
  }
}
