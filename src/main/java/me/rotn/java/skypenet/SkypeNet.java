package me.rotn.java.skypenet;

import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Skype;
import com.skype.SkypeException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class SkypeNet {

    private final boolean CHATBOT_TROLL = false;
    private static final String COMMAND_PREFIX = ">>";
    private static final boolean DEBUG = true;

    private Map<String, IBotCommand> commands = new LinkedHashMap<>();
    private Keyboard keyboard;
    private Map<String, ChatterBotSession> cleverBots = new HashMap<>();
    private ChatterBotFactory botFactory = new ChatterBotFactory();
    private static SkypeNet instance;

    public static void main(String[] args) throws SkypeException, IllegalAccessException, InstantiationException {
        new SkypeNet();
    }

    private SkypeNet() throws SkypeException, InstantiationException, IllegalAccessException {
        try {
            keyboard = new Keyboard();
        } catch (AWTException e) {
            System.out.println("Couldn't create Virtual Keyboard! (Is AWT supported in your JRE?)");
            e.printStackTrace();
            System.exit(1);
        }
        loadCommands();
        //loadExternalModules();
        System.out.println("Loaded!");
        instance = this;
        Skype.setDaemon(false);
        Skype.addChatMessageListener(new ChatMessageAdapter() {
            @Override
            public void chatMessageReceived(ChatMessage chatMessage) {
                try {
                    handle(chatMessage);
                    if (DEBUG) {
                        System.out.printf("[%s] %s", chatMessage.getSender().getDisplayName(), chatMessage.getContent());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void chatMessageSent(ChatMessage chatMessage) throws SkypeException {
            }
        });
    }

    public static SkypeNet getInstance() {
        return instance;
    }

    //TODO: Make this actually work
   /* private void loadExternalModules()  {
        File modulesFolder = new File("./modules/");
        if (!modulesFolder.exists()) {
            modulesFolder.mkdirs();
        }
        if (modulesFolder.listFiles().length < 1) {
            System.out.println("No external modules detected.");
            return;
        }
        for (File f : modulesFolder.listFiles()) {
            try {
                if (f.getCanonicalPath().endsWith(",jar")) {
                    JarFile moduleJar = new JarFile(f);
                    Enumeration entries = moduleJar.entries();
                    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {f.toURI().toURL()});
                    while (entries.hasMoreElements()) {
                        JarEntry jarEntry = (JarEntry) entries.nextElement();
                        if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) continue;
                        String className = jarEntry.getName().substring(0,jarEntry.getName().length()-6);
                        className = className.replace('/', '.');
                        try {
                            Class<?> clazz = classLoader.loadClass(className);
                            try {
                                this.registerCommand(clazz);
                            } catch (IllegalAccessException | InstantiationException e) {
                                System.out.println("Could not register command. Command class: " + clazz.getCanonicalName());
                                e.printStackTrace();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Couldn't load module: " + f.getName());
                e.printStackTrace();
            }
        }
    }*/

    private void handle(ChatMessage message) throws Exception {
        if (!CHATBOT_TROLL) {
            try {
                String[] args = message.getContent().split(" ");
                if (commands.containsKey(args[0])) {
                    String response = commands.get(args[0]).command(Arrays.copyOfRange(args, 1, args.length));
                    keyboard.type(response);
                    if (DEBUG) System.out.println(response);
                }
            } catch (SkypeException e) {
                System.out.println("invalid message " + message);
                e.printStackTrace();
            }
        } else {
            ChatterBotSession bot;
            if (!cleverBots.containsKey(message.getSenderId()))
                cleverBots.put(message.getSenderId(), botFactory.create(ChatterBotType.CLEVERBOT).createSession());
            bot = cleverBots.get(message.getSenderId());
            keyboard.type(bot.think(
                    message.
                            getContent()));
        }
    }

    private void loadCommands() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = "me/rotn/java/skypenet/commands";
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid package name: " + path);
        }
        List<Class<?>> classes = new LinkedList<>();
        while (resources.hasMoreElements()) {
            File f = new File(resources.nextElement().getFile());
            classes.addAll(find(f, path));
        }

        for (Class<?> clazz : classes) {
            try {
                registerCommand(clazz);
            } catch (ReflectiveOperationException e) {
                System.out.println("Could not register command: " + clazz.getCanonicalName());
            }
        }
    }

    private static List<Class<?>> find(final File file, final String scannedPackage) {
        List<Class<?>> classes = new LinkedList<>();
        String resource = scannedPackage + "." + file.getName();
        if (file.isDirectory()) {
            for (File nestedFile : file.listFiles()) {
                classes.addAll(find(nestedFile, scannedPackage));
            }
        } else if (resource.endsWith(".class")) {
            String className = resource.substring(0, resource.lastIndexOf(".class")).replaceAll("/", ".");
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
                ignore.printStackTrace();
            }
        }
        return classes;
    }

    private void registerCommand(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isInterface()) return;
        if (IBotCommand.class.isAssignableFrom(clazz)) {
            IBotCommand command = (IBotCommand) clazz.newInstance();
            if (commands.containsKey(command.name())) {
                if (commands.get(command.name()).getPriority().moreImportantThan(command.getPriority())) return;
            }
            commands.put(String.format("%s%s", COMMAND_PREFIX, command.name()), command);
        }
    }

    public Map<String, IBotCommand> getCommands() {
        return commands;
    }

}
