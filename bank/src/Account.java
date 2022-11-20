import java.sql.*;
import java.sql.Connection;

public class Account {
    int accountNumber;
    double balance;
    
    public Account(int accountNumber, double _balance) {
        this.accountNumber = accountNumber;
        this.balance = _balance;
    }

    // class to get account number and return it 
    public int getAccNumber (Connection connection, int id) {

        String sql_query = String.format ("SELECT acc_num FROM user WHERE login_id = %d", id) ;
        int returnAccNum = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(sql_query);
            while (data.next()) {
                int accNum = data.getInt("acc_num");
                System.out.println("Account number: " + accNum);
                returnAccNum = returnAccNum + accNum;
            }
    }    catch (Exception e) {
         throw new RuntimeException(e);
    }
        return returnAccNum;
       
    }


    //class to get balance and return it
    public double getBalance (Connection connection, int accountNumber) {

        String sql_query = String.format ("SELECT balance FROM account WHERE acc_num = %d", accountNumber) ;
        double returnBal = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(sql_query);
            while (data.next()) {
                double accBal = data.getDouble("balance");
                System.out.println("Your account balance is: "+ accBal);
                returnBal = returnBal + accBal;
            }

    }   catch (Exception e) {
        throw new RuntimeException(e);
    }
        return returnBal;
        
    }

    // class to update balance inside database
    public void updateBalance(Connection connection, double balanceUpdate, int accountNumber) {

        String sql_query = String.format ("UPDATE account SET balance = %f WHERE acc_num = %d", balanceUpdate, accountNumber);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_query);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

}
}


