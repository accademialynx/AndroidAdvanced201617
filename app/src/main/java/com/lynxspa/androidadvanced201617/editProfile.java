package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class editProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);




        Button gpsButton=(Button) findViewById(R.id.gpsButton);
        Button wifiButton=(Button) findViewById(R.id.wifiButton);
        Button nfcButton=(Button) findViewById(R.id.nfcButton);
        Button beaconButton=(Button) findViewById(R.id.beaconButton);

        ArrayList<Button> buttons=new ArrayList<Button>();
        buttons.add(gpsButton);
        buttons.add(wifiButton);
        buttons.add(nfcButton);
        buttons.add(beaconButton);

        creaProfilo();
    }

    private void creaProfilo(){

        final TextView textView=(TextView) findViewById(R.id.nomeProfilo);
        Button confirmButton=(Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent mainActivity = new Intent(editProfile.this,MainActivity.class);
                mainActivity.putExtra("nomeProfilo",textView);
                startActivity(mainActivity);
            }
        });

    }

}
