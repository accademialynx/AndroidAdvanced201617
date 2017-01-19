package com.lynxspa.androidadvanced201617.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lynxspa.androidadvanced201617.Utils.SurveyType;
import com.lynxspa.androidadvanced201617.data.Profile;

import java.util.ArrayList;

/**
 * Created by Samuele on 19/01/2017.
 */

public class ProfileSQLiteHelper extends SQLiteOpenHelper{

    private static  ProfileSQLiteHelper instance;

    public static final String DATABASE_NAME = "profile.db";
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_COLUMN_ID = "id";
    public static final String PROFILE_COLUMN_PROFILE_NAME = "profileName";
    public static final String PROFILE_COLUMN_SURVEY_TYPE = "surveyType";
    public static final String PROFILE_COLUMN_BRIGHTNESS = "brightness";
    public static final String PROFILE_COLUMN_AUTO_BRIGHTNESS = "autoBrightness";
    public static final String PROFILE_COLUMN_VOLUME = "volume";
    public static final String PROFILE_COLUMN_BLUETOOTH = "bluetooth";
    public static final String PROFILE_COLUMN_WIFI = "wifi";

    private ProfileSQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    private SQLiteDatabase myDB;

    public static ProfileSQLiteHelper getInstance(Context context){
        if(instance==null){
          return instance= new ProfileSQLiteHelper(context);
        }else{
            return instance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.myDB = db;
        this.myDB.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL,%s INTEGER,%s INTEGER,%s TEXT,%s INTEGER,%s TEXT,%s TEXT)",
            PROFILE_TABLE_NAME,PROFILE_COLUMN_ID,PROFILE_COLUMN_PROFILE_NAME,PROFILE_COLUMN_SURVEY_TYPE,PROFILE_COLUMN_BRIGHTNESS,PROFILE_COLUMN_AUTO_BRIGHTNESS,PROFILE_COLUMN_VOLUME,PROFILE_COLUMN_BLUETOOTH,PROFILE_COLUMN_WIFI));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.myDB = db;
        this.myDB.execSQL("DROP TABLE IF EXISTS profile ");
        onCreate(myDB);
    }
    public boolean insertProfile  (Profile profile)
    {
        getDatabase().insert(PROFILE_TABLE_NAME, null, makeProfileContentValues(profile));
        return true;
    }


    public ArrayList<Profile> viewProfiles(){
        ArrayList<Profile> arrayList= new ArrayList<Profile>();
        SQLiteDatabase db= getDatabase();
        Cursor res= db.rawQuery("SELECT * FROM profile ",null);
        while (res.moveToNext()){
            Profile p= new Profile();
            p.setId(res.getInt(res.getColumnIndex(PROFILE_COLUMN_ID)));
            p.setProfileName(res.getString(res.getColumnIndex(PROFILE_COLUMN_PROFILE_NAME)));
            //SurveyType s= null;
            //p.setSurveyType(s.fromCode(res.getInt(res.getColumnIndex(PROFILE_COLUMN_SURVEY_TYPE))));
            p.setBrightness(res.getInt(res.getColumnIndex(PROFILE_COLUMN_BRIGHTNESS)));
            p.setAutoBrightness(Boolean.valueOf(res.getString(res.getColumnIndex(PROFILE_COLUMN_AUTO_BRIGHTNESS))));
            p.setVolume(res.getInt(res.getColumnIndex(PROFILE_COLUMN_VOLUME)));
            p.setBluetooth(Boolean.valueOf(res.getString(res.getColumnIndex(PROFILE_COLUMN_BLUETOOTH))));
            p.setWifi(Boolean.valueOf(res.getString(res.getColumnIndex(PROFILE_COLUMN_WIFI))));
            arrayList.add(p);
        }
        return arrayList;
    }


    private SQLiteDatabase getDatabase(){
        if(this.myDB == null){
            this.myDB = this.getWritableDatabase();
        }
        return this.myDB;
    }

    private ContentValues makeProfileContentValues(Profile profile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_PROFILE_NAME, profile.getProfileName());
        contentValues.put(PROFILE_COLUMN_SURVEY_TYPE, String.valueOf(profile.getSurveyType()));
        contentValues.put(PROFILE_COLUMN_BRIGHTNESS, profile.getBrightness());
        contentValues.put(PROFILE_COLUMN_AUTO_BRIGHTNESS, profile.isAutoBrightness());
        contentValues.put(PROFILE_COLUMN_VOLUME, profile.getVolume());
        contentValues.put(PROFILE_COLUMN_BLUETOOTH, String.valueOf(profile.isBluetooth()));
        contentValues.put(PROFILE_COLUMN_WIFI,String.valueOf(profile.isWifi()));
        return  contentValues;

    }


}
