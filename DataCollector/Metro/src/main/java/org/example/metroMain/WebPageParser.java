package org.example.metroMain;

import org.example.CSVParser.CSVParser;
import org.example.JSONParser.JSONParserFile;
import org.example.writterJson.JSONDataWriterStations;
import org.example.writterJson.JSONWriterStations;

import java.io.IOException;
import java.util.List;


public class WebPageParser {
    public static void main(String[] args) throws IOException {
        MetroParser metroParser = new MetroParser();
        List<MetroLine> metroLines = metroParser.parseMetroLine();
        for (MetroLine metroLine : metroLines) {
            System.out.println(metroLine.getNumber() + " " + metroLine.getName());
        }
        List<MetroStation> metroStations = metroParser.parseMetroStation();
        for (MetroStation metroStation : metroStations) {
            System.out.println(metroStation.getName() + " " + metroStation.getLine() + " " + metroStation.isHasConnection());
        }

        JSONWriterStations jsonWriterStations = new JSONWriterStations();

        for (MetroLine line : metroLines) {
            for (MetroStation station : metroStations) {
                if (station.getLine().equals(line.getNumber())) {
                    jsonWriterStations.addStation(line.getNumber(), station.getName());
                }
            }
        }
        jsonWriterStations.createJSONFile();

        JSONDataWriterStations JSONDataWriterStations = new JSONDataWriterStations();
        for (MetroStation station : metroStations) {
            JSONDataWriterStations.addMetroStationData(station);
        }
        CSVParser parser = new CSVParser();
        List<MetroStationDate> data = parser.parseCSVFile("data/0/5/dates-2.csv");
        for (MetroStationDate metroStationDate : data) {
            JSONDataWriterStations.addCSVData(metroStationDate);
        }

        List<MetroStationDepth> metroStationDepthList = JSONParserFile.parseJsonData("data/2/4/depths-1.json");
        for (MetroStationDepth metroStationDepth : metroStationDepthList) {
            JSONDataWriterStations.addJSONData(metroStationDepth);
        }

        JSONDataWriterStations.writeToJsonFile("station.json");
    }
}