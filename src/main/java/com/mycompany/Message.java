package com.mycompany;

/**
 *
 * @author Cristiano Tavares ST10443016
 */

public class Message {
    private String messageId;
    private int messageNum;
    private String recipient;
    private String text;
    private String messageHash;
    private String status; // Sent, Stored, Disregarded
    
    public Message (
        String messageId,
        int messageNum,
        String recipient, 
        String text,
        String messageHash,
        String status
    ){
        this.messageId = messageId;
        this.messageNum = messageNum;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = messageHash;
        this.status = status;
    }
    
    public String getMessageId(){
        return messageId;
    }
    
    public void setMessageId(String messageId){
        this.messageId = messageId;
    }
    
    public int getMessageNum(){
        return messageNum;
    }
    
    public void setMessageNum(int messageNum){
        this.messageNum = messageNum;
    }
    
    public String getRecipient(){
        return recipient;
    }
    
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getMessageHash(){
        return messageHash;
    }
    
    public void setMessageHash(String messageHash){
        this.messageHash = messageHash;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
}
