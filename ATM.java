package test;

public class ATM {
	// min & max length required for bank account number
    public static final int LENGTH_ACCNUM = 12;
    
    // boolean function to check the bank account number length
    public static boolean checkLength (String ban){
        if (ban.length() == LENGTH_ACCNUM){
            return true;
        }
        else{
            return false;
        }
    }
    // boolean function to check if the bank account number is a reserved number
    public static boolean checkReserved (String ban){
        if (ban == "000000000000" || ban == "999999999999"){
            return true;
        }
        else{
            return false;
        }
    }
    // boolean function to check if the bank account number contains only numbers (numerical characters)
    public static boolean checkNumeric (String ban){
        if (ban.matches("[0-9]+")){
            return true;
        }
        else{
            return false;
        }
    }
    // boolean function to check if the transfer amount is positive 
    public static boolean checkPositiveAmount (String amount){
        if (Double.parseDouble(amount) > 0.0){
            return true;
        }
        else{
            return false;
        }
    }
    // boolean function to check if the transfer amount meets the minimum amount & the maximum limit
    public static boolean checkAmountLimit (String amount){
        if (Double.parseDouble(amount) >= 1.0 && Double.parseDouble(amount) <= 10000.0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static String send_money (String ban, String amount){
        if (checkLength(ban) == false){
            return "Your bank account number must be 12 digits.";
        }
        if (checkReserved(ban) == true){
            return "Your bank account number is reserved or restricted.";
        }
        if (checkNumeric(ban) == false){
            return "Your bank account number contains non-numeric characters.";
        }
        if (checkPositiveAmount(amount) == false){
            return "The transfer amount must not be negative or zero.";
        }
        if (checkAmountLimit(amount) == false){
            return "The minimum amount required is 1.00 and the maximum amount allowed is 10000.00";
        }
        System.out.println("The bank account you want to transfer money to is #" + ban);
        System.out.println("The amount you want to transfer is $" + amount);
        // if all criteria has been satisfied, the transaction is successful.
        return "Transaction successful!";
    }
}
