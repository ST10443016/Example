package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cristiano Tavares ST10443016
 */
public class MessageManagerTest {
    
    class TestableMessageManager extends MessageManager {
        public List<String> messagesShown = new ArrayList<>();

        @Override
        protected void showMessage(String message) {
            messagesShown.add(message);
        }
    }
    
    @Test
    public void testValidateTextTrue() {
        TestableMessageManager instance = new TestableMessageManager();
        String validText = "Message reaady to send.";

        assertTrue(instance.validateText(validText));                  
        assertTrue(instance.messagesShown.isEmpty());                 
    }
    
    @Test
    public void testValidateTextFalse() {
        TestableMessageManager instance = new TestableMessageManager();
        String longText = "x".repeat(251);
        assertFalse(instance.validateText(longText));
        assertEquals("Please enter a message of less than 250 characters.", instance.messagesShown.get(0));
    }
    
    @Test
    public void testValidateRecipientTrue() {
        TestableMessageManager instance = new TestableMessageManager();
        String validNumber = "+27831234567";

        assertTrue(instance.validateRecipient(validNumber));         
        assertTrue(instance.messagesShown.isEmpty());                 
    }
    
    @Test
    public void testValidateRecipientFalse() {
        TestableMessageManager instance = new TestableMessageManager();
        assertFalse(instance.validateRecipient("08575975889"));
        assertEquals("Invalid number format.", instance.messagesShown.get(0));
    }
    
    @Test
    public void testGenerateMessageId() {
        MessageManager instance = new MessageManager();
        String id = instance.generateMessageId();
        assertTrue(id.matches("\\d{10}"), "Message ID should contain only digits");
        System.out.println("Message ID generated: " + id);
    }
    
    @Test
    void testCreateMessageHash() {
        MessageManager instance = new MessageManager();

        String id = "0012345678";
        int msgNum = 0;
        String recipient = "+27718693002";
        String text = "Hi Mike, can you join us for dinner tonight";

        Message message = new Message(id, msgNum, recipient, text, "", "");

        String expectedHash = "00:0:HITONIGHT";
        String actualHash = instance.createMessageHash(message);

        System.out.println("Generated Hash: " + actualHash);
        assertEquals(expectedHash, actualHash, "Message hash should match expected value for Test Case 1");
    }

    @Test
    void testSendMessage() {
        MessageManager instance = new MessageManager();
        assertEquals("Sent", instance.getStatusFromChoice(0));
        assertEquals("Stored", instance.getStatusFromChoice(1));
        assertEquals("Disregarded", instance.getStatusFromChoice(2));
    }   
}
