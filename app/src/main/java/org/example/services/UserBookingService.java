package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

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
    private static final String USERS_PATH = "D:/Irctc project/IRCTC/app/src/main/java/org/example/localDB/Users.json";


    public UserBookingService(User user1) throws IOException {
        this.user=user1;
        File users= new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {return user1.getName().equals(user.getName())
                &&
                        UserServiceUtil.checkPassword(user.getPassword(),user1}).findFirst();
    return foundUser.isPresent();})
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
