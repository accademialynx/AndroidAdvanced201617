package com.lynxspa.androidadvanced201617;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.encryptDecrypt.EncryptDecryptClass;
import com.lynxspa.androidadvanced201617.profile.ProfileDetail;
import com.lynxspa.androidadvanced201617.profile.Profilo;
import com.lynxspa.androidadvanced201617.profile.ProfiloEncrypted;

import java.util.ArrayList;
/*
* Created by Mohamed and Kishore
* */
public class MainActivity extends AppCompatActivity {

    DBHelper mydb;
    private Activity currentActivity;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = DBHelper.getInstance(this);
        currentActivity=this;

    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayList<String> listaProfili=new ArrayList<>();
        Profilo profiloDecrypted=null;
        if(mydb.getAllProfiles()!=null){
            for(int i=0;i<mydb.getAllProfiles().size();i++){
                Profilo profilo= mydb.getAllProfiles().get(i);
               /* ProfiloEncrypted profiloEncrypted=new ProfiloEncrypted(
                        profilo.getId(),profilo.getName(),String.valueOf(profilo.getRadioButton()),String.valueOf(profilo.getBrigthnesBar()),
                        String.valueOf(profilo.getBrightnessCheckBox()), String.valueOf(profilo.getVolumeBarRing()), String.valueOf(profilo.getVolumeBarMusic()),
                        String.valueOf(profilo.getVolumeBarNotification()), String.valueOf(profilo.getBluetoothSwitch()), String.valueOf(profilo.getWifiSwitch()),
                        profilo.getAppName(), profilo.getPassword());

                try {
                    profiloDecrypted = EncryptDecryptClass.decrypt(profiloEncrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                String nameProfilo=profilo.getName();
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Profilo profilo = mydb.getAllProfiles().get(position);
                if (profilo.getPassword()==null) {
                    Intent modifyProfile = new Intent(MainActivity.this, ProfileDetail.class);
                    modifyProfile.putExtra("Profilo", profilo.getId());
                    startActivity(modifyProfile);
                } else {
                    LayoutInflater inflater = currentActivity.getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.get_password_layout, null);
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(currentActivity)
                            .setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    passwordEditText = (EditText) dialogView.findViewById(R.id.validationPassword);
                                    if (profilo.getPassword() != null && profilo.getPassword().equals(passwordEditText.getText().toString()) &&
                                            !passwordEditText.getText().toString().isEmpty()) {
                                        Intent modifyProfile = new Intent(MainActivity.this, ProfileDetail.class);
                                        modifyProfile.putExtra("Profilo", profilo.getId());
                                        startActivity(modifyProfile);
                                    } else {
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(), "Password errata", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    dialogBuilder.setView(dialogView);
                    dialogBuilder.show();
                }
            }
        });

    }
}
