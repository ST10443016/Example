package com.mycompany;

import javax.swing.JOptionPane;

/**
 *
 * @author Cristiano Tavares ST10443016
 */
public class LoginManager {
    UserManager manager = new UserManager();

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    protected void showError(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    protected void showInfo(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean Register(String name, String surname, String username, String password, String number) {
        boolean correct = checkUserName(username);
        boolean strong = checkPasswordComplexity(password);
        boolean valid = checkCellPhoneNumber(number);

        if (!correct) {
            showError(
                "Username is not correctly formatted.\nPlease ensure that your username contains an underscore and is no more than five characters in length.",
                "Invalid Username");
            return false;
        }

        if (!strong) {
            showError(
                "Password is not correctly formatted.\nPlease ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                "Weak Password");
            return false;
        }

        if (!valid) {
            showError(
                "Cell phone number incorrectly formatted or does not contain international code.",
                "Invalid Phone Number");
            return false;
        }

        manager.insert(new User(name, surname, username, password, number));
        return true;
    }

    private boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    private boolean checkCellPhoneNumber(String number) {
        return number.matches("\\+27\\d{9}");
    }

    private boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*(),.?:{}|<>].*");
    }

    public boolean Login(String username, String password) {
        boolean match = passwordsMatch(username, password);

        if (!match) {
            showError("Incorrect username and password.", "Login Failed");
            return false;
        }
        return true;
    }

    private boolean passwordsMatch(String username, String password) {
        for (int i = 0; i < manager.count; i++) {
            User user = manager.data[i];
            if (user != null && user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    String userWelcome = "Welcome " + user.getName() + ", " + user.getSurname() + ". It is great to see you again.";
                    showInfo(userWelcome, "Login Successful");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    // Test-accessible duplicates of private methods
    public boolean checkUserNameTest(String username) {
        return checkUserName(username);
    }

    public boolean checkPasswordComplexityTest(String password) {
        return checkPasswordComplexity(password);
    }

    public boolean checkCellPhoneNumberTest(String number) {
        return checkCellPhoneNumber(number);
    }
}