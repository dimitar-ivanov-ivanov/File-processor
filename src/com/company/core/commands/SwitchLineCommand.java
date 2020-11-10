package com.company.core.commands;

import com.company.annotations.Inject;
import com.company.data.interfaces.Manager;

/**
 * The type Switch line command.
 *
 * @author Dimitar Ivanov
 * @see Command
 */
public class SwitchLineCommand extends Command {

    /**
     * The File manager that is going to be injected
     */
    @Inject
    private Manager fileManager;

    /**
     * Instantiates a new Switch line command.
     *
     * @param data the data
     */
    public SwitchLineCommand(String[] data) {
        super(data);
    }

    /**
     * Get the index of the first line and the index of the second line
     * and pass them to the manager to handle them
     *
     * @return message about the status of the execution
     */
    @Override
    public String execute() {
        int firstLineIndex = Integer.parseInt(getData()[1]);
        int secondLineIndex = Integer.parseInt(getData()[2]);
        return fileManager.switchLinesInFile(firstLineIndex, secondLineIndex);
    }
}
