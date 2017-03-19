package com.lynxspa.androidadvanced201617.dbDir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lynxspa.androidadvanced201617.profileDir.Profilo;

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
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String NFC_TAG_ID = "nfcTagId";
    public static final String WIFI_SSID = "wifiSSID";
    public static final String BEACON_ID = "beaconId";
    public static final String APP_NAME="appName";

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
                        ""+ WIFI_SWITCH +" INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            if((LATITUDE!=null && !LATITUDE.isEmpty()) && (LONGITUDE!=null && !LONGITUDE.isEmpty())) {
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + LONGITUDE + " DOUBLE");
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + LATITUDE + " DOUBLE");
                VERSION_DB=newVersion;
            }
            if((WIFI_SSID!=null && !WIFI_SSID.isEmpty())) {
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + WIFI_SSID + " TEXT");
                VERSION_DB=newVersion;
            }
            if((NFC_TAG_ID!=null && !NFC_TAG_ID.isEmpty())) {
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + NFC_TAG_ID + " TEXT");
                VERSION_DB=newVersion;
            }
            if((BEACON_ID!=null && !BEACON_ID.isEmpty())) {
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + BEACON_ID + " TEXT");
                VERSION_DB=newVersion;
            }
            if((APP_NAME!=null && !APP_NAME.isEmpty())) {
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN " + APP_NAME + " TEXT");
                VERSION_DB=newVersion;
            }
        }
    }

    public boolean insertOrUpdateProfile (Profilo profilo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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
            db.update(PROFILES_TABLE_NAME, contentValues, PROFILES_COLUMN_ID+"= " + profilo.getId(), null);
        }
        return true;
    }

    public int delete(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(PROFILES_TABLE_NAME, PROFILES_COLUMN_ID + "=" + id, null);
    }

    public Profilo getProfileById(int id) {
        List<Profilo> profileList = getProfilesBySql("SELECT * FROM "+PROFILES_TABLE_NAME+" WHERE "+PROFILES_COLUMN_ID+"= "+ id);

        if (profileList != null && profileList.size() > 0) {
            return profileList.get(0);
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
                    res.getInt(res.getColumnIndex(WIFI_SWITCH)));
            array_list.add(profilo);
            res.moveToNext();
        }
        return array_list;
    }

}
