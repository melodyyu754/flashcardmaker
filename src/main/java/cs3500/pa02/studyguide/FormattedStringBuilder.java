package cs3500.pa02.studyguide;

import cs3500.pa02.studysession.DifficultyLevel;
import cs3500.pa02.studysession.Flashcard;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that builds a formatted study guide from a list of Markdown files.
 */
public class FormattedStringBuilder {
  private ArrayList<MdFile> sortedFiles;
  private StringBuilder studyGuide;
  private ArrayList<Flashcard> flashcards;

  /**
   * Instantiates a FormattedStringBuilder object.
   *
   * @param sortedFiles the list of MdFiles to build the study guide from
   */
  public FormattedStringBuilder(ArrayList<MdFile> sortedFiles) {
    this.sortedFiles = sortedFiles;
    this.studyGuide = new StringBuilder();
    this.flashcards = new ArrayList<>();
  }

  /**
   * Concatenates the study guide into a StringBuilder object by iterating
   * through each file and formatting its content.
   * If there is a #, it's a header, take the important info out of the current stringBlock
   * and take the header line as well. After doing so, reset the stringBlock to a new StringBuilder.
   * If it is not a header, then add the line to the stringBlock.
   * If there is no next line, take the important info out of the current stringBlock.
   */
  public void catStudyGuide() {
    boolean isFirstHeader = true;

    for (MdFile mdf : sortedFiles) {
      try (Scanner scanner = new Scanner(new File(mdf.getFile().getAbsolutePath()))) {
        StringBuilder stringBlock = new StringBuilder();

        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          if (line.startsWith("#")) {
            bracketHelp(stringBlock.toString());
            headerHelp(line, isFirstHeader);
            isFirstHeader = false;
            stringBlock = new StringBuilder();
          } else {
            stringBlock.append(line);
          }
        }
        bracketHelp(stringBlock.toString());
      } catch (IOException e) {
        System.out.println("Error reading file: " + mdf.getFile().getName());
        e.printStackTrace();
      }
    }
  }

  /**
   * A helper method that works with headers to append them to the studyGuide.
   *
   * @param line          the header line to append
   * @param isFirstHeader a boolean indicating whetehr this is the first header
   *                      being appended.
   */
  private void headerHelp(String line, boolean isFirstHeader) {
    if (!isFirstHeader) {
      studyGuide.append("\n");
    }
    studyGuide.append(line).append("\n");
  }

  /**
   * A helper method to append bracketed links to the study guide.
   * Separates the flashcards from the non-flashcard important info
   *
   * @param stringBlock the line to check for bracketed links and append to the study guide.
   */
  private void bracketHelp(String stringBlock) {
    int startIndex = stringBlock.indexOf("[[");
    int endIndex = stringBlock.indexOf("]]");

    while (startIndex != -1) {
      String currentLine = stringBlock.substring(startIndex + 2, endIndex);
      if (currentLine.contains(":::")) {
        int splitIndex = currentLine.indexOf(":::");
        // add a new flashcard to the arraylist of flashcards.
        flashcards.add(new Flashcard((currentLine.substring(0, splitIndex)),
            currentLine.substring(splitIndex + 3), DifficultyLevel.HARD));
      } else {
        studyGuide.append("- " + currentLine).append("\n");
      }
      startIndex = stringBlock.indexOf("[[", endIndex + 2);
      endIndex = stringBlock.indexOf("]]", startIndex);
    }
  }

  /**
   * Returns the final study guide as a String.
   *
   * @return the final study guide as a String.
   * @throws IllegalStateException if the study guide hasn't been built yet.
   */
  public String getStudyGuideString() {
    if (!this.studyGuide.isEmpty()) {
      return this.studyGuide.toString();
    } else {
      throw new IllegalStateException("StudyGuide not built yet.");
    }
  }

  /**
   * Returns the final flashcard file as a String, formatted correctly.
   *
   * @return the final flashcard file as a String
   * @throws IllegalStateException if the flashcard file hasn't been built yet.
   */
  public String getFlashcardFileString() {
    if (!this.flashcards.isEmpty()) {
      StringBuilder fcFileStringBuilder = new StringBuilder();
      for (Flashcard flashcard : flashcards) {
        String flashcardString = flashcard.getDiffLevel().toString() + " "
            + flashcard.getQuestion() + ":::" + flashcard.getAnswer();
        fcFileStringBuilder.append(flashcardString).append("\n");
      }
      return fcFileStringBuilder.toString();
    } else {
      throw new IllegalStateException("cs3500.pa02.StudySession.Flashcard list not built yet.");
    }
  }

  /**
   * Writes the formatted study guide to a file.
   *
   * @param finalString          the formatted study guide as a String
   * @param finalFlashcardString the formatted flashcards as a String
   * @param target               the file path as a string to write the study guide to
   */
  public void writeFile(String finalString, String finalFlashcardString, String target) {
    String srTarget = target.substring(0, target.length() - 3) + ".sr";

    try (FileWriter writer = new FileWriter(target)) {
      writer.write(finalString);
    } catch (IOException e) {
      System.out.println("Error writing to file: " + target);
      e.printStackTrace();
    }

    try (FileWriter writer = new FileWriter(srTarget)) {
      writer.write(finalFlashcardString);
    } catch (IOException e) {
      System.out.println("Error writing to file: " + srTarget);
      e.printStackTrace();
    }
  }
}