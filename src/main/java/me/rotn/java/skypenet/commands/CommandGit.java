package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

public class CommandGit implements IBotCommand {
    @Override
    public String command(ChatMessage chatMessages, String[] args) {
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

    @Override
    public boolean requirePrefix() {
        return true;
    }
}
