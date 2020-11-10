package com.company;

import com.company.core.Engine;
import com.company.core.commands.CommandInterpreter;
import com.company.core.commands.interfaces.Interpreter;
import com.company.data.FileManager;
import com.company.data.interfaces.Manager;

public class Main {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        Manager fileManager = new FileManager();
        Interpreter commandInterpreter = new CommandInterpreter(fileManager);
        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
