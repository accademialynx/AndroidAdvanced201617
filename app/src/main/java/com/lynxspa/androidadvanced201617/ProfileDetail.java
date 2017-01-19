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

public class ProfileDetail extends AppCompatActivity {

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText editText=(EditText) findViewById(R.id.nomeProfilo);

        /*Button gpsButton=(Button) findViewById(R.id.gpsButton);
        Button wifiButton=(Button) findViewById(R.id.wifiButton);
        Button nfcButton=(Button) findViewById(R.id.nfcButton);
        Button beaconButton=(Button) findViewById(R.id.beaconButton);

        ArrayList<Button> buttons=new ArrayList<Button>();
        buttons.add(gpsButton);
        buttons.add(wifiButton);
        buttons.add(nfcButton);
        buttons.add(beaconButton);*/
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        SeekBar brightnessBar=(SeekBar)findViewById(R.id.brightnessBar);

        CheckBox checkBox=(CheckBox)findViewById(R.id.brightnessCheckBox);

        SeekBar volumeBar=(SeekBar)findViewById(R.id.volumeBar);

        Switch bluetooth=(Switch)findViewById(R.id.switchBluetooth);
        Switch wifi=(Switch)findViewById(R.id.switchWifi);

        creaProfilo(editText, radioGroup,brightnessBar,checkBox, volumeBar, bluetooth, wifi);

    }

    private void creaProfilo(final EditText editText, final RadioGroup buttons, final SeekBar brightnessBar, final CheckBox brightnessCheckBox,
                             final SeekBar volumeBar, final Switch bluetooth, final Switch wifi){

        Button confirmButton=(Button)findViewById(R.id.confirmButton);
        mydb = DBHelper.getInstance(this);
             confirmButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View arg0) {
                     if (editText.getText().toString() != null && !editText.getText().toString().equals("")) {
                         mydb.addProfile(editText.toString(),buttons,brightnessBar,brightnessCheckBox,volumeBar,bluetooth,wifi);
                     } else {
                         Toast.makeText(getApplicationContext(), "Errore campo vuoto, inserire un profilo valido", Toast.LENGTH_SHORT).show();
                    }
                     finish();
                }
            });




    }

}
