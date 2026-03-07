package org.example.entities;

import java.sql.Time;
import java.util.*;
import java.util.Date;

public class Train {
    private String trainId;

    private String trainNo;

//    private Date departureTime;

//    private Date arrivalTime;

    private List<List<Integer>> seats;

    private Map<String, String> stationTimes;

    private List<String> stations;

//getters and setters---------------------------------------------------------------------------
    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
//constructor----------------------------------------------------------------------
    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    //to get the info of a train
    public String getTrainInfo(){
        return String.format("TrainId: %s TrainNo: %s",trainId,trainNo );
    }
}
