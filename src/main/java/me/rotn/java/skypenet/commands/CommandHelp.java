package me.rotn.java.skypenet.commands;

import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.IBotCommand;
import me.rotn.java.skypenet.SkypeNet;
import org.apache.commons.lang.StringUtils;

public class CommandHelp implements IBotCommand {

    @Override
    public String command(String[] args) {
        return String.format("Commands: %s.", StringUtils.join(SkypeNet.getInstance().getCommands().keySet(), ", "));
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.HIGH;
    }
}
