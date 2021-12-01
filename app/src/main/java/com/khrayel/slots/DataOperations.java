package com.khrayel.slots;


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
                /*, Context context*/
                                       )
            {

                String fileContents = data;

                PrintWriter out1 = null;
                try
                    {
                        out1 = new PrintWriter(new FileWriter(filename));
                        out1.write(data);

                    } catch (Exception ex)
                    {
                        System.out.println("error: " + ex.toString());
                    }


//             try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE))
//                    {
//                        fos.write(fileContents.getBytes());
//                    } catch (FileNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }

            }


        default String readStringFromFile (String filename)
            {
                File file=new File(filename);
                if(!file.exists())
                    {
                        return "";
                    }
                StringBuilder everything = new StringBuilder();
                String line;
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
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
