package com.example.mdbproject1;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static ArrayList<String> numberNamesFromList(ArrayList<String> listOfNames, int number) {

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        Random rand = new Random();
        int numberOfElements = number ;

        for (int i = 0; i < numberOfElements; i++) {

            int randomIndex = rand.nextInt(listOfNames.size());
            while(indices.contains(randomIndex)){
                randomIndex = rand.nextInt(listOfNames.size());
            }
            String randomElement = (String)listOfNames.get(randomIndex);
            indices.add(randomIndex);
            names.add(randomElement);




        }
        return names;
    }

    public static ArrayList namesFromFile(Context c) throws IOException {
        ArrayList names = new ArrayList();
        AssetManager assetManager = c.getAssets();
        InputStream is = assetManager.open("names.txt");
        Scanner nameScanner = new Scanner(is);
        while (nameScanner.hasNextLine()) {
            String name = nameScanner.nextLine();
            names.add(name);
        }

        Log.d("game", names.toString());
        return names;
    }
    public static boolean fileExists(String fileName, Context context) {
        try {
            context.openFileInput(fileName);
        }
        catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static String[] namesToList(String names){
        String[] listOfNames = names.split(System.getProperty("line.separator"));
        //System.out.println(listOfNames);
        return listOfNames;

    }

    public static boolean intPresent(int toCheckValue, ArrayList<Integer> arr){

        for(int nothingCould = 0; nothingCould<arr.size(); nothingCould++){
            if(toCheckValue == arr.get(nothingCould))
                return true;
        }
        return false;
    }


    /* IMAGE STUFF -----------------------------------------------
    *
    *
    *
    *
     */
    public static Drawable getImage(Context c, String name) {
        String imgName = name.toLowerCase().replace(" ", "");
        Log.d("image Name",imgName);
        int resourceID = c.getResources().getIdentifier(imgName, "drawable", c.getPackageName());
        Log.d("resource ID"," "+resourceID);
        return c.getResources().getDrawable(resourceID);
    }


}
