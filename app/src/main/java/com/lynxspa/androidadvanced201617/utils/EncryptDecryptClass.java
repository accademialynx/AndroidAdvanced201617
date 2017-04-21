package com.lynxspa.androidadvanced201617.utils;

import com.lynxspa.androidadvanced201617.profile.Profilo;
import com.lynxspa.androidadvanced201617.profile.ProfiloEncrypted;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Esami on 21/03/2017.
 */
public class EncryptDecryptClass {

    private static final String key="ciaocaprettaamic";
    private static final String initialVector="minitialvectoriv";
    public EncryptDecryptClass() {
    }

    public static ProfiloEncrypted encrypt(Profilo profilo) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec iv=new IvParameterSpec(initialVector.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] nameEncrypted = cipher.doFinal(profilo.getName().getBytes());
        byte[] radioButtonEncrypted = cipher.doFinal(String.valueOf(profilo.getRadioButton()).getBytes());
        byte[] brightnessEncrypted = cipher.doFinal(String.valueOf(profilo.getBrigthnesBar()).getBytes());
        byte[] brightnessCheckEncrypted = cipher.doFinal(String.valueOf(profilo.getBrightnessCheckBox()).getBytes());
        byte[] volumeRingEncrypted = cipher.doFinal(String.valueOf(profilo.getVolumeBarRing()).getBytes());
        byte[] volumeMusicEncrypted = cipher.doFinal(String.valueOf(profilo.getVolumeBarMusic()).getBytes());
        byte[] volumeNotificationEncrypted = cipher.doFinal(String.valueOf(profilo.getVolumeBarNotification()).getBytes());
        byte[] bluetoothSwitchEncrypted = cipher.doFinal(String.valueOf(profilo.getBluetoothSwitch()).getBytes());
        byte[] wifiSwitchEncrypted = cipher.doFinal(String.valueOf(profilo.getWifiSwitch()).getBytes());
        byte[] appEncrypted = cipher.doFinal(String.valueOf(profilo.getAppName()).getBytes());
        byte[] passwordEncrypted = cipher.doFinal(String.valueOf(profilo.getPassword()).getBytes());


        ProfiloEncrypted profiloEncrypted=new ProfiloEncrypted(profilo.getId(),nameEncrypted.toString(),radioButtonEncrypted.toString(),brightnessEncrypted.toString(),
                brightnessCheckEncrypted.toString(),volumeRingEncrypted.toString(),volumeMusicEncrypted.toString(),volumeNotificationEncrypted.toString(),
                bluetoothSwitchEncrypted.toString(),wifiSwitchEncrypted.toString(),appEncrypted.toString(),passwordEncrypted.toString());
        return profiloEncrypted;
    }

    public static Profilo decrypt(ProfiloEncrypted profiloEncrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec iv=new IvParameterSpec(initialVector.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] nameDecrypted = cipher.doFinal(profiloEncrypted.getName().getBytes());
        byte[] radioButtonDecrypted = cipher.doFinal(profiloEncrypted.getRadioButton().getBytes());
        byte[] brightnessDecrypted = cipher.doFinal(profiloEncrypted.getBrigthnesBar().getBytes());
        byte[] brightnessCheckDecrypted = cipher.doFinal(profiloEncrypted.getBrightnessCheckBox().getBytes());
        byte[] volumeRingDecrypted = cipher.doFinal(profiloEncrypted.getVolumeBarRing().getBytes());
        byte[] volumeMusicDecrypted = cipher.doFinal(profiloEncrypted.getVolumeBarMusic().getBytes());
        byte[] volumeNotificationDecrypted = cipher.doFinal(profiloEncrypted.getVolumeBarNotification().getBytes());
        byte[] bluetoothSwitchDecrypted = cipher.doFinal(profiloEncrypted.getBluetoothSwitch().getBytes());
        byte[] wifiSwitchDecrypted = cipher.doFinal(profiloEncrypted.getWifiSwitch().getBytes());
        byte[] appDecrypted = cipher.doFinal(String.valueOf(profiloEncrypted.getAppName()).getBytes());
        byte[] passwordDecrypt=cipher.doFinal(String.valueOf(profiloEncrypted.getPassword()).getBytes());

        Profilo profilo=new Profilo(profiloEncrypted.getId(),nameDecrypted.toString(), Integer.valueOf(radioButtonDecrypted.toString()),
                Integer.valueOf(brightnessDecrypted.toString()),Integer.valueOf(brightnessCheckDecrypted.toString()),
                Integer.valueOf(volumeRingDecrypted.toString()),Integer.valueOf(volumeMusicDecrypted.toString()),
                Integer.valueOf(volumeNotificationDecrypted.toString()),Integer.valueOf(bluetoothSwitchDecrypted.toString()),
                Integer.valueOf(wifiSwitchDecrypted.toString()),appDecrypted.toString(),passwordDecrypt.toString(),"");
        return profilo;
    }
}
