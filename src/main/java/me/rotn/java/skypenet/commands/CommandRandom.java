package me.rotn.java.skypenet.commands;

import me.rotn.java.skypenet.CommandPriority;
import me.rotn.java.skypenet.IBotCommand;

import java.security.SecureRandom;
import java.util.Random;

public class CommandRandom implements IBotCommand {

    private Random random = new SecureRandom();

    @Override
    public String command(String[] args) {
        if (args.length < 1) return "Invalid Arguments. Required 'min value' 'max value' ";
        int min, max;
        try {
            min = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return args[0] + " is not a number!";
        }
        try {
            max = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return args[1] + " is not a number!";
        }
        if (min == max) {
            return "The numbers cannot be equal.";
        }
        if (min > max) {
            min ^= max;
            max ^= min;
            min ^= max;
        }
        return String.valueOf(random.nextInt(max - min) + min);
    }

    @Override
    public String name() {
        return "random";
    }

    @Override
    public CommandPriority getPriority() {
        return CommandPriority.NORMAL;
    }
}
