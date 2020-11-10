package com.company.core.commands;

import com.company.annotations.Inject;
import com.company.data.interfaces.Manager;

/**
 * The type Switch word command.
 *
 * @author Dimitar Ivanov
 * @see Command
 */
public class SwitchWordCommand extends Command {

    /**
     * The File manager that is going to be injected
     */
    @Inject
    private Manager fileManager;

    /**
     * Instantiates a new Switch word command.
     *
     * @param data the data
     */
    public SwitchWordCommand(String[] data) {
        super(data);
    }

    /**
     * Get the index of the first line and the index of the second line and the indices of the words on each line
     * and pass them to the manager to handle them
     *
     * @return message about the status of the execution
     */
    @Override
    public String execute() {
        int firstLineIndex = Integer.parseInt(getData()[1]);
        int firstLineWordIndex = Integer.parseInt(getData()[2]);
        int secondLineIndex = Integer.parseInt(getData()[3]);
        int secondLineWordIndex = Integer.parseInt(getData()[4]);

        return fileManager.switchWordsAtChosenLinesInFile(firstLineIndex, firstLineWordIndex, secondLineIndex, secondLineWordIndex);
    }
}
