package me.rotn.java.skypenet;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static java.awt.event.KeyEvent.*;

public class Keyboard {

    private Robot robot;

    public Keyboard() throws AWTException {
        this(new Robot());
    }

    public Keyboard(Robot robot) {
        this.robot = robot;
    }

    public void type(String message) {
        StringSelection selection = new StringSelection(message);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        robot.keyPress(VK_CONTROL);
        robot.keyPress(VK_V);
        robot.keyRelease(VK_V);
        robot.keyRelease(VK_CONTROL);
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }
}