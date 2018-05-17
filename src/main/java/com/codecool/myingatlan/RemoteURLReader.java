package com.codecool.myingatlan;


import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RemoteURLReader {

    public RemoteURLReader() {}

    public String readFromURL(String path) throws IOException {
        URL url = new URL(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String readLine = reader.readLine();
        reader.close();
        return readLine;
    }
}

