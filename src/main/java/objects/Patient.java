package objects;

import java.util.ArrayList;

public  class Patient {
    private ArrayList<Sensor> sensors = new ArrayList<>();
    private String name="no";
    private int period=0;

    public boolean setPeriod(int period){
        this.period = period;
        return true;
    }
    public int getPeriod(){
        return this.period;
    }

    public boolean setName(String patientname){
        this.name = patientname;
        return true;
    }
    public String getName(){
        return this.name;
    }

    public Boolean addSensor(Sensor sensor){
        sensors.add(sensor);
        return true;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }
}
