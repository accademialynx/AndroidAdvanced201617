package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.profileDir.ProfileDetail;
import com.lynxspa.androidadvanced201617.profileDir.Profilo;

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
        for(int i=0;i<mydb.getAllProfiles().size();i++){
            listaProfili.add(mydb.getAllProfiles().get(i).getName());
        }}

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
