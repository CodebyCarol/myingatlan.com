package com.codecool.myingatlan.services;


import com.codecool.myingatlan.RemoteURLReader;

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
        this.calculatorPath = calculatorPath == null ? "" : calculatorPath ;
        this.remoteURLReader = remoteURLReader;
    }
}
