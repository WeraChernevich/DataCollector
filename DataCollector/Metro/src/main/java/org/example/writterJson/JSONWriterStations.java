package org.example.writterJson;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONWriterStations {
    private Map<String, List<String>> stationsByLines = new HashMap<>();

    public void addStation(String line, String station) {
       List<String> stationList = stationsByLines.get(line);
       if (stationList == null) {
           stationList = new ArrayList<>();
           stationsByLines.put(line, stationList);
       }
       stationList.add(station);
    }

    public void createJSONFile() {
        JSONObject stationsJSON = new JSONObject();
        JSONObject stationsObject = new JSONObject();
        for (Map.Entry<String, List<String>> entry : stationsByLines.entrySet()) {
            stationsObject.put(entry.getKey(), entry.getValue());
        }
        stationsJSON.put("stations", stationsObject);

        try (FileWriter stationsWriter = new FileWriter("map.json")){
            stationsWriter.write(stationsJSON.toString(4));
            System.out.println("JSON create 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
