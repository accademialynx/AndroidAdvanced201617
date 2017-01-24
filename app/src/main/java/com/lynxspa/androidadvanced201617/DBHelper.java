package com.lynxspa.androidadvanced201617;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

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
        db.execSQL("DROP TABLE IF EXISTS Profiles");
        onCreate(db);
    }

    public boolean addProfile (String nameProfile, RadioGroup button, SeekBar brightnessBar, CheckBox brightnessCheckBox,
                               SeekBar volumeBar,Switch bluetoothSwitch, Switch wifiSwitch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_PROFILE, nameProfile);
        contentValues.put(RADIO_BUTTON_VALUE, button.getCheckedRadioButtonId());
        contentValues.put(BRIGHTNESS_BAR_VALUE, brightnessBar.getProgress());
        contentValues.put(CHECK_BRIGHTNESS, brightnessCheckBox.getId());
        contentValues.put(BRIGHTNESS_VOLUME, volumeBar.getProgress());
        contentValues.put(BLUETOOTH_SWITCH, bluetoothSwitch.getId());
        contentValues.put(WIFI_SWITCH, wifiSwitch.getId());
        db.insert(PROFILES_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateProfile (Integer id, String nameProfile, RadioGroup button, SeekBar brightnessBar, CheckBox brightnessCheckBox,
                                  SeekBar volumeBar,Switch bluetoothSwitch, Switch wifiSwitch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_PROFILE, nameProfile);
        contentValues.put(RADIO_BUTTON_VALUE, button.getCheckedRadioButtonId());
        contentValues.put(BRIGHTNESS_BAR_VALUE, brightnessBar.getProgress());
        contentValues.put(CHECK_BRIGHTNESS, brightnessCheckBox.getId());
        contentValues.put(BRIGHTNESS_VOLUME, volumeBar.getProgress());
        contentValues.put(BLUETOOTH_SWITCH, bluetoothSwitch.getId());
        contentValues.put(WIFI_SWITCH, wifiSwitch.getId());
        db.update(PROFILES_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
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

    public ArrayList<String> getAllProfiles() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT name FROM Profiles", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NAME_PROFILE)));
            res.moveToNext();
        }
        return array_list;
    }

}
