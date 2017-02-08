package com.lynxspa.androidadvanced201617;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Esami on 19/01/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBProfiles.db";
    public static final String PROFILES_TABLE_NAME = "Profiles";
    public static final String PROFILES_COLUMN_ID = "id";
    public static final String NAME_PROFILE = "name";
    public static final String RADIO_BUTTON_VALUE = "radioButton";
    public static final String BRIGHTNESS_BAR_VALUE = "brightnessBar";
    public static final String CHECK_BRIGHTNESS = "brightnessCheckBox";
    public static final String BRIGHTNESS_VOLUME = "volumeBar";
    public static final String BLUETOOTH_SWITCH = "bluetoothSwitch";
    public static final String WIFI_SWITCH = "wifiSwitch";

    public static final int VERSION_DB=1;
    private static final int VER_SESSION_FEEDBACK_URL = 2;
    private static final int VER_SESSION_NOTES_URL_SLUG = 3;

    private static final int DATABASE_CURRENT_VERSION = VER_SESSION_NOTES_URL_SLUG;

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
                "CREATE TABLE Profiles " +
                        "(id INTEGER PRIMARY KEY, name TEXT,radioButton INTEGER,brightnessBar INTEGER," +
                        " brightnessCheckBox INTEGER,volumeBar INTEGER,bluetoothSwitch INTEGER, wifiSwitch INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version=oldVersion;
        if(VERSION_DB==version){
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN "
                        + "(id INTEGER PRIMARY KEY, name TEXT,radioButton INTEGER,brightnessBar INTEGER," +
                        " brightnessCheckBox INTEGER,volumeBar INTEGER,bluetoothSwitch INTEGER, wifiSwitch INTEGER)");
                version = VER_SESSION_FEEDBACK_URL;
        }else{
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN "
                        + "(id INTEGER PRIMARY KEY, name TEXT,radioButton INTEGER,brightnessBar INTEGER," +
                        " brightnessCheckBox INTEGER,volumeBar INTEGER,bluetoothSwitch INTEGER, wifiSwitch INTEGER)");
                db.execSQL("ALTER TABLE " + PROFILES_TABLE_NAME + " ADD COLUMN "
                        + "(id INTEGER PRIMARY KEY, name TEXT,radioButton INTEGER,brightnessBar INTEGER," +
                        " brightnessCheckBox INTEGER,volumeBar INTEGER,bluetoothSwitch INTEGER, wifiSwitch INTEGER)");
                version = VER_SESSION_NOTES_URL_SLUG;
        }

    }


    public boolean insertOrUpdateProfile (Profilo profilo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(getData(profilo.getId())==null){
            contentValues.put(NAME_PROFILE, profilo.getName());
            contentValues.put(RADIO_BUTTON_VALUE, profilo.getRadioGroup());
            contentValues.put(BRIGHTNESS_BAR_VALUE, profilo.getBrigthnesBar());
            contentValues.put(CHECK_BRIGHTNESS, profilo.getBrightnessCheckBox());
            contentValues.put(BRIGHTNESS_VOLUME, profilo.getVolumeBar());
            contentValues.put(BLUETOOTH_SWITCH, profilo.getBluetoothSwitch());
            contentValues.put(WIFI_SWITCH, profilo.getWifiSwitch());
            db.insert(PROFILES_TABLE_NAME, null, contentValues);
        }else if(getData(profilo.getId())!=null){
            contentValues.put(NAME_PROFILE, profilo.getName());
            contentValues.put(RADIO_BUTTON_VALUE, profilo.getRadioGroup());
            contentValues.put(BRIGHTNESS_BAR_VALUE, profilo.getBrigthnesBar());
            contentValues.put(CHECK_BRIGHTNESS, profilo.getBrightnessCheckBox());
            contentValues.put(BRIGHTNESS_VOLUME, profilo.getVolumeBar());
            contentValues.put(BLUETOOTH_SWITCH, profilo.getBluetoothSwitch());
            contentValues.put(WIFI_SWITCH, profilo.getWifiSwitch());
            db.update(PROFILES_TABLE_NAME, contentValues, "id = " + profilo.getId(), new String[]{Integer.toString(profilo.getId())});
        }
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM Profiles WHERE id=" + id + "", null);
        return res;
    }

    public Integer deleteProfile(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PROFILES_TABLE_NAME, PROFILES_COLUMN_ID+"= ? ", new String[] { Integer.toString(id) });
    }

    public ArrayList<Profilo> getAllProfiles() {
        ArrayList<Profilo> array_list = new ArrayList<Profilo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT name FROM Profiles", null );
        res.moveToFirst();


        while(res.isAfterLast() == false){
            Profilo profilo=new Profilo(
                    res.getColumnIndex(PROFILES_COLUMN_ID),
                    res.getString(res.getColumnIndex(NAME_PROFILE)),
                    res.getInt(res.getColumnIndex(RADIO_BUTTON_VALUE)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_BAR_VALUE)),
                    res.getInt(res.getColumnIndex(CHECK_BRIGHTNESS)),
                    res.getInt(res.getColumnIndex(BRIGHTNESS_VOLUME)),
                    res.getInt(res.getColumnIndex(BLUETOOTH_SWITCH)),
                    res.getInt(res.getColumnIndex(WIFI_SWITCH)));
            array_list.add(profilo);
            res.moveToNext();
        }
        return array_list;
    }

}
