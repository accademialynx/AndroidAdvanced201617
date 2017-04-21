package com.lynxspa.androidadvanced201617.utils;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Esami on 21/04/2017.
 */
public class HashChallengePassword {

    private static final String initialVector="minitialvectoriv";

    public static String hashPassword(String password)throws Exception{
        String hashedPassword="";
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        hashedPassword=String.format("%064x", new java.math.BigInteger(1, digest)).substring(0, 16);
        return hashedPassword;
    }

    public static byte[] encrypt(String risultato, String hash)throws Exception{
        SecretKeySpec skeySpec = new SecretKeySpec(hash.getBytes("UTF-8"), "AES");
        IvParameterSpec iv=new IvParameterSpec(initialVector.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
        byte[] challengeCrypt=cipher.doFinal(risultato.getBytes());
        return challengeCrypt;
    }

    public static byte[] decrypt(byte[] risultato, String hash)throws Exception{
        SecretKeySpec skeySpec = new SecretKeySpec(hash.getBytes("UTF-8"), "AES");
        IvParameterSpec iv=new IvParameterSpec(initialVector.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
        String s = new String(risultato, "US-ASCII");
        byte[] challengeDecrypt=cipher.doFinal(s.getBytes());
        return challengeDecrypt;
    }
}
