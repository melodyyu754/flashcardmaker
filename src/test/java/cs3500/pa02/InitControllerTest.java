package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import cs3500.pa02.studysession.InitController;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests for the initController class.
 */
public class InitControllerTest {
  /**
   * Tests the initBankAndQuantity() method. Initializes a controller with preset readable and
   * appendable objects, then checks if the flashcardBank and HowManyQs fields are initialized
   * correctly.
   */
  @Test
  public void testInitBankAndQuantity() {
    String string = "./src/main/SampleOutput/output.sr";

    Readable input = new StringReader("xyz\n" + string + "\n" + 3);

    Appendable output = new StringBuilder();

    InitController controller = new InitController(input, output);

    assertEquals(null, controller.getFlashcardBank());

    // run the workflow
    controller.initBankAndQuantity();

    // check that the flashcardBank and howManyQs is set correctly
    assertEquals("./src/main/SampleOutput/output.sr", controller.getFlashcardBank().toString());
    assertEquals(3, controller.getHowManyQs());
  }

  /**
   * Tests the run() method. Initializes a controller with preset readable and appendable objects,
   * then asserts if the flashcard list has the right flashcards
   */
  @Test
  public void testRun() {
    String string = "./src/main/SampleOutput/fileToFlashcardsOutputTestFile.sr";
    Readable input = new StringReader(string + "\n" + 1 + "\nHARD\n" + "EASY\n" + "EASY\n");
    Appendable output = new StringBuilder();

    InitController controller = new InitController(input, output);
    controller.initBankAndQuantity();

    assertEquals(new ArrayList<>(), controller.getFlashcards());

    controller.run();

    assertNotEquals(new ArrayList<>(), controller.getFlashcards());
    assertEquals(3, controller.getFlashcards().size());

    assertTrue(controller.getFlashcards().contains(new Flashcard("QUESTION1",
        "ANSWER1", DifficultyLevel.HARD)));
    assertTrue(controller.getFlashcards().contains(new Flashcard("QUESTION2",
        "ANSWER2", DifficultyLevel.EASY)));
    assertTrue(controller.getFlashcards().contains(new Flashcard("QUESTION3",
        "ANSWER3", DifficultyLevel.EASY)));
  }
}