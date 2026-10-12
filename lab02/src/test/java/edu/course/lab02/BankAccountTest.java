package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testSuccessfulWithdrawal() {
        BankAccount account = new BankAccount(1000);
        
        account.withdraw(400);
        
        assertEquals(600, account.getBalance());
    }

    @Test
    void testWithdrawExceedsBalanceThrowsException() {
        BankAccount account = new BankAccount(500);
        
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600);
        });
        
        assertEquals(500, account.getBalance());
    }

    @Test
    void testNegativeInitialBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-100);
        });
    }

    @Test
    void testDepositPositiveAmount() {
        BankAccount account = new BankAccount(200);
        account.deposit(300);
        assertEquals(500, account.getBalance());
    }
}