package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

public class CommandLenny implements IBotCommand {

    @Override
    public String command(ChatMessage chatMessage, String[] args) {
        return "( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)";
    }

    @Override
    public String name() {
        return "lenny";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.LOW;
    }

    @Override
    public boolean requirePrefix() {
        return true;
    }
}
