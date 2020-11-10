package com.company.core.commands.interfaces;

/**
 * The interface Executable.
 * All commands must implement this interface
 *
 * @author Dimitar Ivanov
 */
public interface Executable {

    /**
     * Execute string.
     *
     * @return message to indicate the result of the execution
     */
    String execute();
}
