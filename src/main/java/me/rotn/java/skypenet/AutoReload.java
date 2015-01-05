package me.rotn.java.skypenet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AutoReload extends Thread {

    @Override
    public void run() {
        String lastHash = getMD5Hash(getJarName());
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            String newHash = getMD5Hash(getJarName());
            if (!newHash.equals(lastHash)) {
                Keyboard.type("/me Updated JAR detected. Restarting.");
                System.exit(0);
            }
        }
    }

    private String getMD5Hash(Path file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(file));
            return Arrays.toString(md.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Path getJarName() {
        return new File(SkypeNet.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toPath();
    }

}
