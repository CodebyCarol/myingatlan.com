package com.codecool.myingatlan.services;


import com.codecool.myingatlan.RemoteURLReader;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.io.IOException;

//serves the python API calculcating avg price given the district and sqm
public class PriceCalculatorService {

    private String calculatorPath;
    private RemoteURLReader remoteURLReader;

    public PriceCalculatorService() {
    }

    public PriceCalculatorService(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }

    public PriceCalculatorService(String calculatorPath, RemoteURLReader remoteURLReader) {
        this.calculatorPath = calculatorPath == null ? "0" : calculatorPath ;
        this.remoteURLReader = remoteURLReader;
    }

    public int getAvgPrice(String symbol)throws IOException{
        String url = String.format(calculatorPath, symbol);
        String result = remoteURLReader.readFromURL(url);
        JSONObject json = new JSONObject(result);
        String price = json.get("price").toString();
        return Integer.parseInt(price);
    }

    public int getPriceDif(int databasePrice, int avgPrice) {
        int priceDif = databasePrice - avgPrice;
        return priceDif;
    }


}
