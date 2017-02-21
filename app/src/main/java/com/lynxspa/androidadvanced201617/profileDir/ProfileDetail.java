package com.lynxspa.androidadvanced201617.profileDir;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.AppListDir.ListAppActivity;
import com.lynxspa.androidadvanced201617.MainActivity;
import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.mapDir.MapsActivity;

public class ProfileDetail extends AppCompatActivity implements View.OnClickListener {

    DBHelper mydb;
    EditText editText;
    SeekBar brightnessBar;
    CheckBox brightnessCheckBox;
    SeekBar volumeBar;
    Switch bluetoothSwitch;
    Switch wifiSwitch;
    RadioButton gpsButton;
    RadioButton wifiButton;
    RadioButton nfcButton;
    RadioButton beaconButton;
    RadioGroup radioGroup;
    TextView appList;

    final static private int APP_LIST_ACTIVITY=1;

    private Profilo currentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mydb = DBHelper.getInstance(this);
        final Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.get("Profilo")!=null) {
            modProfile();
        }else{
            newProfile();
        }
    }

    private void newProfile(){
        setContentView(R.layout.activity_edit_profile);

        mydb = DBHelper.getInstance(this);

        editText = (EditText) findViewById(R.id.nomeProfilo);

        editText.setOnClickListener(this);

        gpsButton = (RadioButton) findViewById(R.id.gpsButton);
        wifiButton = (RadioButton) findViewById(R.id.wifiButton);
        nfcButton = (RadioButton) findViewById(R.id.nfcButton);
        beaconButton = (RadioButton) findViewById(R.id.beaconButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        brightnessBar = (SeekBar) findViewById(R.id.brightnessBar);
        brightnessCheckBox = (CheckBox) findViewById(R.id.brightnessCheckBox);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        bluetoothSwitch = (Switch) findViewById(R.id.switchBluetooth);
        wifiSwitch = (Switch) findViewById(R.id.switchWifi);
        appList = (TextView) findViewById(R.id.appList);

        final Button confirmButton = (Button) findViewById(R.id.confirmButton);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (editText.getText().toString() != null && !editText.getText().toString().isEmpty() && !editText.getText().toString().equals("Inserisci nome profilo")) {
                    int id = DBHelper.getInstance(getApplicationContext()).getAllProfiles().size() + 1;

                    int checkBoxBrightness = 0;
                    if (brightnessCheckBox.isChecked()) {
                        checkBoxBrightness = 1;
                    }

                    int switchBluetooth = 0;
                    if (bluetoothSwitch.isChecked()) {
                        switchBluetooth = 1;
                    }

                    int switchWifi = 0;
                    if (wifiSwitch.isChecked()) {
                        switchWifi = 1;
                    }

                    Profilo profilo = new Profilo(id, editText.getText().toString(), radioGroup.getCheckedRadioButtonId(), brightnessBar.getProgress(), checkBoxBrightness, volumeBar.getProgress(), switchBluetooth, switchWifi);
                    mydb.insertOrUpdateProfile(profilo);
                } else if (editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        appList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listAppsActivity = new Intent(ProfileDetail.this, ListAppActivity.class);
                startActivityForResult(listAppsActivity, 1);
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity=new Intent(ProfileDetail.this, MapsActivity.class);
                startActivity(mapsActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==APP_LIST_ACTIVITY && resultCode == Activity.RESULT_OK){
            appList.setText(data.getStringExtra("App"));
        }
    }

    @Override
    public void onClick(View v) {
        if(editText.getText().toString().equals("Inserisci nome profilo")){
            editText.setText("");
        }else if(editText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"inserisci un profilo valido",Toast.LENGTH_SHORT).show();
            return;
        }
    }


    private void modProfile(){
        setContentView(R.layout.activity_modify);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        mydb = DBHelper.getInstance(this);
        final Bundle profilo = getIntent().getExtras();
        currentProfile=mydb.getProfileById((Integer) profilo.get("Profilo"));

        final Button confirmButton=(Button)findViewById(R.id.modifyButton);
        final Button deleteProfile=(Button)findViewById(R.id.deleteProfile);

        editText=(EditText) findViewById(R.id.nomeProfilo);
        brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);
        brightnessCheckBox=(CheckBox)findViewById(R.id.brightnessCheckBox);
        volumeBar=(SeekBar)findViewById(R.id.volumeBar);
        bluetoothSwitch=(Switch)findViewById(R.id.switchBluetooth);
        wifiSwitch=(Switch)findViewById(R.id.switchWifi);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        final RadioButton gpsButton=(RadioButton) findViewById(R.id.gpsButton);
        final RadioButton wifiButton=(RadioButton) findViewById(R.id.wifiButton);
        final RadioButton nfcButton=(RadioButton) findViewById(R.id.nfcButton);
        final RadioButton beaconButton=(RadioButton) findViewById(R.id.beaconButton);

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


        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.delete(currentProfile.getId());
                Intent mainActivity = new Intent(ProfileDetail.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString() != null && !editText.getText().toString().isEmpty() && !editText.getText().toString().equals("Inserisci nome profilo")) {
                    int checkBoxBrightness = 0;
                    if (brightnessCheckBox.isChecked()) {
                        checkBoxBrightness = 1;
                    }

                    int switchBluetooth = 0;
                    if (bluetoothSwitch.isChecked()) {
                        switchBluetooth = 1;
                    }

                    int switchWifi = 0;
                    if (wifiSwitch.isChecked()) {
                        switchWifi = 1;
                    }

                    currentProfile = new Profilo(currentProfile.getId(), editText.getText().toString(), radioGroup.getCheckedRadioButtonId(), brightnessBar.getProgress(), checkBoxBrightness, volumeBar.getProgress(), switchBluetooth, switchWifi);
                    mydb.insertOrUpdateProfile(currentProfile);
                    Intent mainActivity = new Intent(ProfileDetail.this, MainActivity.class);
                    startActivity(mainActivity);
                } else if (editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        appList = (TextView) findViewById(R.id.appList);

        appList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listAppsActivity = new Intent(ProfileDetail.this, ListAppActivity.class);
                startActivity(listAppsActivity);
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity=new Intent(ProfileDetail.this, MapsActivity.class);
                startActivity(mapsActivity);
            }
        });
    }

}
