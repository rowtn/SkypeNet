package me.rotn.java.skypenet.commands;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.framework.IBotCommand;

import java.util.Random;

public class CommandEightBall implements IBotCommand {

    @Override
    public String command(ChatMessage chatMessage, String[] args) {
        String[] options = new String[] {"It is certain", "It is decidedly so", "Without a doubt", "Yes definitely", "You may rely on it", "As I see it, yes", "Most likely", "Outlook good", "Yes", "Signs point to yes", "Reply hazy try again", "Ask again later", "Better not tell you now", "Cannot predict now", "Concentrate and ask again", "Don't count on it", "My reply is no", "My sources say no", "Outlook not so good", "Very doubtful"};
        int chosen = new Random().nextInt(options.length);
        return options[chosen];
    }

    @Override
    public String name() {
        return "8ball";
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
