package domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Fitness {
    private String date;
    private Integer noOfSteps;
    private Double hoursOfSleep;
    private List<String> physicalActivities;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNoOfSteps() {
        return noOfSteps;
    }

    public void setNoOfSteps(Integer noOfSteps) {
        this.noOfSteps = noOfSteps;
    }

    public Double getHoursOfSleep() {
        return hoursOfSleep;
    }

    public void setHoursOfSleep(Double hoursOfSleep) {
        this.hoursOfSleep = hoursOfSleep;
    }

    public List<String> getPhysicalActivities() {
        return physicalActivities;
    }

    public void setPhysicalActivities(List<String> physicalActivities) {
        this.physicalActivities = physicalActivities;
    }

    public Fitness(String date, Integer noOfSteps, Double hoursOfSleep, List<String> physicalActivities) {
        this.date = date;
        this.noOfSteps = noOfSteps;
        this.hoursOfSleep = hoursOfSleep;
        this.physicalActivities = physicalActivities;
    }

    @Override
    public String toString() {
        return "Fitness{" +
                "date='" + date + '\'' +
                ", noOfSteps=" + noOfSteps +
                ", hoursOfSleep=" + hoursOfSleep +
                ", physicalActivities=" + physicalActivities +
                '}';
    }
}
