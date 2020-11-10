package com.company.core.commands.interfaces;

/**
 * The interface Interpreter.
 *
 * @author Dimitar Ivanov
 */
public interface Interpreter {

    /**
     * Interpret executable.
     *
     * @param data        the data
     * @param commandName the command name
     * @return the executable
     */
    Executable interpret(String[] data, String commandName);
}
