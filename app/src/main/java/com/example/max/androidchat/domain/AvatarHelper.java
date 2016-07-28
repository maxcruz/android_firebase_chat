package com.example.max.androidchat.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AvatarHelper {

    private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";

    public static String getAvatarUrl(String email) {
        return GRAVATAR_URL + md5(email) + "?s=72";
    }

    private static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

}
