package org.example.CSVParser;

import org.example.metroMain.MetroStationDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public List<MetroStationDate> parseCSVFile(String filePath) {

        List<MetroStationDate> data = new ArrayList<>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
           String line;
           while ((line = bufferedReader.readLine()) != null) {
               String[] value = line.split(",");
               if (value.length >= 2) {
                   String name = value[0];
                   String depth = value[1];

                   data.add(new MetroStationDate(name, depth));
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
