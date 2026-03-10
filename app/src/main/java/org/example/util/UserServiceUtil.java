package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String plainpassword){
        return BCrypt.hashpw(plainpassword, BCrypt.gensalt());
    }
    public static boolean checkPassword (String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
