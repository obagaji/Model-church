package com.jtc.Model.church.churchEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Filephone

{
    public Filephone() {}
// creating a file and a buffered file writer
    public void writeUsingBufferedWriter(String[] data)
    {
        File file = new File("C:/PhoneNumberwriter.txt");

        FileWriter fr = null;

        BufferedWriter br = null;

        try
        {
            fr = new FileWriter(file);

            br = new BufferedWriter(fr);

            for(int i = 0; i < data.length; ++i)
            {
                String dataWithNewLine = data[i];

                br.write(dataWithNewLine);

                }
        }
        catch (IOException varIO)
        {
            varIO.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
                fr.close();
            }
            catch (IOException varIO)
            {
                varIO.printStackTrace();
            }

            }

        }
    }

