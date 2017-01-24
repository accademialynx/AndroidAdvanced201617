package com.lynxspa.androidadvanced201617;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
        // ArrayList<String> profili=new ArrayList<String>();
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mydb.getAllProfiles() );

        listView.setAdapter(adapter);
        Button addProfileButton=(Button)findViewById(R.id.addProfile);
        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent editActivity = new Intent(MainActivity.this, ProfileDetail.class);
                //  editActivity.putStringArrayListExtra("listaProfili", profili);
                startActivity(editActivity);

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modifyProfile = new Intent(MainActivity.this, ModifyProfile.class);
                modifyProfile.putStringArrayListExtra("listaProfili", mydb.getAllProfiles());
                startActivity(modifyProfile);
            }
        });
      /*  listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence menu[] = new CharSequence[] {"Modifica", "Elimina"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(new Application());
                builder.setTitle("Seleziona un'opzione");
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent editActivity = new Intent(MainActivity.this, ProfileDetail.class);
                        if (builder.getContext().getApplicationContext().equals("Modifica")) {
                            //  editActivity.putStringArrayListExtra("listaProfili", profili);
                            startActivity(editActivity);
                        }else{
                            return;
                        }
                    }
                });
                builder.show();
            }
        });*/

    }
}
