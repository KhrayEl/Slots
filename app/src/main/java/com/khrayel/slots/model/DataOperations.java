package com.khrayel.slots.model;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileReader;


import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public interface DataOperations
    {

        default void writeStringToFile (
                String data, String filename
                , Context context
                                       )
            {

                String fileContents = data;
                String path =
                        // context.getFilesDir()+"/"+
                        filename;

/*
                PrintWriter out1 = null;
                try
                    {
                        out1 = new PrintWriter(new FileWriter(path));
                        out1.write(data);

                    } catch (Exception ex)
                    {
                        System.out.println("error: " + ex.toString());
                    }
*/

                try (FileOutputStream fos = context.openFileOutput(path, Context.MODE_PRIVATE))
                    {
                        fos.write(fileContents.getBytes());
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

            }


        default String readStringFromFile (String filename, Context context)
            {
                FileInputStream fis = null;

                try
                    {
                        fis = context.openFileInput(filename);
                    } catch (FileNotFoundException e)
                    {
                        return "";
                    }
                InputStreamReader inputStreamReader =
                        new InputStreamReader(fis, StandardCharsets.UTF_8);

                StringBuilder everything = new StringBuilder();
                String line;
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
                    {
                        while ((line = bufferedReader.readLine()) != null)
                            {
                                everything.append(line);

                            }

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                return everything.toString();

            }

    }
