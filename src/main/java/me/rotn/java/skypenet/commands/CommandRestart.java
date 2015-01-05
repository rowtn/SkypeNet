package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import com.skype.SkypeException;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.Keyboard;
import me.rotn.java.skypenet.framework.IBotCommand;

public class CommandRestart implements IBotCommand {
    @Override
    public String command(ChatMessage chatMessage, String[] args) {
        try {
            if (chatMessage.getSender().isAuthorized()) {
                Keyboard.type("/me Restarting");
                System.exit(0);
            }
        } catch (SkypeException e) {
            e.printStackTrace();
            return "Something went wrong...";
        }
        return "You do not have permission to do this.";
    }

    @Override
    public String name() {
        return "restart";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.OMGSOHIGHNOONEWILLDENYYOU;
    }

    @Override
    public boolean requirePrefix() {
        return true;
    }
}
