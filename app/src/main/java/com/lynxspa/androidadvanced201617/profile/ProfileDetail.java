package com.lynxspa.androidadvanced201617.profile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.AppList.ListAppActivity;
import com.lynxspa.androidadvanced201617.Beacon.BeaconActivity;
import com.lynxspa.androidadvanced201617.MainActivity;
import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.Wifi.WifiListActivity;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.map.MapsActivity;
import com.lynxspa.androidadvanced201617.nfc.NFCActivity;

public class ProfileDetail extends AppCompatActivity implements View.OnClickListener {

    private DBHelper mydb;
    private EditText editText;
    private SeekBar brightnessBar;
    private CheckBox brightnessCheckBox;
    private SeekBar volumeBarRing;
    private SeekBar volumeBarMusic;
    private SeekBar volumeBarNotification;
    private Switch bluetoothSwitch;
    private Switch wifiSwitch;
    private RadioButton gpsButton;
    private RadioButton wifiButton;
    private RadioButton nfcButton;
    private RadioButton beaconButton;
    private RadioGroup radioGroup;
    private TextView appList;

    final static private int APP_LIST_ACTIVITY=1;
    final static private int WIFI_LIST_ACTIVITY=1;
    final static private int GPS_ACTIVITY=1;
    final static private int NFC_ACTIVITY=1;
    final static private int BEACON_ACTIVITY=1;

    private Profilo currentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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
        volumeBarRing = (SeekBar) findViewById(R.id.volumeBarRing);
        volumeBarMusic=(SeekBar)findViewById(R.id.volumeBarMusic);
        volumeBarNotification=(SeekBar) findViewById(R.id.volumeBarNotification);
        bluetoothSwitch = (Switch) findViewById(R.id.switchBluetooth);
        wifiSwitch = (Switch) findViewById(R.id.switchWifi);
        appList = (TextView) findViewById(R.id.appList);

        final Button confirmButton = (Button) findViewById(R.id.confirmButton);

        appList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listAppsActivity = new Intent(ProfileDetail.this, ListAppActivity.class);
                startActivityForResult(listAppsActivity, APP_LIST_ACTIVITY);
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity=new Intent(ProfileDetail.this, MapsActivity.class);
                startActivityForResult(mapsActivity,GPS_ACTIVITY);
            }
        });

        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wifiActivity=new Intent(ProfileDetail.this, WifiListActivity.class);
                startActivityForResult(wifiActivity, WIFI_LIST_ACTIVITY);
                //ArrayList<WifiList> wifi=wifiActivity.getParcelableArrayListExtra("wifi");
            }
        });

        nfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nfcActivity=new Intent(ProfileDetail.this, NFCActivity.class);
                startActivityForResult(nfcActivity, NFC_ACTIVITY);
            }
        });

        beaconButton.setOnClickListener(new  View.OnClickListener(){
            @Override
        public void onClick(View v){
                Intent beaconActivity=new Intent(ProfileDetail.this, BeaconActivity.class);
                startActivityForResult(beaconActivity, BEACON_ACTIVITY);
            }
        });


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

                    if(!editText.getText().toString().isEmpty()) {
                        Profilo profilo = new Profilo(id, editText.getText().toString(), radioGroup.getCheckedRadioButtonId(),
                                brightnessBar.getProgress(), checkBoxBrightness, volumeBarRing.getProgress(), volumeBarMusic.getProgress(),
                                volumeBarNotification.getProgress(), switchBluetooth, switchWifi, appList.getText().toString());
                        mydb.insertOrUpdateProfile(profilo);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Errore: Inserisci nome profilo", Toast.LENGTH_SHORT).show();
                    }

                } else if (editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
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
        mydb = DBHelper.getInstance(this);

        editText=(EditText)findViewById(R.id.nomeProfilo);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        editText.setOnClickListener(this);

        Button confirmButton=(Button)findViewById(R.id.modifyButton);
        Button deleteProfile=(Button)findViewById(R.id.deleteProfile);
        gpsButton=(RadioButton) findViewById(R.id.gpsButton);
        wifiButton=(RadioButton) findViewById(R.id.wifiButton);
        nfcButton=(RadioButton) findViewById(R.id.nfcButton);
        RadioButton beaconButton=(RadioButton) findViewById(R.id.beaconButton);
        brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);
        brightnessCheckBox=(CheckBox)findViewById(R.id.brightnessCheckBox);
        volumeBarRing = (SeekBar) findViewById(R.id.volumeBarRing);
        volumeBarMusic=(SeekBar)findViewById(R.id.volumeBarMusic);
        volumeBarNotification=(SeekBar) findViewById(R.id.volumeBarNotification);
        bluetoothSwitch=(Switch)findViewById(R.id.switchBluetooth);
        wifiSwitch=(Switch)findViewById(R.id.switchWifi);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        appList = (TextView) findViewById(R.id.appList);

        final Bundle profilo = getIntent().getExtras();
        currentProfile=mydb.getProfileById((Integer) profilo.get("Profilo"));
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

        volumeBarRing.setProgress(currentProfile.getVolumeBarRing());
        volumeBarMusic.setProgress(currentProfile.getVolumeBarMusic());
        volumeBarNotification.setProgress(currentProfile.getVolumeBarNotification());
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

        appList.setText(currentProfile.getAppName());

        appList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listAppsActivity = new Intent(ProfileDetail.this, ListAppActivity.class);
                startActivityForResult(listAppsActivity, APP_LIST_ACTIVITY);
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity=new Intent(ProfileDetail.this, MapsActivity.class);
                mapsActivity.putExtra("Profilo", currentProfile.getId());
                startActivityForResult(mapsActivity,GPS_ACTIVITY);
            }
        });

        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wifiActivity=new Intent(ProfileDetail.this, WifiListActivity.class);
                wifiActivity.putExtra("Profilo", currentProfile.getId());
                startActivityForResult(wifiActivity, WIFI_LIST_ACTIVITY);
            }
        });

        nfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nfcActivity=new Intent(ProfileDetail.this, NFCActivity.class);
                nfcActivity.putExtra("Profilo", currentProfile.getId());
                startActivityForResult(nfcActivity, NFC_ACTIVITY);
            }
        });

        beaconButton.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent beaconActivity=new Intent(ProfileDetail.this, BeaconActivity.class);
                beaconActivity.putExtra("Profilo", currentProfile.getId());
                startActivityForResult(beaconActivity, BEACON_ACTIVITY);
            }
        });


        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.deleteProfile(currentProfile.getId());
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

                   currentProfile = new Profilo(currentProfile.getId(), editText.getText().toString(), radioGroup.getCheckedRadioButtonId(),
                            brightnessBar.getProgress(), checkBoxBrightness, volumeBarRing.getProgress(),volumeBarMusic.getProgress(),
                            volumeBarNotification.getProgress(), switchBluetooth, switchWifi,appList.getText().toString());
                    mydb.insertOrUpdateProfile(currentProfile);
                    Intent mainActivity = new Intent(ProfileDetail.this, MainActivity.class);
                    startActivity(mainActivity);
                } else if (editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}