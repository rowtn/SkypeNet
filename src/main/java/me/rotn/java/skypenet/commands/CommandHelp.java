package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;
import me.rotn.java.skypenet.SkypeNet;
import org.apache.commons.lang.StringUtils;

public class CommandHelp implements IBotCommand {

    @Override
    public String command(ChatMessage chatMessages, String[] args) {
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

    @Override
    public boolean requirePrefix() {
        return true;
    }
}
