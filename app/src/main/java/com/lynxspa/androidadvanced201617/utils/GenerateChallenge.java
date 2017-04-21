package com.lynxspa.androidadvanced201617.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Esami on 21/04/2017.
 */
public class GenerateChallenge {


    public static Integer getFirstRandomNumber() {

        int firstNumber=0;

        List<Integer> listNumber = getListNumbers();
        for(int num:listNumber){
            int idx = new Random().nextInt(listNumber.size());
            firstNumber= (listNumber.get(idx));
        }


        int secondNumber=0;
        for(int num:listNumber){
            int idx = new Random().nextInt(listNumber.size());
            secondNumber = (listNumber.get(idx));
        }

        return firstNumber;
    }

    public static Integer getSecondRandomNumber() {

        List<Integer> listNumber= getListNumbers();
        int secondNumber=0;
        for(int num:listNumber){
            int idx = new Random().nextInt(listNumber.size());
            secondNumber = (listNumber.get(idx));
        }
        return secondNumber;
    }

    public static String getChallengeOperation() {

        List<String> listNumber= getListOperations();
        String operation="";
        for(String op:listNumber){
            int idx = new Random().nextInt(listNumber.size());
            operation = (listNumber.get(idx));
        }
        return operation;
    }


    public static List<Integer> getListNumbers(){
        List<Integer> listNumber=new ArrayList<Integer>();
        listNumber.add(1);
        listNumber.add(2);
        listNumber.add(3);
        listNumber.add(4);
        listNumber.add(5);
        listNumber.add(6);
        listNumber.add(7);
        listNumber.add(8);
        listNumber.add(9);
        listNumber.add(10);
        return listNumber;
    }

    public static List<String> getListOperations(){
        List<String> listOperations=new ArrayList<String>();
        listOperations.add("+");
        listOperations.add("-");
        listOperations.add("x");
        return listOperations;
    }


    public static int checkChallenge(int firstNumber,String operation,int secondNumber){
        int somma=0;
        if(operation.equals("+")){
            somma=firstNumber+secondNumber;
        }else if(operation.equals("-")){
                somma=firstNumber-secondNumber;
        }else if(operation.equals("x")){
            somma=firstNumber*secondNumber;
        }
        return somma;
    }
}
