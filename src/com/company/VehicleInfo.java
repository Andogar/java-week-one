package com.company;

public class VehicleInfo {
    private int VIN;
    private double odometer;
    private double consumption;
    private double lastOilChangeMiles;
    private double engineSize;

    public VehicleInfo() {
    }

    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getLastOilChangeMiles() {
        return lastOilChangeMiles;
    }

    public void setLastOilChangeMiles(double lastOilChangeMiles) {
        this.lastOilChangeMiles = lastOilChangeMiles;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }
}
