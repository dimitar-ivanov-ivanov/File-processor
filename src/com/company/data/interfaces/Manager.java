package com.company.data.interfaces;


/**
 * The interface Manager.
 * All file managers must implement this interface
 *
 * @author Dimitar Ivanov
 */
public interface Manager {

    /**
     * Switch lines in file.
     *
     * @param firsLinetIndex  the first line index
     * @param secondLineIndex the second line index
     */
    String switchLinesInFile(int firsLinetIndex, int secondLineIndex);

    /**
     * Switch words at chosen lines in file.
     *
     * @param firstLineIndex      the first line index
     * @param firstLineWordIndex  the first line word index
     * @param secondLineIndex     the second line index
     * @param secondLineWordIndex the second line word index
     */
    String switchWordsAtChosenLinesInFile(int firstLineIndex, int firstLineWordIndex, int secondLineIndex, int secondLineWordIndex);

    /**
     * Write to file.
     */
    String writeToFile();

    /**
     * Read from file.
     */
    void readFromFile();
}
