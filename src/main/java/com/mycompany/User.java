package com.mycompany;

/**
 *
 * @author Cristiano Tavares ST10443016
 */

public class User {
    private String _name;
    private String _surname;
    private String _username;
    private String _password;
    private String _number;
    
    public User (
        String name,
        String surname,
        String username,
        String password,
        String number
    ){
        _name = name;
        _surname = surname;
        _username = username;
        _password = password;
        _number = number;
    }
    
    public String getName(){
        return _name;
    }
    
    public String getSurname(){
        return _surname;
    }
    
    public String getUsername(){
        return _username;
    }
    
    public String getPassword(){
        return _password;
    }
    
    public String getNumber(){
        return _number;
    }
}