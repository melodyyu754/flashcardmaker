package cs3500.pa02.studyguide;

/**
 * Enumeration for sortflag, containing 3 options for how to sort the files
 */
public enum SortFlag {
  /**
   * FILENAME: a sortflag option when user wants to sort the files by filename (alphabetical).
   */
  FILENAME,
  /**
   * CREATED: a sortflag option when user wants to sort the files by date created.
   */
  CREATED,
  /**
   * MODIFIED: a sortflag option when user wants to sort the files by last date modified.
   */
  MODIFIED
}
