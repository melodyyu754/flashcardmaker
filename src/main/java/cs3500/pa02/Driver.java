package cs3500.pa02;

import cs3500.pa02.studyguide.FileDriver;
import cs3500.pa02.studyguide.SortFlag;
import cs3500.pa02.studysession.InitController;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * The Driver class is the entry point for the file processing program.
 * The program processes files within the root directory and writes
 * the sorted output to the target file.
 */
public class Driver {
  /**
   * The main method for the program - parses command line arguments
   * and starts the file processing program. If there are no command line arguments,
   * run a study session.
   *
   * @param args The command line arguments. Expects either three arguments (the root directory,
   *             the sort flag, and the target file name) or none.
   * @throws IllegalArgumentException if the number of arguments passed is
   *                                  not equal to three.
   */
  public static void main(String[] args) {
    if (args.length == 3) {
      Path root = Path.of(args[0]);
      SortFlag sortFlag = SortFlag.valueOf(args[1].toUpperCase());
      String target = args[2];

      FileDriver fd = new FileDriver(root, sortFlag, target);
    } else if (args.length == 0) {
      InitController c = new InitController(new InputStreamReader(System.in),
          new PrintStream(System.out));
      c.initBankAndQuantity();
      c.run();
    } else {
      throw new IllegalArgumentException("Driver method expects either 0 or 3 arguments.");
    }
  }
}