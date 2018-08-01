package objects;

import datastructure.SensorValue;
import interfaces.PatientMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Monitor implements PatientMonitor {


    ArrayList<Patient> patients =new ArrayList<>();
    HashMap<Sensor,ArrayList<String>> sensorValues= new HashMap<>();
    int  monitorPeriod=0;

    public int getMonitorPeriod(){
        return this.monitorPeriod;
    }
    public boolean setMonitorPeriod(int monitorPeriod){
        this.monitorPeriod = monitorPeriod;
        return true;
    }

    public boolean addPatient(Patient patient){
        this.patients.add(patient);
        return true;
    }


    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public boolean writeToDB(Sensor sensor,String datavalue) {

        if(this.sensorValues.containsKey(sensor)){
            //append after old
            this.sensorValues.get(sensor).add(datavalue);
        }else {
            //Create new
            ArrayList values= new ArrayList();
            values.add(datavalue);
            this.sensorValues.put(sensor,values);
        }

        return true;
    }

    @Override
    public void printDB() {
        for (Patient eachPatient:patients) {

            System.out.println("patient "+eachPatient.getName());

            for (Sensor eachSensor:
                    eachPatient.getSensors()) {
                if(sensorValues.containsKey(eachSensor)){
                    System.out.println(eachSensor.getSensorType()+" "+eachSensor.getName());
                    ArrayList<String > values = sensorValues.get(eachSensor);
                    for (String eachValue:values
                         ) {
                        System.out.println(eachValue);
                    }
                }
               }

        }
    }

    @Override
    public boolean monitor() {
        for(int time=0;time<=monitorPeriod;time++){
            for (Patient eachPatient:patients) {
                if(time%eachPatient.getPeriod()==0){
                    for (Sensor eachSensor:eachPatient.getSensors()) {
                        //Read each sensor value

                        try {
                            SensorValue result = eachSensor.read();
                            //Check status
                            if(result.getSensorStatus()==-1){
                                showMessage("["+time+"] "+eachSensor.getName()+" falls");
                            }else if(result.getSensorStatus()==1){
                                showMessage("[" + time + "] " + eachPatient.getName() + " is in danger! Cause: " + eachSensor.getName() + " " + result.getSensorValue());
                            }
                            writeToDB(eachSensor,"[" + time + "] "+result.getSensorValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return false;
    }

    public void printpatientInfo(){

        for (Patient eachPatient:patients) {
            System.out.println("---------Patient Info---------");
            System.out.println("Name: "+eachPatient.getName());
            System.out.println("Period: "+eachPatient.getPeriod());
            for (Sensor eachSensor:
            eachPatient.getSensors()) {
                System.out.println("---------Sensor Info-----------");
                System.out.println("SensorType: "+eachSensor.getSensorType());
                System.out.println("SensorName: "+eachSensor.getName());
                System.out.println("Sensor Lowerbound: "+eachSensor.getLowerbound());
                System.out.println("Sensor Upperbound: "+eachSensor.getUpperbound());
                System.out.println("Sensor DataSetName: "+eachSensor.getDatasetName());
            }

        }
    }
}
