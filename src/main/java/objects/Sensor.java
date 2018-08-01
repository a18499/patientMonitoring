package objects;

import datastructure.SensorValue;

import java.io.*;

public abstract class Sensor {
    private String name;
    private double lowerbound;
    private double upperbound;
    private String datasetName;

    public void setDatasetname(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setUpperbound(double upperbound) {
        this.upperbound = upperbound;
    }

    public double getUpperbound() {
        return upperbound;
    }

    public void setLowerbound(double lowerbound) {
        this.lowerbound = lowerbound;
    }

    public double getLowerbound() {
        return lowerbound;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    abstract public SensorValue read() throws IOException;

    abstract public String getSensorType();
}
