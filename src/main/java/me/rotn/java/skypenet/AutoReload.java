package me.rotn.java.skypenet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class AutoReload extends Thread {

    private String lastHash = getMD5Hash(getJarName());

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            String newHash = getMD5Hash(getJarName());
            if (!newHash.equals(lastHash)) {
                SkypeNet.getKeyboard().type("/me Updated JAR detected. Restarting.");
                System.exit(0);
            }
        }
    }

    private String getMD5Hash(String file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get(file)));
            return Arrays.toString(md.digest());
        } catch (Exception ignored) {
        }
        return null;
    }

    private String getJarName() {
        return new File(SkypeNet.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
    }

}
