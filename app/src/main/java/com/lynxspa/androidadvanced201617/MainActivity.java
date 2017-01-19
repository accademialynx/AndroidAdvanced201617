package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


        ArrayList<String> profili=new ArrayList<String>();
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,profili);

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
        if(getIntent().getStringExtra("profilo")!=null) {
            String profilo = getIntent().getStringExtra("profilo").toString();
            Toast.makeText(getApplicationContext(), profilo+" aggiunto", Toast.LENGTH_SHORT).show();
            profili.add(profilo);
        }
        else{
            return;
        }
    }



    private void visualizzaProfilo(String[] profili){

    }




}
