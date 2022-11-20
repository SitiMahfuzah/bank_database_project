import java.util.Scanner; //import scanner class
import java.sql.Connection;


public class Customer extends User {

    Account account;

    public Customer(Connection connection, int _id) {
        this.id = _id;
    }

    // 1) get accNum from Account using getAccNumber by id
    // 2) get balance from Account using getBalance by accNumber
    // 3) ask withdraw amount by withdrawAmount
    // 4) update balance using updateBalance from Account

    public void withdrawMoney(Connection connection, int _id) {

        int accNum = account.getAccNumber(connection,_id);
        double balance = account.getBalance(connection, accNum);
        try (Scanner input = new Scanner (System.in)) {
            System.out.println("Enter withdraw amount: ");
            double withdrawAmount = input.nextDouble();
            input.nextLine();

            while (withdrawAmount > balance) {
                String toPrint = String.format("Not enough balance in the account. Balance = RM %f", balance);
                System.out.println(toPrint);
                System.out.println("Please enter new withdraw amount:");
                withdrawAmount = input.nextDouble();
                input.nextLine();
            } 
            double newBalance = balance - withdrawAmount;
            account.updateBalance(connection, newBalance, accNum);
        } 
        }
    
    
    // 1) get accNum from Account using getAccNumber by id
    // 2) get balance from Account using getBalance by accNumber
    // 3) ask transfer account number
    // 4) ask transfer amount
    // 5) check if the transfer amount is within balance amount or not
    // 6) update balance for both account using updateBalance from Account

    public void transferMoney(Connection connection, int id) {

        int accNum = account.getAccNumber(connection,id);
        double myBalance = account.getBalance(connection, accNum);
        

        try (Scanner input = new Scanner (System.in)) {

            System.out.println("Enter transfer account number: ");
            int transferAcc = input.nextInt();
            input.nextLine();

            System.out.println("Enter transfer amount: ");
            double transferAmount = input.nextDouble();
            input.nextLine();

            while (transferAmount > myBalance) {
                String toPrint = String.format("Not enough balance in the account. Balance = RM %f", myBalance);
                System.out.println(toPrint);
                System.out.println("Please enter new withdraw amount:");
                transferAmount = input.nextDouble();
                input.nextLine();
            }

            double otherAccBalance = account.getBalance(connection,transferAcc);
            myBalance = myBalance - transferAmount;
            double theirBalance = otherAccBalance + transferAmount;

            account.updateBalance(connection, myBalance, accNum);
            account.updateBalance(connection, theirBalance, transferAcc);

    }
}
}


