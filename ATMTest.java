package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ATMTest {

	@Test // check if the length of the bank account number meets the criteria
	public void testCheckLength() {
		assertTrue(ATM.checkLength("123456789012")); // length must only be 12 characters
		assertFalse(ATM.checkLength("12345"));
		assertFalse(ATM.checkLength("12345678901234"));
	}
	@Test
	public void testCheckReserved() {
		assertTrue(ATM.checkReserved("000000000000"));
		assertTrue(ATM.checkReserved("999999999999"));
		assertFalse(ATM.checkReserved("123456789012"));
	}
	@Test
	public void testCheckNumeric() {
		assertTrue(ATM.checkNumeric("123456789012"));
		assertFalse(ATM.checkNumeric("abcd12345678"));
		assertFalse(ATM.checkNumeric("1234-5678-9012"));
	}
	@Test // check if the amount to be transferred meets the criteria
	public void testCheckPositive() {
		assertTrue(ATM.checkPositiveAmount("100.00"));
		assertFalse(ATM.checkPositiveAmount("0.00"));
		assertFalse(ATM.checkPositiveAmount("-100.00"));
	}
	@Test
	public void testCheckLimit() {
		assertTrue(ATM.checkAmountLimit("1.00"));  // minimum valid amount
        assertTrue(ATM.checkAmountLimit("10000.00"));  // maximum valid amount
        assertTrue(ATM.checkAmountLimit("500.00"));  // within valid range
        assertFalse(ATM.checkAmountLimit("0.99"));  // below minimum limit
        assertFalse(ATM.checkAmountLimit("10000.01"));  // above maximum limit
	}
	
	@Test // test cases for send_money() method
	public void testSendMoney() {
		// test case 2: account number not entered or invalid account number length
        assertEquals("Your bank account number must be 12 digits.",
                ATM.send_money("12345", "100.00"));
        assertEquals("Your bank account number must be 12 digits.",
                ATM.send_money("12345678901234", "100.00"));
        // test case 7: reversed/restricted bank account number
        assertEquals("Your bank account number is reserved or restricted.",
                ATM.send_money("000000000000", "100.00"));
        assertEquals("Your bank account number is reserved or restricted.",
                ATM.send_money("999999999999", "100.00"));
        // test case 3: non-numeric account number
        assertEquals("Your bank account number contains non-numeric characters.",
                ATM.send_money("1234abcd5678", "100.00"));
        // test case 4: negative amount
        assertEquals("The transfer amount must not be negative or zero.",
                ATM.send_money("123456789012", "-100.00"));
        assertEquals("The transfer amount must not be negative or zero.",
                ATM.send_money("123456789012", "0.00"));
        // test case 6: amount below minimum limit
        assertEquals("The minimum amount required is 1.00 and the maximum amount allowed is 10000.00",
                ATM.send_money("123456789012", "0.99"));
        // test case 5: amount above maximum limit
        assertEquals("The minimum amount required is 1.00 and the maximum amount allowed is 10000.00",
                ATM.send_money("123456789012", "10000.01"));
        // test case 1: successful case
        assertEquals("Transaction successful!", ATM.send_money("432187652109", "150.00"));
        // transaction will print success messages since there are no further checks after this.
	}
}
