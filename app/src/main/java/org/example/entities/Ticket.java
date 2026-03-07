package org.example.entities;

import java.util.Date;

public class Ticket {
    private String ticketId;

    private String userId;

    //start of the train route
    private String source;

    //end of the train route
    private String destination;

    private String dateOfTravel;

    private Train trainNumber;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public Train getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Train trainNumber) {
        this.trainNumber = trainNumber;
    }

    //constructor

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel, Train trainNumber) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.trainNumber = trainNumber;
    }


    //method to return the Tickets information
    public String getTicketInfo(){
        return String.format("TicketId: %s belongs to User %s from %s to %s on %s.",ticketId,userId,source,destination,dateOfTravel)
    }
}
