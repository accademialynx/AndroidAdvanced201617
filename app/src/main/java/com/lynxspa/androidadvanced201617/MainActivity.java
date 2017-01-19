package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] profili={"Casa","Lavoro","Accademia"};
        ListView listView=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,profili);

        listView.setAdapter(adapter);
        Button addProfileButton=(Button)findViewById(R.id.addProfile);
        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent editActivity = new Intent(MainActivity.this,ProfileDetail.class);
                startActivity(editActivity);
            }
        });


    }




    private void visualizzaProfilo(String[] profili){

    }




}
