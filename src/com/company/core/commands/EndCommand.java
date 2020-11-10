package com.company.core.commands;

import com.company.annotations.Inject;
import com.company.data.interfaces.Manager;

/**
 * The type End command.
 *
 * @author Dimitar Ivanov
 * @see Command
 */
public class EndCommand extends Command {

    /**
     * The File manager that is going to be injected
     */
    @Inject
    Manager fileManager;

    /**
     * Instantiates a new End command.
     *
     * @param data the data
     */
    public EndCommand(String[] data) {
        super(data);
    }

    /**
     * Before ending the program write the changes in the same file
     *
     * @return message about the status of the execution
     */
    @Override
    public String execute() {
        return fileManager.writeToFile();
    }
}
