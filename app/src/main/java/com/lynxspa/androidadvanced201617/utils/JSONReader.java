package com.lynxspa.androidadvanced201617.utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Esami on 21/04/2017.
 */
public class JSONReader {

    public static Map<String,String> readFromFileJson(InputStreamReader jsonFile) {

        Map<String,String> jMapPasswords=new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(jsonFile);
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang= (JSONArray) jsonObject.get("Passwords");

            String row="";
            String col="";
            String valueCol="";

            for(int i=0;i<lang.size();i++){
                JSONObject obj= (JSONObject) lang.get(i);
                row = (String) obj.get("row");
                JSONArray values=(JSONArray) obj.get("values");

                for(int j=0;j<values.size();j++){
                    JSONObject objValues=(JSONObject) values.get(j);
                    Long cols=(Long) objValues.get("col");
                    col=cols.toString();

                    Long password=(Long) objValues.get("value");
                    valueCol=password.toString();
                }

                String indexCell = row+"-"+col;
                jMapPasswords.put(indexCell, valueCol);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jMapPasswords;
    }

    /*public Map<String,String> getAllPasswords(){
        Map<String, String> map=new HashMap<>();
            map.put("A-1", "1293");
            map.put("A-2", "5354");
            map.put("A-3", "2344");
            map.put("A-4", "4543");
            map.put("A-5", "7636");
            map.put("A-6", "2523");
            map.put("A-7", "3242");
            map.put("A-8", "3346");
            map.put("A-9", "2443");
            map.put("A-10", "6222");
            map.put("B-1", "4334");
            map.put("B-2", "2344");
            map.put("B-3", "2346");
            map.put("B-4", "8577");
            map.put("B-5", "7555");
            map.put("B-6", "7566");
            map.put("B-7", "3454");
            map.put("B-8", "4454");
            map.put("B-9", "4443");
            map.put("B-10", "3344");
            map.put("C-1", "2678");
            map.put("C-2", "4234");
            map.put("C-3", "4266");
            map.put("C-4", "2357");
            map.put("C-5", "3533");
            map.put("C-6", "4565");
            map.put("C-7", "4333");
            map.put("C-8", "2355");
            map.put("C-9", "6665");
            map.put("C-10", "5466");
            map.put("D-1", "6553");
            map.put("D-2", "3334");
            map.put("D-3", "2233");
            map.put("D-4", "4688");
            map.put("D-5", "3334");
            map.put("D-6", "5542");
            map.put("D-7", "6777");
            map.put("D-8", "5543");
            map.put("D-9", "2322");
            map.put("D-10", "4455");
            map.put("E-1", "6363");
            map.put("E-2", "6654");
            map.put("E-3", "2333");
            map.put("E-4", "566");
            map.put("E-5", "2344");
            map.put("E-6", "3234");
            map.put("E-7", "5322");
            map.put("E-8", "3455");
            map.put("E-9", "2524");
            map.put("E-10", "4445");
            map.put("F-1", "4654");
            map.put("F-2", "6653");
            map.put("F-3", "5654");
            map.put("F-4", "5667");
            map.put("F-5", "2233");
            map.put("F-6", "8753");
            map.put("F-7", "5222");
            map.put("F-8", "3455");
            map.put("F-9", "1432");
            map.put("F-10", "3453");
            map.put("G-1", "2343");
            map.put("G-2", "2365");
            map.put("G-3", "3423");
            map.put("G-4", "8334");
            map.put("G-5", "4356");
            map.put("G-6", "4455");
            map.put("G-7", "4533");
            map.put("G-8", "4443");
            map.put("G-9", "4533");
            map.put("G-10", "4345");
        return map;
    }*/
}
