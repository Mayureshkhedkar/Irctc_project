package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;
import java.io.File;

public class UserBookingService {

    //global user which is logged in for all the services provided iin this class
    private User user;

    private List<User> userList;


    //the texts in the json are created in a snake case and here in the entities
    // class the general notations used are of camel case so we need a object mapper
    //object mapper maps CAMAL CASE to SNAKE CASE
    private ObjectMapper objectMapper = new ObjectMapper();



    // this is the static user path which refeers to the local database created to get all the users from
    private static final String USERS_PATH = "src/main/java/org/example/localDB/Users.json";

    public UserBookingService() throws IOException {
        loaduser();
    }
    public UserBookingService(User user) throws IOException{
        this.user = user;
        loaduser();
    }

    public void loaduser() throws IOException{
        userList= objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }
    public boolean loginUser(User usertologin) {
        Optional<User> foundUser = userList.stream().filter(user1 -> {return user1.getName().equals(user.getName())
                        && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedpassword());})
                .findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
    try{
        userList.add(user1);
        saveUserListToFile();
        return Boolean.TRUE;
    }catch (IOException ex){
        return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws IOException {
            File usersFile= new File(UserBookingService.USERS_PATH);
            objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking(){
        user.printTicket();
    }

    public Boolean cancelBooking(String ticketId){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticketid to cancel");
        ticketId=s.next();
        if(ticketId == null || ticketId.isEmpty() ){
            System.out.println("Ticket ID cannot be null or empty.");
        return Boolean.FALSE;
        }
        String finalTicketID1 = ticketId;
        boolean removed = user.getTicketsBooked().removeIf(ticket-> ticket.getTicketId().equals(finalTicketID1));
        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if(removed){
            System.out.println("Ticket with Id"+ticketId+"has been canceled.");
            return Boolean.TRUE;
        }else {
            System.out.println("No ticket found with ID "+ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String sourceST, String destinationST) {
        try{
            TrainService trainService=new TrainService();
            return trainService.searchTrains(sourceST,destinationST);

        } catch (IOException ex) {
            return null;
        }
    }
    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }
    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
