package com.mycompany;

/**
 *
 * @author Cristiano Tavares ST10443016
 */

public class UserManager {
    int capacity = 2;
    int count = 0;
    
    User[] data = new User[capacity];
    
    public void insert(User user){
        data[count] = user;
        count++;
    }
}
