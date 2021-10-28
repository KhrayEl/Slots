package com.khrayel.slots;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class DataOperations {

    public JSONObject ReadStats()
    {
        JSONObject obj=new JSONObject();

        return obj;
    }

    String LoadDataFromFile(Context context) {
        FileInputStream fis = null;
        String contents;
        try {
            fis = context.openFileInput("data");
        } catch (FileNotFoundException e) {
            //  e.printStackTrace();
            return context.getString(R.string.default_record);
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line)/*.append('\n')*/;
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }
        return contents;
    }

    void SaveDataToFIle(int number, Context context) {
        String filename = "data";
        String fileContents = Integer.toString(number);
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}