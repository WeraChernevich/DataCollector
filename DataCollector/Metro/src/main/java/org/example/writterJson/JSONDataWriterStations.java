package org.example.writterJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.metroMain.MetroStationDate;
import org.example.metroMain.MetroStationDepth;
import org.example.metroMain.MetroStation;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONDataWriterStations {
    private Map<String, JSONObject> stationDataMap;

    public JSONDataWriterStations() {

        stationDataMap = new HashMap<>();
    }

    public void addMetroStationData(MetroStation metroStation) {
        JSONObject station = new JSONObject();
        station.put("line", metroStation.getLine());
        station.put("hasConnect", metroStation.isHasConnection());

        stationDataMap.put(metroStation.getName(), station);
    }

    public void addCSVData(MetroStationDate metroStationDate) {
        String name = metroStationDate.getName();
        String date = metroStationDate.getDate();

        JSONObject station = stationDataMap.getOrDefault(name, new JSONObject());
        station.put("date", date);
        stationDataMap.put(name, station);
    }

    public void addJSONData(MetroStationDepth metroStationDepth) {
        String name = metroStationDepth.getName();
        String depth = metroStationDepth.getDepth();

        JSONObject station = stationDataMap.getOrDefault(name, new JSONObject());
        station.put("depth", depth);
        stationDataMap.put(name, station);
    }

    public void writeToJsonFile(String fileName) {
        JSONArray stationArray = new JSONArray();
        for (Map.Entry<String, JSONObject> entry : stationDataMap.entrySet()) {
            JSONObject stationObject = new JSONObject();
            stationObject.put("name", entry.getKey());

            JSONObject stationProperties = entry.getValue();

            for (Object key : stationProperties.keySet()) {
                String propertyKey = (String) key;
                Object propertyValue = stationProperties.get(propertyKey);
                stationObject.put(propertyKey, propertyValue);
            }
            stationArray.add(stationObject);
        }

        JSONObject resultObject = new JSONObject();
        resultObject.put("stations", stationArray);

        try (FileWriter file = new FileWriter(fileName)){
            file.write(resultObject.toString(4));
            System.out.println("Json create 2" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
