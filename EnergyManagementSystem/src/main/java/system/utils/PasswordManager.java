package system.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class PasswordManager {
    public static String encode(String password) throws NoSuchAlgorithmException {

        // code copied from Javatpoint, we used MD5

        /* MessageDigest instance for MD5. */
        MessageDigest m = MessageDigest.getInstance("MD5");

        /* Add plain-text password bytes to digest using MD5 update() method. */
        m.update(password.getBytes());

        /* Convert the hash value into bytes */
        byte[] bytes = m.digest();

        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        /* Complete hashed password in hexadecimal format */
        return s.toString();
    }

    // Verify user given password with user encoded password
    public static boolean verify(String decoded, String encoded) {
        try {

            return encode(decoded).equals(encoded);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

/*
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String en = PasswordManager.encode("kamrul");
        System.out.println(en);
        System.out.println(verify("kamrul1",en));
    }*/
}
