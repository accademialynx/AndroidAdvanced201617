package com.lynxspa.androidadvanced201617;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
        setContentView(R.layout.activity_edit_profile);

        editText=(EditText) findViewById(R.id.nomeProfilo);

        editText.setOnClickListener(this);

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

       /* ArrayList<String> nomeProfili=mydb.getAllProfiles();
        for(int i=0;i<nomeProfili.toArray().length;i++){
        }*/
        aggiornaProfilo(editText, radioGroup,brightnessBar,checkBox, volumeBar, bluetooth, wifi);

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

    private void aggiornaProfilo(final EditText editText, final RadioGroup buttons, final SeekBar brightnessBar, final CheckBox brightnessCheckBox,
                             final SeekBar volumeBar, final Switch bluetooth, final Switch wifi){

        Button confirmButton=(Button)findViewById(R.id.confirmButton);
        mydb = DBHelper.getInstance(this);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (editText.getText().toString() != null && !editText.getText().toString().isEmpty()) {
                    /**
                     *
                     * da implementare ancora l'aggiornamento dei dati presenti nel db
                     *
                     * */
                 //   mydb.updateProfile(editText.getText().toString(), buttons, brightnessBar, brightnessCheckBox, volumeBar, bluetooth, wifi);
                } else if(editText.getText().toString().equals("Inserisci nome profilo") || editText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Errore: nome profilo non valido", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });




    }
}
