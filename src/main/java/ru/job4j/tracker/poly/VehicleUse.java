package ru.job4j.tracker.poly;

public class VehicleUse {
    public static void main(String[] args) {
        Vehicle autobus = new Autobus();
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle[] vehicles = new Vehicle[]{autobus, plane, train};
        for (Vehicle veh : vehicles) {
            veh.move();
        }
    }
}
