package com.lynxspa.androidadvanced201617.nfcDir;

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
import android.widget.TextView;
import android.widget.Toast;

import com.lynxspa.androidadvanced201617.R;
import com.lynxspa.androidadvanced201617.dbDir.DBHelper;

/**
 * Created by Esami on 14/03/2017.
 */
public class NFCActivity extends AppCompatActivity {

   private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private TextView mText;
    private int mCount = 0;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_nfc);
        mText = (TextView) findViewById(R.id.info);
        mText.setText("Scan a tag");

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
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            mText.setText("tag == null");
        } else {
            String tagInfo = tag.toString() + "\n";

            tagInfo += "\nTag Id: \n";
            byte[] tagId = tag.getId();
            tagInfo += "length = " + tagId.length + "\n";
            for (int i = 0; i < tagId.length; i++) {
                tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";
            }
            tagInfo += "\n";

            String[] techList = tag.getTechList();
            tagInfo += "\nTech List\n";
            tagInfo += "length = " + techList.length + "\n";
            for (int i = 0; i < techList.length; i++) {
                tagInfo += techList[i] + "\n ";
            }
            mText.setText(tagId.toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }
}
