package com.company.data;

import com.company.data.interfaces.Manager;
import com.company.messages.OutputMessages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The type File manager.
 *
 * @author Dimitar Ivanov
 * @see Manager
 */
public class FileManager implements Manager {

    /**
     * The path to the file which we will be working on
     */
    private Path filePath;

    /**
     * Matrix in which each inner array represents a line in the file
     */
    private String[][] lines;

    /**
     * Instantiates a new File manager.
     */
    public FileManager() {
        readFromFile();
    }

    /**
     * Read from file
     */
    @Override
    public void readFromFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the full input file directory:");
        String inputPath = getPath(scanner);
        this.filePath = Paths.get(inputPath);
        this.lines = getLines(filePath);
    }

    private String getPath(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Get the file and it is valid turn
     *
     * @param inPath the path to the file
     * @return a matrix in which each inner array represents a line in the file
     * @throws IOException if we can't read from the file with the given path
     */
    private String[][] getLines(Path inPath) {
        String[][] lines = new String[0][];

        try {
            lines = Files.readAllLines(inPath)
                    .stream()
                    .map(line -> line.split(" "))
                    .toArray(String[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * Switch one line with another in the file
     *
     * @param firstLineIndex  the first line index
     * @param secondLineIndex the second line index
     * @return message for success
     */
    @Override
    public String switchLinesInFile(int firstLineIndex, int secondLineIndex) {
        String[] temp = lines[firstLineIndex];
        lines[firstLineIndex] = lines[secondLineIndex];
        lines[secondLineIndex] = temp;

        return String.format(OutputMessages.SWITCH_LINES_SUCCESS, firstLineIndex, secondLineIndex);
    }

    /**
     * Swap one word from a certain line with another word from another/same line
     *
     * @param firstLineIndex      the first line index
     * @param firstLineWordIndex  the first line word index
     * @param secondLineIndex     the second line index
     * @param secondLineWordIndex the second line word index
     * @return message for success
     */
    @Override
    public String switchWordsAtChosenLinesInFile(int firstLineIndex, int firstLineWordIndex, int secondLineIndex, int secondLineWordIndex) {
        String firstWord = lines[firstLineIndex][firstLineWordIndex];
        String secondWord = lines[secondLineIndex][secondLineWordIndex];

        lines[firstLineIndex][firstLineWordIndex] = lines[secondLineIndex][secondLineWordIndex];
        lines[secondLineIndex][secondLineWordIndex] = firstWord;

        return String.format(OutputMessages.SWITCH_WORDS_SUCCESS, firstWord, firstLineIndex, secondWord, secondLineIndex);
    }

    /**
     * Turn the matrix to a list in which each array gets turned to a line in the file
     * and write the results in the same file
     *
     * @return message for the status of the execution
     * @throws IOException if we can't write in the file with the given path
     */
    @Override
    public String writeToFile() {
        String result;
        List<String> linesToList = Arrays.stream(lines)
                .map(line -> String.join(" ", line))
                .collect(Collectors.toList());

        try {
            Files.write(filePath, linesToList);
            result = OutputMessages.WRITE_FILE_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            result = OutputMessages.WRITE_FILE_FAIL;
        }

        return result;
    }
}

