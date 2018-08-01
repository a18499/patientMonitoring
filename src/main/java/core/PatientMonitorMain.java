package core;


import objects.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;


public class PatientMonitorMain {

    Monitor patientMonitor = new Monitor();

    private ArrayList<String> readFile(String filepath){
        ArrayList<String> result = new ArrayList<>();
        if (Files.exists(Paths.get(filepath))){
            try {
                File inpuFile = new File(filepath);
                FileReader readFile = new FileReader(inpuFile);
                BufferedReader test = new BufferedReader(readFile);
                String eachline;

                while ((eachline = test.readLine()) != null) {
                    result.add(eachline);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.err.println("Input File Does Not exists");
        }
        return result;
    }

    protected boolean init (String inputFilePath,String inputDataSourcePath){

        ArrayList<String> inputs = readFile(inputFilePath);

        int count=0;

        Patient eachPatient=null;
        for (String each:inputs) {
            if(count==0){
                patientMonitor.setMonitorPeriod(Integer.parseInt(each.trim()));
                count++;
            }else {
                if(each.trim().contains("patient")){
                    //spilt command
                    if(eachPatient != null){

                        patientMonitor.addPatient(eachPatient);
                    }
                    eachPatient = new Patient();
                    String [] patient = each.split(" ");
                    int counter=0;
                    for (String patientInfo: patient) {

                        if(counter==0){

                        }else if(counter==1){
                            eachPatient.setName(patientInfo.trim());
                        }else if(counter==2){
                            eachPatient.setPeriod(Integer.parseInt(patientInfo.trim()));
                        }
                        counter ++;
                    }
                }else if(each.trim().contains("BloodPressureSensor")){
                    //Spilt it
                    String[] bloodPressureSensor= each.split(" ");
                    Sensor bloodSensor = new BloodSensor();
                    bloodSensor.setName(bloodPressureSensor[1]);
                    bloodSensor.setDatasetname(bloodPressureSensor[2]);
                    bloodSensor.setLowerbound(Double.parseDouble(bloodPressureSensor[3]));
                    bloodSensor.setUpperbound(Integer.parseInt(bloodPressureSensor[4]));
                    ((BloodSensor) bloodSensor).init(bloodPressureSensor[2]);
                   eachPatient.addSensor(bloodSensor);
                }else if(each.trim().contains("PulseSensor")){
                    //Spilt it
                    String[] pulseSensorString = each.split(" ");
                    Sensor pulseSensor = new PulseSensor();
                    pulseSensor.setName(pulseSensorString[1]);
                    pulseSensor.setDatasetname(pulseSensorString[2]);
                    pulseSensor.setLowerbound(Double.parseDouble(pulseSensorString[3]));
                    pulseSensor.setUpperbound(Double.parseDouble(pulseSensorString[4]));
                    ((PulseSensor) pulseSensor).init(pulseSensorString[2]);
                    eachPatient.addSensor(pulseSensor);
                }else if(each.trim().contains("TemperatureSensor")){
                    //Spilt it
                    String[] temperatureSensorString = each.split(" ");
                    Sensor temperatureSensor = new TemperateureSensor();
                    temperatureSensor.setName(temperatureSensorString[1]);
                    temperatureSensor.setDatasetname(temperatureSensorString[2]);
                    temperatureSensor.setLowerbound(Double.parseDouble(temperatureSensorString[3]));
                    temperatureSensor.setUpperbound(Double.parseDouble(temperatureSensorString[4]));
                    ((TemperateureSensor) temperatureSensor).init(temperatureSensorString[2]);
                    eachPatient.addSensor(temperatureSensor);
                }
            }

        }
        //Add the last one
        if(eachPatient!=null){
            patientMonitor.addPatient(eachPatient);
        }


       // patientMonitor.printpatientInfo();

        return true;
    }
    public void start(){

        patientMonitor.monitor();
        patientMonitor.printDB();
    }
    public static void main(String args[]){

        if(args.length!=1){
            System.err.println("Argument wrong");
            System.out.println("EX: XX.jar [inputFilePath]");
            return;
        }

        String inputFilePath = args[0];

        PatientMonitorMain patientMonitor = new PatientMonitorMain();
        patientMonitor.init(inputFilePath,"TestingData\\BloodPressureData1.dataset");
        patientMonitor.start();
    }
}

