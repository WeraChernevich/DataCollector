package org.example.metroMain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MetroParser {

    public List<MetroLine> parseMetroLine() throws IOException {
        Document document = Jsoup.connect("https://skillbox-java.github.io/").get();
        Elements linesMetro = document.select(".js-metro-line");
        List<MetroLine> metroLines = new ArrayList<>();
        for (Element lineMetro : linesMetro) {
            String number = lineMetro.attr("data-line");
            String name = lineMetro.text();
            MetroLine metroLine = new MetroLine(name, number);
            metroLines.add(metroLine);
        }
        return metroLines;
    }

    public List<MetroStation> parseMetroStation() throws IOException {
        Document document = Jsoup.connect("https://skillbox-java.github.io/").get();
        Elements stations = document.select("div.js-metro-stations");
        List<MetroStation> metroStations = new ArrayList<>();

        for (Element station : stations) {
            String lineNumber = station.attr("data-line");
            Elements stationElements = station.select("p.single-station");

            for (Element stationElement : stationElements) {
                String name = stationElement.select(".name").text();
                boolean hasTransfer = !stationElement.select(".t-icon-metroln[title*=переход]").isEmpty();

                metroStations.add(new MetroStation(name, lineNumber, hasTransfer));
            }
        }

        return metroStations;
    }
}