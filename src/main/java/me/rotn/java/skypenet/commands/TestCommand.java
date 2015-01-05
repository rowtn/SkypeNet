package me.rotn.java.skypenet.commands;

import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

public class TestCommand implements IBotCommand {
    @Override
    public String command(String[] message) {
        return "Hello, World";
    }

    @Override
    public String name() {
        return "test";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.NORMAL;
    }
}
