package org.example.metroMain;

import java.util.List;

public class MetroStation {
    private String name;
    private String line;
    private boolean hasConnection;

    public MetroStation(String name, String line, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    @Override
    public String toString() {
        return "MetroStation{" +
                "name=" + name +
                ", line='" + line + '\'' +
                ", hasConnection=" + hasConnection +
                '}';
    }
}

