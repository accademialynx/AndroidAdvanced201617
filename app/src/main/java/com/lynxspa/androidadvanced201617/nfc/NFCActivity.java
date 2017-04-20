package com.lynxspa.androidadvanced201617.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.db.DBHelper;
import com.lynxspa.androidadvanced201617.profile.Profilo;

import java.util.List;

public class NFCActivity extends AppCompatActivity {

    private DBHelper mydb;
    private NfcAdapter mAdapter;
    private TextView mText;
    private Button confirm;
    private int idprofilo=0;
    private static final String MIME_TEXT_PLAIN = "text/plain";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nfc);
        mydb=DBHelper.getInstance(this);

        mText = (TextView) findViewById(R.id.info);
        confirm=(Button)findViewById(R.id.confirmButtonNFC);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        final Intent intent = this.getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        String[][] techList = new String[][] { new String[] { NfcV.class.getName() } };

        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filter.addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException(e.getMessage());
        }

        IntentFilter[] filters=new IntentFilter[]{filter};
        mAdapter.enableForegroundDispatch(this, pendingIntent, filters, techList);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ListView techListView=(ListView)findViewById(R.id.nfcTechList);;
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        final Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (tag == null) {
            mText.setText("null tag");
        } else {
            String tagInfo = "";

            byte[] tagId = tag.getId();
            for (int i = 0; i < tagId.length; i++) {
                tagInfo += Integer.toHexString(tagId[i] & 0xFF) + ":";
            }

            String[] techList = tag.getTechList();

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
                    /*
                    * devo implementare la parcelable? */
                   // intent.putExtra("nfc", (Parcelable) nfc);
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
