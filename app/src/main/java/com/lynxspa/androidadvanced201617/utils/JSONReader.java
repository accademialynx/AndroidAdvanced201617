package com.lynxspa.androidadvanced201617.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Esami on 21/04/2017.
 */
public class JSONReader {

    public Map<String, String> readJson(String fileJson) {

        try {

            JSONObject obj = new JSONObject(fileJson);
            JSONArray m_jArry = obj.getJSONArray("Passwords");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;
            String key_value="";
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("row"));
                String row_value =jo_inside.getString("row");

                String col_value=jo_inside.getString("col");
                String password_value = jo_inside.getString("value");

                key_value=row_value+col_value;
                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put(key_value, password_value);

                formList.add(m_li);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
