package datastructure;

public class SensorValue {
    //0: sensor normal
    //-1: sensor fail
    //1: sensor danger
    private int sensorStatus;
    private double sensorValue;

    public void setSensorStatus(int sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }

    public double getSensorValue() {
        return sensorValue;
    }

    public int getSensorStatus() {
        return sensorStatus;
    }
}
