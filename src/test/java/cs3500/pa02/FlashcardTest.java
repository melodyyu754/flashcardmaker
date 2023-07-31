package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the flashcard class and its methods
 */
public class FlashcardTest {
  Flashcard fc1;
  Flashcard fc2;

  /**
   * Initializes two flashcards
   */
  @BeforeEach
  public void setup() {
    fc1 = new Flashcard("What color is the sky?", "Blue", DifficultyLevel.HARD);
    fc2 = new Flashcard("What color is the sun?", "Yellow", DifficultyLevel.EASY);
  }

  /**
   * Tests the overridden equals method. Tests .equals method on same objects, objects of different
   * classes, slightly different objects, and objects that are different but have the same fields.
   */
  @Test
  public void equalsTest() {
    Flashcard fc1Copy = new Flashcard("What color is the sky?",
        "Blue", DifficultyLevel.HARD);
    assertEquals(fc1, fc1Copy);

    Flashcard fc1DifferentLevel = new Flashcard("What color is the sky?",
        "Blue", DifficultyLevel.EASY);
    assertNotEquals(fc1, fc1DifferentLevel);

    Flashcard fc1DifferentQuestion = new Flashcard("What color is the moon?",
        "Blue", DifficultyLevel.EASY);
    assertNotEquals(fc1, fc1DifferentQuestion);

    Flashcard fc1DifferentAnswer = new Flashcard("What color is the moon?",
        "Blue", DifficultyLevel.EASY);
    assertNotEquals(fc1, fc1DifferentAnswer);

    assertNotEquals(fc1, fc2);
    assertNotEquals(fc1, new String("hello"));

    assertEquals(fc1, fc1);
  }

  /**
   * Tests the hashCode method on a variety of different objects.
   */
  @Test
  public void hashCodeTest() {
    assertNotEquals(fc1.hashCode(), fc2.hashCode());
    assertNotEquals(fc1.hashCode(), new String("hello").hashCode());
    Flashcard fc1Copy = new Flashcard("What color is the sky?",
        "Blue", DifficultyLevel.HARD);
    assertEquals(fc1.hashCode(), fc1Copy.hashCode());
    assertEquals(fc1.hashCode(), fc1.hashCode());
  }

  /**
   * Tests the hardToEasy method. Checks that fc1 is originally hard, mutates fc1 to easy and
   * checks it, then ensures that when you call hardToEasy on an easy card, it remains easy.
   */
  @Test
  public void hardToEasyTest() {
    assertEquals(DifficultyLevel.HARD, fc1.getDiffLevel());
    fc1.hardToEasy();
    assertEquals(DifficultyLevel.EASY, fc1.getDiffLevel());
    fc1.hardToEasy();
    assertEquals(DifficultyLevel.EASY, fc1.getDiffLevel());
  }

  /**
   * Tests the easyToHard method. Checks that fc2 is originally easy, mutates fc2 to hard and
   * checks it, then ensures that when you call easyToHard on a hard card, it remains hard.
   */
  @Test
  public void easyToHardTest() {
    // checks that fc2 is originally easy
    assertEquals(DifficultyLevel.EASY, fc2.getDiffLevel());
    fc2.easyToHard();
    // mutates fc2 to hard, and checks it.
    assertEquals(DifficultyLevel.HARD, fc2.getDiffLevel());
    // ensures that when you call easyToHard on a hard card, it remains hard.
    fc2.easyToHard();
    assertEquals(DifficultyLevel.HARD, fc2.getDiffLevel());
  }

  /**
   * Tests the getQuestion() method;
   */
  @Test
  public void getQuestionTest() {
    assertEquals("What color is the sky?", fc1.getQuestion());
    assertEquals("What color is the sun?", fc2.getQuestion());
  }

  /**
   * Tests the getAnswer() method;
   */
  @Test
  public void getAnswerTest() {
    assertEquals("Blue", fc1.getAnswer());
    assertEquals("Yellow", fc2.getAnswer());
  }

  /**
   * Tests the getDiffLevel() method;
   */
  @Test
  public void getDiffLevel() {
    assertEquals(DifficultyLevel.HARD, fc1.getDiffLevel());
    assertEquals(DifficultyLevel.EASY, fc2.getDiffLevel());
  }
}
