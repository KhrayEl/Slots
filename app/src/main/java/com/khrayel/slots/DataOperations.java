package com.khrayel.slots;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class DataOperations
    {
        public class QuoteBank
            {

                private Context mContext;

                public QuoteBank (Context context)
                    {
                        this.mContext = context;
                    }

                public List<String> readLine (String path)
                    {
                        List<String> mLines = new ArrayList<>();

                        AssetManager am = mContext.getAssets();

                        try
                            {
                                InputStream inputStream = am.open(path);
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                String line;

                                while ((line = reader.readLine()) != null)
                                    mLines.add(line);
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                        return mLines;
                    }


//        void SaveDataToFIle (int number)
//            {
//                String filename = "data";
//                String fileContents = Integer.toString(number);
//
//                try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE))
//                    {
//                        fos.write(fileContents.getBytes());
//                    } catch (FileNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//            }
//
//        String LoadDataFromFile ()
//            {
//                FileInputStream fis = null;
//                String contents;
//                try
//                    {
//                        fis = this.openFileInput("data");
//                    } catch (FileNotFoundException e)
//                    {
//                        //  e.printStackTrace();
//                        return getString(R.string.default_record);
//                    }
//
//                InputStreamReader inputStreamReader =
//                        new InputStreamReader(fis, StandardCharsets.UTF_8);
//                StringBuilder stringBuilder = new StringBuilder();
//                try (BufferedReader reader = new BufferedReader(inputStreamReader))
//                    {
//                        String line = reader.readLine();
//                        while (line != null)
//                            {
//                                stringBuilder.append(line)/*.append('\n')*/;
//                                line = reader.readLine();
//                            }
//                    } catch (IOException e)
//                    {
//                        // Error occurred when opening raw file for reading.
//                    } finally
//                    {
//                        contents = stringBuilder.toString();
//                    }
//                try
//                    {
//                        inputStreamReader.close();
//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                return contents;
//            }
            }
        private void writeToFile(String data,Context context) {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write(data);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }


        private String readFromFile(Context context) {

            String ret = "";

            try {
                InputStream inputStream = context.openFileInput("config.txt");

                if ( inputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        stringBuilder.append("\n").append(receiveString);
                    }

                    inputStream.close();
                    ret = stringBuilder.toString();
                }
            }
            catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
            } catch (IOException e) {
                Log.e("login activity", "Can not read file: " + e.toString());
            }

            return ret;
        }


    }
