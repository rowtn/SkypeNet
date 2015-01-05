package me.rotn.java.skypenet.framework.vilsol;

import com.skype.ChatMessage;
import com.skype.SkypeException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Utils {

    public static boolean isInteger(Object s){
        try{
            Integer.parseInt(s.toString());
            return true;
        }catch(Exception ignore){
        }

        return false;
    }

    public static boolean isDouble(Object s){
        try{
            Double.parseDouble(s.toString());
            return true;
        }catch(Exception ignore){
        }

        return false;
    }

    public static String serializeMessage(ChatMessage message){
        String s = "";

        try{
            s += "[" + message.getTime().toString() + "] " + message.getSenderDisplayName() + ": " + message.getContent();
        }catch(SkypeException ignored){
        }

        return s;
    }

    public static String upload(List<ChatMessage> s){
        String data = "";
        for(ChatMessage m : s){
            data += serializeMessage(m) + "\n";
        }
        return upload(data);
    }

    public static String upload(String s){
        try{
            URL url = new URL("http://paste.ubuntu.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(true);
            String postData = "content=" + URLEncoder.encode(s, "UTF-8") + "&poster=Skype Bot&syntax=text";
            con.setRequestProperty("Content-length", String.valueOf(postData.length()));
            con.setDoOutput(true);
            con.setDoInput(true);
            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(postData);
            output.close();
            con.getResponseCode();
            return con.getURL().toString();
        }catch(java.io.IOException ignored){
        }
        return null;
    }
}
