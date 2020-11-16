package com.company.core.commands;

import com.company.annotations.Inject;
import com.company.data.interfaces.Manager;

/**
 * The type Delete row command.
 *
 * @author Dimitar Ivanov
 * @see Command
 */
public class DeleteRowCommand extends Command {

    /**
     * The File manager.
     */
    @Inject
    Manager fileManager;

    /**
     * Instantiates a new Command.
     *
     * @param data the data
     */
    public DeleteRowCommand(String[] data) {
        super(data);
    }

    /**
     * Get the index of the row from the given data and delete that row if possible.
     *
     * @return message about the status of the execution
     */
    @Override
    public String execute() {
        int rowIndex = Integer.parseInt(getData()[1]);
        return fileManager.deleteRowInFile(rowIndex);
    }
}
