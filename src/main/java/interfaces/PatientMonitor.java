package interfaces;


import objects.Sensor;

public interface PatientMonitor {

    void showMessage(String message);
    boolean writeToDB(Sensor sensor,String datavalue);
    void printDB();
    boolean monitor();



}
