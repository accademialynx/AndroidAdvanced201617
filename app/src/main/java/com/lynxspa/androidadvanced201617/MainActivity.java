package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.EncryptDecrypt.EncryptDecryptClass;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.profileDir.ProfileDetail;
import com.lynxspa.androidadvanced201617.profileDir.Profilo;
import com.lynxspa.androidadvanced201617.profileDir.ProfiloEncrypted;

import java.util.ArrayList;
/*
* Created by Mohamed and Kishore
* */
public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = DBHelper.getInstance(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayList<String> listaProfili=new ArrayList<>();

        if(mydb.getAllProfiles()!=null){
            byte[] raw=new byte[16];
            for(int i=0;i<mydb.getAllProfiles().size();i++){
                /*ProfiloEncrypted profiloEncrypted=new ProfiloEncrypted(mydb.getAllProfiles().get(i).getId(),
                        mydb.getAllProfiles().get(i).getName().toString(),String.valueOf(mydb.getAllProfiles().get(i).getRadioButton()),
                        String.valueOf(mydb.getAllProfiles().get(i).getBrightnessCheckBox()), String.valueOf(mydb.getAllProfiles().get(i).getBrightnessCheckBox()),
                        String.valueOf(mydb.getAllProfiles().get(i).getVolumeBarRing()), String.valueOf(mydb.getAllProfiles().get(i).getVolumeBarMusic()),
                        String.valueOf(mydb.getAllProfiles().get(i).getVolumeBarNotification()), String.valueOf(mydb.getAllProfiles().get(i).getBluetoothSwitch()),
                        String.valueOf(mydb.getAllProfiles().get(i).getWifiSwitch()),mydb.getAllProfiles().get(i).getAppName().toString());

                try {
                    Profilo profilo=EncryptDecryptClass.decrypt(raw,profiloEncrypted);
                    listaProfili.add(profilo.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                String nameProfilo=mydb.getAllProfiles().get(i).getName();
                listaProfili.add(nameProfilo);
            }
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaProfili);
        listView.setAdapter(adapter);

        Button addProfileButton=(Button)findViewById(R.id.addProfile);
        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent editActivity = new Intent(MainActivity.this, ProfileDetail.class);
                startActivity(editActivity);

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modifyProfile = new Intent(MainActivity.this, ProfileDetail.class);
                Profilo profilo=mydb.getAllProfiles().get(position);
                modifyProfile.putExtra("Profilo", profilo.getId());
                startActivity(modifyProfile);
            }
        });

    }
}
