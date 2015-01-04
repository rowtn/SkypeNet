package me.rotn.java.skypenet.commands;

import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.IBotCommand;
import org.apache.commons.lang.StringUtils;

public class CommandCleverbot implements IBotCommand {

    private ChatterBotSession bot;

    public CommandCleverbot() {
        try {
            bot = new ChatterBotFactory().create(ChatterBotType.CLEVERBOT).createSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String command(String[] args) {
        if (args.length < 1) return "Please enter a message!";
        if (args[0].equalsIgnoreCase("restart")) {
            try {
                bot = new ChatterBotFactory().create(ChatterBotType.CLEVERBOT).createSession();
                return "Restarting!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Restart failed!";
            }
        }
        try {
            return bot.think(StringUtils.join(args, " "));
        } catch (Exception e) {
            e.printStackTrace();
            return "There was an error sending/recieving the message.";
        }
    }

    @Override
    public String name() {
        return "c";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.NORMAL;
    }
}
