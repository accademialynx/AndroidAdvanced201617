package com.lynxspa.androidadvanced201617.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Esami on 20/04/2017.
 */
public class RandomParam {

    public static String getRandomParam() {

        String letterRandom="";
        List<String> listLetter=getRandomLetter();
        for(String str:listLetter){
            int idx = new Random().nextInt(listLetter.size());
            letterRandom = (listLetter.get(idx));
        }

        List<String> listNumber =getRandomNumber();
        String randomNumber="";
        for(String str:listNumber){
            int idx = new Random().nextInt(listNumber.size());
            randomNumber = (listNumber.get(idx));
        }

        String string = letterRandom+"-"+randomNumber;

        return string;
    }

    public static List<String> getRandomLetter(){
        List<String> listLetter=new ArrayList<String>();
        listLetter.add("A");
        listLetter.add("B");
        listLetter.add("C");
        listLetter.add("D");
        listLetter.add("E");
        listLetter.add("F");
        listLetter.add("G");
        return listLetter;
    }

    public static List<String> getRandomNumber(){
        List<String> listNumber=new ArrayList<String>();
        listNumber.add("1");
        listNumber.add("2");
        listNumber.add("3");
        listNumber.add("4");
        listNumber.add("5");
        listNumber.add("6");
        listNumber.add("7");
        listNumber.add("8");
        listNumber.add("9");
        listNumber.add("10");
        return listNumber;
    }
}
