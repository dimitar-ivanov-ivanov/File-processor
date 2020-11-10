package com.company.core.commands;

import com.company.core.commands.interfaces.Executable;


/**
 * The type Command.
 *
 * @author Dimitar Ivanov
 * @see Executable
 */
public abstract class Command implements Executable {

    /**
     * The data needed for execution
     */
    private String[] data;

    /**
     * Instantiates a new Command.
     *
     * @param data the data
     */
    public Command(String[] data) {
        this.data = data;
    }

    /**
     * Get data
     *
     * @return the data
     */
    public String[] getData() {
        return data;
    }
}
