package com.lynxspa.androidadvanced201617.nfcDir;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;
import com.lynxspa.androidadvanced201617.profileDir.Profilo;

import java.util.List;

public class NFCActivity extends AppCompatActivity {

    private DBHelper mydb;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private TextView mText;
    private Button confirm;
    private int idprofilo=0;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_nfc);
        mydb=DBHelper.getInstance(this);

        mText = (TextView) findViewById(R.id.info);
        confirm=(Button)findViewById(R.id.confirmButtonNFC);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] {
                ndef,
        };

        Intent intent = getIntent();
        String action = intent.getAction();

        mTechLists = new String[][] { new String[] { NfcF.class.getName() } };


    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        ListView techListView=(ListView)findViewById(R.id.nfcTechList);;
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        final Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            mText.setText("tag == null");
        } else {
            String tagInfo = "";

            byte[] tagId = tag.getId();
            for (int i = 0; i < tagId.length; i++) {
                tagInfo += Integer.toHexString(tagId[i] & 0xFF) + ":";
            }

            String[] techList = tag.getTechList();
         /*   tagInfo += "\nTech List\n";
            tagInfo += "length = " + techList.length + "\n";
            for (int i = 0; i < techList.length; i++) {
                tagInfo += techList[i] + "\n ";
            }*/
            mText.setText(tagInfo.substring(0, tagInfo.length() - 1));


            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, techList);

            techListView.setAdapter(adapter);

            final String finalTagInfo = tagInfo;
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent();
                    final Bundle profilo = getIntent().getExtras();
                    if(profilo!=null){
                        Profilo currentProfile=mydb.getProfileById((Integer) profilo.get("Profilo"));
                        idprofilo=currentProfile.getId();
                    }else{
                        List<Profilo> profili = mydb.getAllProfiles();
                        if(!profili.isEmpty() || profili!=null) {
                            for (int i = 0; i < profili.size(); i++) {
                                idprofilo = profili.get(i).getId() + 1;
                            }
                        }
                        else{
                            idprofilo=1;
                        }
                    }
                    NFC nfc=new NFC(tag.toString(), finalTagInfo,idprofilo);
                    mydb.insertOrUpdateNfc(nfc);
                    intent.putExtra("nfc", (Parcelable) nfc);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }
}
