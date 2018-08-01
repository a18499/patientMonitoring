package objects;

import datastructure.SensorValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TemperateureSensor extends Sensor {
    private BufferedReader datasetReader;
    public boolean init(String datasetname){
        if(Files.exists(Paths.get(datasetname))){

            File inputFile = new File(datasetname);
            try {
                FileReader readFile = new FileReader(inputFile);
                datasetReader = new BufferedReader(readFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //System.out.println("init sussed");
        }else {
            System.err.println("File does not found");
        }

        return true;
    }
    @Override
    public SensorValue read() throws IOException {
        SensorValue readResult = new SensorValue();
        String sensorvalue = this.datasetReader.readLine();
        if(sensorvalue!=null){
            double sensorIntValue = Double.parseDouble(sensorvalue);
            //Check value
            if(sensorIntValue==-1){
                readResult.setSensorValue(sensorIntValue);
                readResult.setSensorStatus(-1);
            }else if(sensorIntValue<this.getLowerbound()||sensorIntValue>this.getUpperbound()){
                readResult.setSensorStatus(1);
                readResult.setSensorValue(sensorIntValue);
            }else {
                readResult.setSensorValue(sensorIntValue);
                readResult.setSensorStatus(0);
            }
        }else{
            //If it is the end of file
            readResult.setSensorValue(-1);
            readResult.setSensorStatus(-1);
        }

        return readResult;
    }

    @Override
    public String getSensorType() {
        return "TemperatureSensor";
    }
}
