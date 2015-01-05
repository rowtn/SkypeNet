package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

public class CommandTopkek implements IBotCommand {

    @Override
    public String command(ChatMessage chatMessage, String[] args) {
        return "https://s.mazenmc.io/index.swf";
    }

    @Override
    public String name() {
        return "topkek";
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
