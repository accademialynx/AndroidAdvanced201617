package com.lynxspa.androidadvanced201617;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
        TextView textView=(TextView)findViewById(R.id.nameProfile);
        ArrayList<String> listaProfili=new ArrayList<>();
        for(int i=0;i<mydb.getAllProfiles().size();i++){
            listaProfili.add(mydb.getAllProfiles().get(i).getName());
            textView.setText(listaProfili.get(i));
        }

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
                Profilo profilo=mydb.getAllProfiles().get(position);
                modifyProfile.putExtra("Profilo", profilo.getId());
                modifyProfile.putExtras(modifyProfile);
                startActivity(modifyProfile);
            }
        });



    }
}
