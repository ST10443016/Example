package com.mycompany;
import java.util.Random;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristiano Tavares ST10443016
 */

public class MessageManager {
    private List<Message> messages = new ArrayList<>();
    private int totalMessagesSent = 0;
    private int maxMessages = 0;
    
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public String generateMessageId(){
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++){
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
    
    public boolean checkMessageId(String id){
        if(id.length() == 10){
            return true;
        }
        return false;
    }
    
    public int checkRecipientCell(String cell){
        if(cell != null && cell.startsWith("+") && cell.length() <= 12){
            return 1;
        } else {
            return 0;
        }
    }
    
    public String createMessageHash(Message message){
        String[] words = message.getText().split(" ");
        
        String firstWord;
        if (words.length > 0) {
            firstWord = words[0];
        } else {
            firstWord = "";
        }
        
        String lastWord;
        if (words.length > 1){
            lastWord = words[words.length - 1];
        } else {
            lastWord = firstWord;
        }
        
        return(message.getMessageId().substring(0, 2) + ":" + message.getMessageNum() + ":" + firstWord + lastWord).toUpperCase();
    }
    
    public String sendMessageOptions(){
        String[] options = {"Send Message", "Store Message", "Disregard Message"};
        
        int choice = JOptionPane.showOptionDialog(
            null,                                   //The parent component of the dialog.
            "Choose what to do with this message:", //The message shown to the user inside the dialog.
            "Message Options",                      //The title of the dialog window.
            JOptionPane.DEFAULT_OPTION,             //A flag telling the dialog to use a default button layout.
            JOptionPane.INFORMATION_MESSAGE,        //The icon type shown in the dialog.
            null,                                   //The icon to use.
            options,                                //An array of options that the user can choose from.
            options[0]                              //The default option that's initially selected.
        );
        return getStatusFromChoice(choice);
    }
    /**
     * Helper method to convert status to a testable message
     * Cannot automatically unit test a JOptionPane.showOptionDialog() without manual input. 
     * It's a GUI component that requires user interaction, which breaks automated testing workflows.
     */ 
    public String getStatusFromChoice(int choice) {
        if (choice == 0) {
            return "Sent";
        } else if (choice == 1) {
            return "Stored";
        } else {
            return "Disregarded";
        }
    }
    
    public void storeMessage(Message message){
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("messageId", message.getMessageId());
        jsonMessage.put("messageNum", message.getMessageNum());
        jsonMessage.put("recipient", message.getRecipient());
        jsonMessage.put("text", message.getText());
        jsonMessage.put("messageHash", message.getMessageHash());
        jsonMessage.put("status", message.getStatus());
        
        JSONArray messageList = new JSONArray();
        
        try {
            File file = new File("messages.json");
            if (file.exists()) {
                JSONParser parser = new JSONParser();
                Object data = parser.parse(new FileReader(file));
                messageList = (JSONArray) data;
            }
            
        messageList.add(jsonMessage);
        
        FileWriter writer = new FileWriter("messages.json");
        writer.write(messageList.toJSONString());
        writer.flush();
        writer.close();
        
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
        }
    }
    
    public void printMessages(List<Message> messages){
        StringBuilder output = new StringBuilder("Messages Sent:\n\n");
        
        for (Message message : messages){
            output.append("Message ID: ").append(message.getMessageId()).append("\n")
                  .append("Message Hash: ").append(message.getMessageHash()).append("\n")
                  .append("Recipient: ").append(message.getRecipient()).append("\n")
                  .append("Message: ").append(message.getText()).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }
    
    public boolean validateRecipient(String recipient) {
        if (checkRecipientCell(recipient) != 1) {
            showMessage("Invalid number format.");
            return false;
        }
        return true;
    }

    public boolean validateText(String text) {
        if (text.length() > 250) {
            showMessage("Please enter a message of less than 250 characters.");
            return false;
        }
        return true;
    }
    
    public void handleSendMessage() {
        if (messages.size() >= maxMessages) {
            JOptionPane.showMessageDialog(null, "You have reached your message limit.");
            return;
        }

        String recipient = JOptionPane.showInputDialog("Enter recipient cell number (+ format, 11 digits max):");
        if (!validateRecipient(recipient)) return;

        String text = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (!validateText(text)) return;

        String id = generateMessageId();
        int msgNum = messages.size() + 1;
        Message tempMessage = new Message(id, msgNum, recipient, text, "", "");
        String hash = createMessageHash(tempMessage);
        String status = sendMessageOptions();

        Message finalMessage = new Message(id, msgNum, recipient, text, hash, status);

        if (status.equals("Sent")) {
            messages.add(finalMessage);
            totalMessagesSent++;
            JOptionPane.showMessageDialog(null,
                "Message sent\n\nID: " + id +
                "\nHash: " + hash +
                "\nRecipient: " + recipient +
                "\nText: " + text);
        } else if (status.equals("Stored")) {
            storeMessage(finalMessage);
            JOptionPane.showMessageDialog(null, "Message stored.");
        } else {
            JOptionPane.showMessageDialog(null, "Message disregarded.");
        }
    }
    
    public void handleShowMessages() {
        JOptionPane.showMessageDialog(null, "Coming Soon.");
    }
    /**
     * My program will only show you how many messages you have sent after you quit
     * because I didn't want a program that repeatedly tells you how many messages you have sent after each message.
     */
    public void handleQuit() {
        JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent);
        printMessages(messages);
    }
    
    public void setMaxMessages(int max) {
        this.maxMessages = max;
    }

    public int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    public List<Message> getMessages() {
        return messages;
    }
}