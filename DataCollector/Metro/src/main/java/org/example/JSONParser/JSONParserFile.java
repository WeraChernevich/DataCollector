package org.example.JSONParser;


import org.example.metroMain.MetroStationDepth;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParserFile {
   public static List<MetroStationDepth> parseJsonData(String filePath) {
      List<MetroStationDepth> metroStationDepthList = new ArrayList<>();
      JSONParser parser = new JSONParser();

      try(BufferedReader reader = new BufferedReader (new FileReader(filePath))) {
         JSONArray jsonArray = (JSONArray) parser.parse(reader);

         for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String stationName = (String) jsonObject.get("station_name");
            String depth = (String) jsonObject.get("depth");

            MetroStationDepth metroStationDepth = new MetroStationDepth(stationName, depth);
            metroStationDepthList.add(metroStationDepth);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return metroStationDepthList;
   }
}

