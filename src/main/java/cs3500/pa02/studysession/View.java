package cs3500.pa02.studysession;

import java.io.IOException;

/**
 * Represents a view for displaying output.
 */
public class View {
  private final Appendable output;

  /**
   * Constructs a view object with the given output.
   *
   * @param output the destination for output
   */
  public View(Appendable output) {
    this.output = output;
  }

  /**
   * Prints the specified string to the output destination
   *
   * @param string the string to be printed
   */
  public void print(String string) {
    try {
      output.append(string).append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}