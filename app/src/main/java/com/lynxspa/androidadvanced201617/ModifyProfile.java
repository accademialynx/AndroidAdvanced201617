package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Esami on 24/01/2017.
 */
public class ModifyProfile extends AppCompatActivity implements View.OnClickListener {


    DBHelper mydb;
    EditText editText;
//    RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
    SeekBar brightnessBar;
    CheckBox brightnessCheckBox;
    SeekBar volumeBar;
    Switch bluetoothSwitch;
    Switch wifiSwitch;
    private Profilo currentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        mydb = DBHelper.getInstance(this);
        final Bundle profilo = getIntent().getExtras();
        currentProfile=mydb.getAllProfiles().get((Integer) profilo.get("Profilo"));

        final Button confirmButton=(Button)findViewById(R.id.modifyButton);
        final Button deleteProfile=(Button)findViewById(R.id.deleteProfile);
        final Button cancel=(Button)findViewById(R.id.cancelButton);

        editText=(EditText) findViewById(R.id.nomeProfilo);
        brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);
        brightnessCheckBox=(CheckBox)findViewById(R.id.brightnessCheckBox);
        volumeBar=(SeekBar)findViewById(R.id.volumeBar);
        bluetoothSwitch=(Switch)findViewById(R.id.switchBluetooth);
        wifiSwitch=(Switch)findViewById(R.id.switchWifi);

        final RadioButton gpsButton=(RadioButton) findViewById(R.id.gpsButton);
        final RadioButton wifiButton=(RadioButton) findViewById(R.id.wifiButton);
        final RadioButton nfcButton=(RadioButton) findViewById(R.id.nfcButton);
        final RadioButton beaconButton=(RadioButton) findViewById(R.id.beaconButton);
        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        editText.setOnClickListener(this);


        editText.setText(currentProfile.getName());

        switch (currentProfile.getRadioButton())
        {
            case R.id.gpsButton :
                gpsButton.setChecked(true);
                break;
            case R.id.wifiButton :
                wifiButton.setChecked(true);
                break;
            case R.id.nfcButton :
                nfcButton.setChecked(true);
                break;
            case R.id.beaconButton:
                beaconButton.setChecked(true);
                break;
            default :
                break;
        }

        brightnessBar.setProgress(currentProfile.getBrigthnesBar());
        if(currentProfile.getBrightnessCheckBox()==1){
            brightnessCheckBox.setChecked(true);
        }else{
            brightnessCheckBox.setChecked(false);
        }

        volumeBar.setProgress(currentProfile.getVolumeBar());
        if(currentProfile.getBluetoothSwitch()==1){
            bluetoothSwitch.setChecked(true);
        }else{
            bluetoothSwitch.setChecked(false);
        }
        if(currentProfile.getWifiSwitch()==1){
            wifiSwitch.setChecked(true);
        }else{
            wifiSwitch.setChecked(false);
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(ModifyProfile.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.delete((Integer) profilo.get("Profilo"));
                Intent mainActivity = new Intent(ModifyProfile.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertOrUpdateProfile(currentProfile);
                Intent mainActivity = new Intent(ModifyProfile.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });


        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity=new Intent(ModifyProfile.this, MapsActivity.class);
                startActivity(mapsActivity);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
