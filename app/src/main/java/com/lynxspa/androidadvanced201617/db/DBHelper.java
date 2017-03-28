package com.lynxspa.androidadvanced201617.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lynxspa.androidadvanced201617.Beacon.BeaconList;
import com.lynxspa.androidadvanced201617.Wifi.WifiList;
import com.lynxspa.androidadvanced201617.map.Mappa;
import com.lynxspa.androidadvanced201617.nfc.NFC;
import com.lynxspa.androidadvanced201617.profile.Profilo;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBProfiles.db";
    public static final String PROFILES_TABLE_NAME = "Profiles";
    public static final String PROFILES_COLUMN_ID = "id";
    public static final String NAME_PROFILE = "name";
    public static final String RADIO_BUTTON_VALUE = "radioButton";
    public static final String BRIGHTNESS_BAR_VALUE = "brightnessBar";
    public static final String CHECK_BRIGHTNESS = "brightnessCheckBox";
    public static final String BRIGHTNESS_VOLUME_RING = "volumeRing";
    public static final String BRIGHTNESS_VOLUME_MUSIC = "volumeMusic";
    public static final String BRIGHTNESS_VOLUME_NOTIFICATION = "volumeNotification";
    public static final String BLUETOOTH_SWITCH = "bluetoothSwitch";
    public static final String WIFI_SWITCH = "wifiSwitch";
    public static final String COLUMN_ID = "id";
    public static final String MAPS_TABLE_NAME="Maps";
    public static final String NAME_MAP="Map";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String WIFI_TABLE_NAME="Maps";
    public static final String WIFI_SSID = "wifiSSID";
    public static final String WIFI_BSSID = "wifiBssid";
    public static final String WIFI_SIGNAL = "wifiSignal";
    public static final String NFC_TABLE_NAME = "NFC";
    public static final String NFC_TAG_ID = "nfcTagId";
    public static final String NFC_TAG_NAME = "nfcTagName";
    public static final String BEACON_TABLE_NAME = "BeaconList";
    public static final String BEACON_NAME = "beaconName";
    public static final String BEACON_ID = "beaconId";
    public static final String BEACON_SIGNAL = "beaconSignal";
    public static final String APP_NAME="appName";
    public static final String PROFILE_ID="profileId";

    public static int VERSION_DB=1;

    private static DBHelper newInstance;
    private DBHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION_DB);
    }

    public static DBHelper getInstance(Context context){
        if(newInstance==null){
            newInstance = new DBHelper(context);
        }
        return newInstance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE "+ PROFILES_TABLE_NAME +" " +"("
                          + PROFILES_COLUMN_ID +" INTEGER PRIMARY KEY," +
                        ""+ NAME_PROFILE +" TEXT," +
                        ""+ RADIO_BUTTON_VALUE +" INTEGER," +
                        ""+ BRIGHTNESS_BAR_VALUE +" INTEGER," +
                        ""+ CHECK_BRIGHTNESS +" INTEGER," +
                        ""+ BRIGHTNESS_VOLUME_RING +" INTEGER," +
                        ""+ BRIGHTNESS_VOLUME_MUSIC +" INTEGER," +
                        ""+ BRIGHTNESS_VOLUME_NOTIFICATION+" INTEGER," +
                        ""+ BLUETOOTH_SWITCH +" INTEGER," +
                        ""+ WIFI_SWITCH +" INTEGER,"+
                        ""+ APP_NAME +" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion < 2) {
            db.execSQL(
                    "CREATE TABLE "+ MAPS_TABLE_NAME +" " +"("
                            + COLUMN_ID +" INTEGER PRIMARY KEY," +
                            ""+ NAME_MAP+" TEXT," +
                            ""+ LONGITUDE+" TEXT," +
                            ""+ LATITUDE+" TEXT," +
                            ""+ PROFILE_ID +" INTEGER,"+
                            " FOREIGN KEY ("+PROFILE_ID+") REFERENCES "+PROFILES_TABLE_NAME+"("+PROFILES_COLUMN_ID+"));"
            );
            db.execSQL(
                    "CREATE TABLE "+ WIFI_TABLE_NAME +" " +"("
                            + COLUMN_ID +" INTEGER PRIMARY KEY," +
                            ""+ WIFI_SSID+" TEXT," +
                            ""+ WIFI_BSSID+" TEXT," +
                            ""+ WIFI_SIGNAL+" TEXT," +
                            ""+ PROFILE_ID +" INTEGER,"+
                            " FOREIGN KEY ("+PROFILE_ID+") REFERENCES "+PROFILES_TABLE_NAME+"("+PROFILES_COLUMN_ID+"));"
            );
            db.execSQL(
                    "CREATE TABLE "+ NFC_TABLE_NAME +" " +"("
                            + COLUMN_ID +" INTEGER PRIMARY KEY," +
                            ""+ NFC_TAG_NAME+" TEXT," +
                            ""+ NFC_TAG_ID+" TEXT," +
                            ""+ PROFILE_ID +" INTEGER,"+
                            " FOREIGN KEY ("+PROFILE_ID+") REFERENCES "+PROFILES_TABLE_NAME+"("+PROFILES_COLUMN_ID+"));"
            );
            db.execSQL(
                    "CREATE TABLE "+ BEACON_TABLE_NAME +" " +"("
                            + COLUMN_ID +" INTEGER PRIMARY KEY," +
                            ""+ BEACON_NAME+" TEXT," +
                            ""+ BEACON_ID+" TEXT," +
                            ""+ BEACON_SIGNAL+" TEXT," +
                            ""+ PROFILES_COLUMN_ID +" INTEGER,"+
                            " FOREIGN KEY ("+PROFILE_ID+") REFERENCES "+PROFILES_TABLE_NAME+"("+PROFILES_COLUMN_ID+"));"
            );
            VERSION_DB=newVersion;
        }

        if(newVersion<3){
            //TODO do something onUpgrade
            VERSION_DB=newVersion;
        }
    }

    public boolean insertOrUpdateProfile (Profilo profilo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] raw=new byte[16];
       // ProfiloEncrypted profiloEncrypted=null;
        try {
           // profiloEncrypted = EncryptDecryptClass.encrypt(raw, profilo);
            if(getProfileById(profilo.getId()) == null){
                contentValues.put(NAME_PROFILE, profilo.getName());
                contentValues.put(RADIO_BUTTON_VALUE, profilo.getRadioButton());
                contentValues.put(BRIGHTNESS_BAR_VALUE, profilo.getBrigthnesBar());
                contentValues.put(CHECK_BRIGHTNESS, profilo.getBrightnessCheckBox());
                contentValues.put(BRIGHTNESS_VOLUME_RING, profilo.getVolumeBarRing());
                contentValues.put(BRIGHTNESS_VOLUME_MUSIC, profilo.getVolumeBarMusic());
                contentValues.put(BRIGHTNESS_VOLUME_NOTIFICATION, profilo.getVolumeBarNotification());
                contentValues.put(BLUETOOTH_SWITCH, profilo.getBluetoothSwitch());
                contentValues.put(WIFI_SWITCH, profilo.getWifiSwitch());
                contentValues.put(APP_NAME, profilo.getAppName());
                db.insert(PROFILES_TABLE_NAME, null, contentValues);
            }else if(getProfileById(profilo.getId()) != null){
                contentValues.put(NAME_PROFILE, profilo.getName());
                contentValues.put(RADIO_BUTTON_VALUE, profilo.getRadioButton());
                contentValues.put(BRIGHTNESS_BAR_VALUE, profilo.getBrigthnesBar());
                contentValues.put(CHECK_BRIGHTNESS, profilo.getBrightnessCheckBox());
                contentValues.put(BRIGHTNESS_VOLUME_RING, profilo.getVolumeBarRing());
                contentValues.put(BRIGHTNESS_VOLUME_MUSIC, profilo.getVolumeBarMusic());
                contentValues.put(BRIGHTNESS_VOLUME_NOTIFICATION, profilo.getVolumeBarNotification());
                contentValues.put(BLUETOOTH_SWITCH, profilo.getBluetoothSwitch());
                contentValues.put(WIFI_SWITCH, profilo.getWifiSwitch());
                contentValues.put(APP_NAME, profilo.getAppName());
                db.update(PROFILES_TABLE_NAME, contentValues, PROFILES_COLUMN_ID+"= " + profilo.getId(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean insertOrUpdateMap (Mappa mappa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] raw=new byte[16];
        //MapEncrypted mapEncrypted=null;
        try {
            //mapEncrypted = EncryptDecryptClass.encrypt(raw, mappa);
            if(getMapByName(mappa.getName()) == null){
                contentValues.put(NAME_MAP, mappa.getName());
                contentValues.put(LONGITUDE, mappa.getLon());
                contentValues.put(LATITUDE, mappa.getLat());
                contentValues.put(PROFILE_ID, mappa.getProfileId());
                db.insert(WIFI_TABLE_NAME, null, contentValues);
            }else if(getMapByName(mappa.getName()) != null){
                contentValues.put(NAME_MAP, mappa.getName());
                contentValues.put(LONGITUDE, mappa.getLon());
                contentValues.put(LATITUDE, mappa.getLat());
                contentValues.put(PROFILE_ID, mappa.getProfileId());
                db.update(MAPS_TABLE_NAME, contentValues, NAME_MAP+"= " + mappa.getName(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean insertOrUpdateWifiList (WifiList wifiList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] raw=new byte[16];
        //WifiEncrypted wifiEncrypted=null;
        try {
            //wifiEncrypted = EncryptDecryptClass.encrypt(raw, wifiList);
            if(getWifiByBssid(wifiList.getBssid()) == null){
                contentValues.put(WIFI_SSID, wifiList.getSsid());
                contentValues.put(WIFI_BSSID, wifiList.getBssid());
                contentValues.put(WIFI_SIGNAL, wifiList.getSignal());
                contentValues.put(PROFILE_ID, wifiList.getProfileId());
                db.insert(WIFI_TABLE_NAME, null, contentValues);
            }else if(getWifiByBssid(wifiList.getBssid()) != null){
                contentValues.put(WIFI_SSID, wifiList.getSsid());
                contentValues.put(WIFI_BSSID, wifiList.getBssid());
                contentValues.put(WIFI_SIGNAL, wifiList.getSignal());
                contentValues.put(PROFILE_ID, wifiList.getProfileId());
                db.update(WIFI_TABLE_NAME, contentValues, WIFI_BSSID+"= " + wifiList.getBssid(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean insertOrUpdateBeacons (BeaconList beacon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] raw=new byte[16];
        //BeaconEncrypted wifiEncrypted=null;
        try {
            //beaconEncrypted = EncryptDecryptClass.encrypt(raw, beacon);
            if(getBeaconByName(beacon.getNameBeacon()) == null){
                contentValues.put(BEACON_NAME, beacon.getNameBeacon());
                contentValues.put(BEACON_ID, beacon.getAddressBeacon());
                contentValues.put(BEACON_SIGNAL, beacon.getDistanceBeacon());
                contentValues.put(PROFILE_ID, beacon.getProfileId());
                db.insert(BEACON_TABLE_NAME, null, contentValues);
            }else if(getBeaconByName(beacon.getNameBeacon()) != null){
                contentValues.put(BEACON_NAME, beacon.getNameBeacon());
                contentValues.put(BEACON_ID, beacon.getAddressBeacon());
                contentValues.put(BEACON_SIGNAL, beacon.getDistanceBeacon());
                contentValues.put(PROFILE_ID, beacon.getProfileId());
                db.update(BEACON_TABLE_NAME, contentValues, BEACON_ID+"= " + beacon.getAddressBeacon(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean insertOrUpdateNfc (NFC nfc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        byte[] raw=new byte[16];
        //NfcEncrypted nfcEncrypted=null;
        try {
            //nfcEncrypted = EncryptDecryptClass.encrypt(raw, nfc);
            if(getNfcById(nfc.getTagId()) == null){
                contentValues.put(NFC_TAG_NAME, nfc.getTagName());
                contentValues.put(NFC_TAG_ID, nfc.getTagId());
                contentValues.put(PROFILE_ID, nfc.getProfileId());
                db.insert(NFC_TABLE_NAME, null, contentValues);
            }else if(getNfcById(nfc.getTagId()) != null){
                contentValues.put(NFC_TAG_NAME, nfc.getTagName());
                contentValues.put(NFC_TAG_ID, nfc.getTagId());
                contentValues.put(PROFILE_ID, nfc.getProfileId());
                db.update(NFC_TABLE_NAME, contentValues, NFC_TAG_ID+"= " + nfc.getTagId(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public int deleteProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(PROFILES_TABLE_NAME, PROFILES_COLUMN_ID + "=" + id, null);
    }

    public Profilo getProfileById(int id) {
        List<Profilo> profileList = getProfilesBySql("SELECT * FROM " + PROFILES_TABLE_NAME + " WHERE " + PROFILES_COLUMN_ID + "= " + id);

        if (profileList != null && profileList.size() > 0) {
            return profileList.get(0);
        }
        return null;
    }

    public Mappa getMapByName(String name) {
        List<Mappa> maps= getMapsBySql("SELECT * FROM " + MAPS_TABLE_NAME + " WHERE " + NAME_MAP + "= " + name);

        if (maps != null && maps.size() > 0) {
            return maps.get(0);
        }
        return null;
    }

    public WifiList getWifiByBssid(String bssid) {
        List<WifiList> wifiList= getWifiBySql("SELECT * FROM " + WIFI_TABLE_NAME + " WHERE " + WIFI_BSSID + "= " + bssid);

        if (wifiList != null && wifiList.size() > 0) {
            return wifiList.get(0);
        }
        return null;
    }

    public NFC getNfcById(String tagId) {
        List<NFC> nfcs= getNfcsBySql("SELECT * FROM " + NFC_TABLE_NAME + " WHERE " + NFC_TAG_ID + "= " + tagId);

        if (nfcs != null && nfcs.size() > 0) {
            return nfcs.get(0);
        }
        return null;
    }

    public BeaconList getBeaconByName(String name) {
        List<BeaconList> beacons= getBeaconsBySql("SELECT * FROM " + BEACON_TABLE_NAME + " WHERE " + BEACON_NAME + "= " + name);

        if (beacons != null && beacons.size() > 0) {
            return beacons.get(0);
        }
        return null;
    }

    public List<Profilo> getAllProfiles() {
        return getProfilesBySql("SELECT * FROM "+PROFILES_TABLE_NAME);
    }

    private List<Profilo> getProfilesBySql(String sqlQuery) {
        List<Profilo> array_list = new ArrayList<Profilo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(sqlQuery , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Profilo profilo=new Profilo(
                    res.getInt(res.getColumnIndex(PROFILES_COLUMN_ID)),
                    res.getString(res.getColumnIndex(NAME_PROFILE)),
                    res.getInt(res.getColumnIndex(RADIO_BUTTON_VALUE)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_BAR_VALUE)),
                    res.getInt(res.getColumnIndex(CHECK_BRIGHTNESS)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_VOLUME_RING)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_VOLUME_MUSIC)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_VOLUME_NOTIFICATION)),
                    res.getInt(res.getColumnIndex(BLUETOOTH_SWITCH)),
                    res.getInt(res.getColumnIndex(WIFI_SWITCH)),
                    res.getString(res.getColumnIndex(APP_NAME)));

            array_list.add(profilo);
            res.moveToNext();
        }
        return array_list;
    }

    private List<Mappa> getMapsBySql(String sqlQuery) {
        List<Mappa> array_list = new ArrayList<Mappa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(sqlQuery , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Mappa wifi=new Mappa(
                    res.getString(res.getColumnIndex(NAME_MAP)),
                    res.getString(res.getColumnIndex(LONGITUDE)),
                    res.getString(res.getColumnIndex(LATITUDE)),
                    res.getInt(res.getColumnIndex(PROFILE_ID)));
            array_list.add(wifi);
            res.moveToNext();
        }
        return array_list;
    }

    private List<WifiList> getWifiBySql(String sqlQuery) {
        List<WifiList> array_list = new ArrayList<WifiList>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(sqlQuery , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            WifiList wifi=new WifiList(
                    res.getString(res.getColumnIndex(WIFI_SSID)),
                    res.getString(res.getColumnIndex(WIFI_BSSID)),
                    res.getString(res.getColumnIndex(WIFI_SIGNAL)),
                    res.getInt(res.getColumnIndex(PROFILE_ID)));
            array_list.add(wifi);
            res.moveToNext();
        }
        return array_list;
    }

    private List<NFC> getNfcsBySql(String sqlQuery) {
        List<NFC> array_list = new ArrayList<NFC>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(sqlQuery , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            NFC nfc=new NFC(
                    res.getString(res.getColumnIndex(NFC_TAG_NAME)),
                    res.getString(res.getColumnIndex(NFC_TAG_ID)),
                    res.getInt(res.getColumnIndex(PROFILE_ID)));
            array_list.add(nfc);
            res.moveToNext();
        }
        return array_list;
    }

    private List<BeaconList> getBeaconsBySql(String sqlQuery) {
        List<BeaconList> array_list = new ArrayList<BeaconList>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(sqlQuery , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            BeaconList beacon=new BeaconList(
                    res.getString(res.getColumnIndex(BEACON_NAME)),
                    res.getString(res.getColumnIndex(BEACON_ID)),
                    res.getString(res.getColumnIndex(BEACON_SIGNAL)),
                    res.getInt(res.getColumnIndex(PROFILE_ID)));
            array_list.add(beacon);
            res.moveToNext();
        }
        return array_list;
    }

}
