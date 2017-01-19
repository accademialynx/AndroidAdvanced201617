package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.Utils.SurveyType;
import com.lynxspa.androidadvanced201617.data.Profile;
import com.lynxspa.androidadvanced201617.sql.ProfileSQLiteHelper;

import java.io.Console;

public class ProfileCreationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextProfileName;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private SeekBar brightnessSeekBar;
    private CheckBox brightnessCheckBox;
    private SeekBar volumeSeekBar;
    private Switch bluetoothSwitch;
    private Switch wifiSwitch;
    private Button confirmButton;
    ProfileSQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
         db= ProfileSQLiteHelper.getInstance(getApplicationContext());

        editTextProfileName= (EditText) findViewById(R.id.editTextProfileName);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        brightnessSeekBar = (SeekBar) findViewById(R.id.seekBarBrightness);
        brightnessCheckBox = (CheckBox) findViewById(R.id.checkBoxAutoBrightness);
        volumeSeekBar = (SeekBar) findViewById(R.id.seekBarVolume);
        bluetoothSwitch= (Switch) findViewById(R.id.switchBluetooth);
        wifiSwitch = (Switch) findViewById(R.id.switchWifi);
        confirmButton= (Button) findViewById(R.id.confirmButton);


        confirmButton.setOnClickListener(this);

        }

    @Override
    public void onClick(View view) {
        if(confirmButton.isPressed()){
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);

            Profile profile = new Profile();

            //profile.setSurveyType((SurveyType) radioButton.getText()));
            profile.setProfileName(String.valueOf(editTextProfileName.getText()));
            profile.setBrightness(brightnessSeekBar.getProgress());
            profile.setAutoBrightness(brightnessCheckBox.isChecked());
            profile.setVolume(volumeSeekBar.getProgress());
            profile.setBluetooth(bluetoothSwitch.isChecked());
            profile.setWifi(wifiSwitch.isChecked());
            editTextProfileName.setText(Boolean.toString(profile.isBluetooth()));



            Intent intent = new Intent(this,MainActivity.class);





            startActivity(intent);

        }



}
}
