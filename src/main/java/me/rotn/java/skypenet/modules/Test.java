package me.rotn.java.skypenet.modules;

import com.skype.ChatMessage;
import me.rotn.java.skypenet.Keyboard;
import me.rotn.java.skypenet.framework.vilsol.Command;

public class Test {

    @Command(name = "echo")
    public static void lol(ChatMessage msg, String echo) {
        Keyboard.type(echo);
    }
}
