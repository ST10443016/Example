package com.mycompany;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cristiano Tavares ST10443016
 */
public class LoginManagerTest {
    
    /**
     * Array is currently empty, but i added "dummy" values
     */
    
    @org.junit.jupiter.api.Test
    public void testLoginFail() {
        LoginManager instance = new LoginManager();
        instance.Register("Cristiano", "Tavares", "Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = instance.Login("Kyle!!!!!!!", "password");
        assertFalse(result);
        System.out.println(result);
    }
    
    @Test
    public void testLoginPass() {
        LoginManager instance = new LoginManager();
        instance.Register("Cristiano", "Tavares", "Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = instance.Login("Kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
        System.out.println(result);
    }

    @Test
    public void checkUserNameTestFail(){
        LoginManager account = new LoginManager();
        boolean result = account.checkUserNameTest("Kyle!!!!!!!");
        assertFalse(result);
        System.out.println(result);
    }
    
    @Test
    public void checkUserNameTestPass(){
        LoginManager account = new LoginManager();
        boolean result = account.checkUserNameTest("Kyl_1");
        assertTrue(result);
        System.out.println(result);
    }
    
    @Test
    public void checkPasswordComplexityTestFail(){
        LoginManager account = new LoginManager();
        boolean result = account.checkPasswordComplexityTest("password");
        assertFalse(result);
        System.out.println(result);
    }
    
    @Test
    public void checkPasswordComplexityTestPass(){
        LoginManager account = new LoginManager();
        boolean result = account.checkPasswordComplexityTest("Ch&&sec@ke99!");
        assertTrue(result);
        System.out.println(result);
    }
    
    @Test
    public void checkCellPhoneNumberTestFail(){
        LoginManager account = new LoginManager();
        boolean result = account.checkCellPhoneNumberTest("08966553");
        assertFalse(result);
        System.out.println(result);
    }
    
    @Test
    public void checkCellPhoneNumberTestPass(){
        LoginManager account = new LoginManager();
        boolean result = account.checkCellPhoneNumberTest("+27838968976");
        assertTrue(result);
        System.out.println(result);
    }
}