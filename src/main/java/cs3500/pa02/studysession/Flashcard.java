package cs3500.pa02.studysession;

/**
 * Represents a flashcard with a question, answer, and difficulty level.
 */
public class Flashcard {
  private String question;
  private String answer;
  private DifficultyLevel diffLevel;

  /**
   * Constructs a flashcard with the specified question, answer, and difficulty level.
   *
   * @param question  the question on the flashcard
   * @param answer    the answer to the flashcard question
   * @param diffLevel the difficulty level of the flashcard
   */
  public Flashcard(String question, String answer, DifficultyLevel diffLevel) {
    this.question = question;
    this.answer = answer;
    this.diffLevel = diffLevel;
  }

  /**
   * Compares this flashcard to the specified object for equality, returning
   * true if all the fields are equal.
   *
   * @param o the object to compare to this flashcard
   * @return true if the flashcard and the object are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Flashcard)) {
      return false;
    }
    Flashcard that = (Flashcard) o;
    return this.question.equals(that.question) && this.answer.equals(that.answer)
        && this.diffLevel.equals(that.diffLevel);
  }

  /**
   * Returns a hash code value for this flashcard
   *
   * @return the hash code value for the flashcard
   */
  @Override
  public int hashCode() {
    return this.question.hashCode() * 1000 + this.answer.hashCode() * 20
        + this.diffLevel.hashCode();
  }

  /**
   * Changes the difficulty level of the flashcard to "EASY"
   */
  public void hardToEasy() {
    this.diffLevel = DifficultyLevel.EASY;
  }

  /**
   * Changes the difficulty level of the flashcard to "HARD"
   */
  public void easyToHard() {
    this.diffLevel = DifficultyLevel.HARD;
  }

  /**
   * Returns the question on the flashcard
   *
   * @return the question on the flashcard
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Returns the answer on the flashcard
   *
   * @return the answer on the flashcard
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Returns the difficulty level on the flashcard
   *
   * @return the difficulty level on the flashcard
   */
  public DifficultyLevel getDiffLevel() {
    return this.diffLevel;
  }
}
