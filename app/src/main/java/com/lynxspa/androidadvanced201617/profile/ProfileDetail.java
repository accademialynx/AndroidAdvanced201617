package com.lynxspa.androidadvanced201617.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.lynxspa.androidadvanced201617.AppList.*;
import com.lynxspa.androidadvanced201617.Beacon.*;
import com.lynxspa.androidadvanced201617.MainActivity;
import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.Wifi.*;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.map.MapsActivity;
import com.lynxspa.androidadvanced201617.nfc.NFCActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private EditText first_password;
    private EditText second_password;
    private String newPassword;
    private String retypePassword;
    private String otpRandomParams;
    private TextView otpPosition;
    private String authType;

    final static private int APP_LIST_ACTIVITY=1;
    final static private int WIFI_LIST_ACTIVITY=1;
    final static private int GPS_ACTIVITY=1;
    final static private int NFC_ACTIVITY=1;
    final static private int BEACON_ACTIVITY=1;

    private Activity currentActivity;
    private Profilo currentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        currentActivity=this;
        mydb = DBHelper.getInstance(this);

        final Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.get("Profilo")!=null) {
            modProfile();
        }else{
            newProfile();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.profile_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        LayoutInflater inflater=currentActivity.getLayoutInflater();
        switch (item.getItemId()){
            case R.id.action_password:
                final View dialogView=inflater.inflate(R.layout.set_password_layout, null);
                AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(currentActivity)
                        .setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                first_password=(EditText) dialogView.findViewById(R.id.first_password);
                                second_password=(EditText)dialogView.findViewById(R.id.second_password);

                                 if((first_password.getText().toString()!=null && !first_password.getText().toString().isEmpty())
                                         && first_password.getText().toString().equals(second_password.getText().toString())) {
                                     newPassword = first_password.getText().toString();
                                     retypePassword = second_password.getText().toString();
                                     authType=AuthenticationType.PASSWORD.name();
                                 }
                                 else{
                                     Toast.makeText(getApplicationContext(), "Errore: Inserisci password o Password diverse", Toast.LENGTH_SHORT).show();
                                    return;
                                 }
                                }
                        });
            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
        break;

            case R.id.oneTimePassword:
                final View otpDialogView=inflater.inflate(R.layout.set_otp_password_layout,null);
                otpPosition=(TextView)otpDialogView.findViewById(R.id.otp_position);
                otpRandomParams =RandomParam.getRandomParam();
                otpPosition.setText(otpRandomParams);
                AlertDialog.Builder otpDialogBuilder=new AlertDialog.Builder(currentActivity)
                        .setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                first_password=(EditText) otpDialogView.findViewById(R.id.otp_password);

                                if((first_password.getText().toString()!=null && !first_password.getText().toString().isEmpty())
                                        && first_password.getText().toString().equals(mydb.getAllPasswords().get(otpRandomParams))) {
                                    authType=AuthenticationType.OTP.name();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Errore: password errata", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                    });
                otpDialogBuilder.setView(otpDialogView);
                otpDialogBuilder.show();

        }
        return true;
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
                                    volumeBarNotification.getProgress(), switchBluetooth, switchWifi,
                                    appList.getText().toString(), newPassword,authType);
                            mydb.insertOrUpdateProfile(profilo);
                        }
                        finish();

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
                            brightnessBar.getProgress(), checkBoxBrightness, volumeBarRing.getProgress(), volumeBarMusic.getProgress(),
                            volumeBarNotification.getProgress(), switchBluetooth, switchWifi,
                            appList.getText().toString(), newPassword,authType);
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
