package me.rotn.java.skypenet.commands;

import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

public class CommandGit implements IBotCommand {
    @Override
    public String command(String[] args) {
        return "https://github.com/ROTNdev/SkypeNet";
    }

    @Override
    public String name() {
        return "git";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.LOW;
    }
}
