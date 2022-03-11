package ru.job4j.tracker.poly;

public interface Transport {
    void drive();

    void driveWithPass(int numOfPass);

    int refuel(int fuel);
}
