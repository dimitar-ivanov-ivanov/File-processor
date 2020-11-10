package com.company.core.commands;

import com.company.common.TypeValidator;
import com.company.core.commands.interfaces.Executable;
import com.company.core.commands.interfaces.Interpreter;
import com.company.data.interfaces.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * The type Command interpreter.
 *
 * @author Dimitar Ivanov
 * @see Interpreter
 */
public class CommandInterpreter implements Interpreter {

    private final String COMMAND_DIRECTORY = "com.company.core.commands.";

    private Manager fileManager;

    /**
     * Instantiates a new Command interpreter.
     *
     * @param fileManager the file manager
     */
    public CommandInterpreter(Manager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Get the command name and instantiate it
     * The interpreter contains all fields that could be used by a command
     * If a command requires a field inject it through reflection
     *
     * @param data        the data
     * @param commandName the command name
     * @return the executable command
     */
    @Override
    public Executable interpret(String[] data, String commandName) {
        String uppercaseName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
        String fullName = COMMAND_DIRECTORY + uppercaseName + "Command";

        Executable executable = null;
        try {
            Class commandInstance = Class.forName(fullName);

            Constructor constructor = commandInstance.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true);
            executable = (Executable) constructor.newInstance((Object) data);

            injectsField(executable);
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return executable;
    }

    /**
     * Inject all fields in the executable command that have an annotation "Inject"
     *
     * @param executable the executable command
     */
    private void injectsField(Executable executable) {
        Field[] fieldsToInject = executable.getClass().getDeclaredFields();

        for (Field fieldToInject : fieldsToInject) {
            String firstAnnotation = fieldToInject.getAnnotations()[0].toString();

            if (firstAnnotation.contains("Inject")) {
                checkCurrentFields(executable, fieldToInject);
            }
        }
    }

    /**
     * Search the  current fields of the command interpreter and if the types match inject it to the executable
     *
     * @param executable    the executable command
     * @param fieldToInject the field to inject
     */
    private void checkCurrentFields(Executable executable, Field fieldToInject) {
        Field[] currentClassFields = this.getClass().getDeclaredFields();
        boolean injectedField = false;

        for (Field currentField : currentClassFields) {
            if (TypeValidator.checkIfFieldTypesAreEqual(fieldToInject, currentField) &&
                    tryToInjectField(executable, fieldToInject, currentField)) {
                break;
            }
        }
    }

    /**
     * Try to inject a field
     *
     * @param executable    the executable
     * @param fieldToInject the field of the executable that must be injected
     * @param currentField  the field of the interpreter that we will inject
     * @return true if the injection worked
     */
    private boolean tryToInjectField(Executable executable, Field fieldToInject, Field currentField) {
        fieldToInject.setAccessible(true);
        boolean injectedField = false;

        try {
            fieldToInject.set(executable, currentField.get(this));
            injectedField = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return injectedField;
    }
}
