package com.mycompany;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristiano Tavares ST10443016
 */

public class App {
    LoginManager account = new LoginManager();
    
    public void Run() {
        while (true) {
            String[] options = {"Exit", "Register", "Login"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Select an option:",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );
            if (choice == 0 || choice == JOptionPane.CLOSED_OPTION) {
                break;
            }
            handleInput(choice);
        }
    }

    public void handleInput(int choice) {
        if (choice == 1) { // Register
            JOptionPane.showMessageDialog(null, "Registration");

            String name = JOptionPane.showInputDialog("Enter name:");
            if (name == null) return;

            String surname = JOptionPane.showInputDialog("Enter surname:");
            if (surname == null) return;

            String username = JOptionPane.showInputDialog("Enter username:");
            if (username == null) return;

            String password = JOptionPane.showInputDialog("Enter password:");
            if (password == null) return;

            String number = JOptionPane.showInputDialog("Enter cellphone number:");
            if (number == null) return;

            boolean success = account.Register(name, surname, username, password, number);
            if (success) {
                JOptionPane.showMessageDialog(null, "Registration successful");
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed");
            }
        } else if (choice == 2) { // Login
            JOptionPane.showMessageDialog(null, "Login");

            String username = JOptionPane.showInputDialog("Enter username:");
            if (username == null) return;

            String password = JOptionPane.showInputDialog("Enter password:");
            if (password == null) return;

            boolean success = account.Login(username, password);
            if (success) {
                //The assisgnment wants us to have 3 pop-ups upon successful login(Login successful, Welcome to QuickChat, Welcome {Name} {Surname})
                JOptionPane.showMessageDialog(null, "Login successful");

                // ====== Part 2 ======
                JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

                MessageManager messageManager = new MessageManager();
                try {
                    int maxMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
                    messageManager.setMaxMessages(maxMessages);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Exiting.");
                    return;
                }

                while (true) {
                    String option = JOptionPane.showInputDialog(
                        "Select an option:\n1) Send Message\n2) Show recently sent messages\n3) Quit"
                    );

                    if (option == null) return;

                    if (option.equals("1")) {
                        messageManager.handleSendMessage();
                    } else if (option.equals("2")) {
                        messageManager.handleShowMessages();
                    } else if (option.equals("3")) {
                        messageManager.handleQuit();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid option selected.");
                    }
                }
                // ====== Part 2 End ======
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid menu option selected");
        }
    }
}
