package tests;
import projectPack.Account;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAccount {
    Account acc = new Account(0, "0000", "Tester", 0.0);

    @Test
    void testType(){
        var accountGetNameType = acc.getName().getClass().getSimpleName();
        assertEquals("String", accountGetNameType);
    }

    @Test 
    void getNameTest(){
        var accountName = acc.getName();
        assertEquals("Tester", accountName);
    }

    @Test 
    void passwordType(){
        var password = acc.getPassword().getClass().getSimpleName();
        assertEquals("String", password);
    }

    @Test
    void getPasswordTest(){
        var password = acc.getPassword();
        assertEquals("0000", password);
    }
}