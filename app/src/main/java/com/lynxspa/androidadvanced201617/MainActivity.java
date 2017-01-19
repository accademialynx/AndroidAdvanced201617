package com.lynxspa.androidadvanced201617;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lynxspa.androidadvanced201617.data.Profile;
import com.lynxspa.androidadvanced201617.sql.ProfileSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button addProfile;
    private ListAdapter adapter;
    private ListView listView;
    private ProfileSQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= ProfileSQLiteHelper.getInstance(this);
        List<Profile> profileList= db.viewProfiles();


        addProfile= (Button) findViewById(R.id.buttonAddProfile);
        listView = (ListView) findViewById(R.id.list_view);

        adapter= new ListAdapter(getApplicationContext(),R.layout.raw_layout);

        for (Profile i: profileList){
            adapter.add(i);
        }

        listView.setAdapter(adapter);

        addProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProfileCreationActivity.class);
        startActivity(intent);
        this.finish();
    }
}
