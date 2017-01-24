package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileDetail extends AppCompatActivity implements View.OnClickListener {

    DBHelper mydb;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mydb = DBHelper.getInstance(this);

        editText=(EditText) findViewById(R.id.nomeProfilo);

        editText.setOnClickListener(this);

        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        final SeekBar brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);
        final CheckBox brightnessCheckBox=(CheckBox)findViewById(R.id.brightnessCheckBox);
        final SeekBar volumeBar=(SeekBar)findViewById(R.id.volumeBar);
        final Switch bluetoothSwitch=(Switch)findViewById(R.id.switchBluetooth);
        final Switch wifiSwitch=(Switch)findViewById(R.id.switchWifi);

        final Button confirmButton=(Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (editText.getText().toString() != null && !editText.getText().toString().isEmpty()) {
                    int id=DBHelper.getInstance(getApplicationContext()).getAllProfiles().size()+1;
                    Profilo profilo=new Profilo(id, editText.getText().toString(), radioGroup, brightnessBar, brightnessCheckBox, volumeBar, bluetoothSwitch, wifiSwitch);
                    mydb.insertOrUpdateProfile(profilo);
                } else if(editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if(editText.getText().toString().equals("Inserisci nome profilo")){
            editText.setText("");
        }else if(editText.getText().toString().equals("")){
            editText.setText("Inserisci nome profilo");
        }else{
            return;
        }
    }

}
