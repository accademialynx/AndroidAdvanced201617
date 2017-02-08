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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        mydb = DBHelper.getInstance(this);

        editText=(EditText) findViewById(R.id.nomeProfilo);

        editText.setOnClickListener(this);



        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        final int radioButtonId= radioGroup.getCheckedRadioButtonId();
        final SeekBar brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);
        final CheckBox brightnessCheckBox=(CheckBox)findViewById(R.id.brightnessCheckBox);
        final SeekBar volumeBar=(SeekBar)findViewById(R.id.volumeBar);
        final Switch bluetoothSwitch=(Switch)findViewById(R.id.switchBluetooth);
        final Switch wifiSwitch=(Switch)findViewById(R.id.switchWifi);

        final Button confirmButton=(Button)findViewById(R.id.modifyButton);
        final Button cancel=(Button)findViewById(R.id.cancelButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(ModifyProfile.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        final RadioButton gpsButton=(RadioButton) findViewById(R.id.gpsButton);
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity = new Intent(ModifyProfile.this, MapsActivity.class);

                startActivity(mapsActivity);
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
