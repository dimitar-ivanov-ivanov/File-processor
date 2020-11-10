package com.company.core;

import com.company.core.commands.interfaces.Executable;
import com.company.core.commands.interfaces.Interpreter;
import com.company.messages.OutputMessages;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Engine.
 *
 * @author Dimtiar Ivanov
 * @see Runnable
 */
public class Engine implements Runnable {

    /**
     * Command interpreter that handles all lines of input
     */
    private Interpreter interpreter;

    /**
     * All valid commands that will be handled by the interpreter
     */
    private List<String> validCommands;


    /**
     * Instantiates a new Engine.
     *
     * @param interpreter the command interpreter
     */
    public Engine(Interpreter interpreter) {
        this.interpreter = interpreter;
        this.validCommands = new ArrayList<>();
        seedCommands();
    }

    /**
     * Add the valid commands to the list
     */
    private void seedCommands() {
        Collections.addAll(validCommands, "switchLine", "switchWord", "end");
    }

    /**
     * Scan the console and unless we get the end command use the command interpreter to get a command execute it
     *
     * @see OutputMessages#END_INPUT
     * @see Interpreter#interpret(String[], String)
     * @see Executable#execute()
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands: ");
        while (true) {

            String[] args = scanner.nextLine().split(" ");
            String commandName = args[0];

            if (validCommands.contains(commandName)) {
                Executable executable = interpreter.interpret(args, commandName);
                String result = executable.execute();
                System.out.println(result);

                if (commandName.equals(OutputMessages.END_INPUT)) {
                    break;
                }
            }
        }
    }
}
