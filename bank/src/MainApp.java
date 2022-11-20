import java.util.Scanner; //import scanner class
import java.sql.*;
import java.sql.Connection;


public class MainApp {
    public static void main (String[] args) throws Exception {
        Connection connection = DBConnect();

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter: \n1= Customer | 2=Admin");
            int userChoice = input.nextInt();
            input.nextLine();
    
            if (userChoice == 1) { //if customer

                System.out.println("Enter Customer ID:\n");
                int id = input.nextInt();
                input.nextLine();
                System.out.println("Enter password:\n");
                String password = input.nextLine();
                input.nextLine();


                Customer customer_user = new Customer(connection, id);
                customer_user.checkLogin(connection,id, password);

                System.out.println("Enter: \n1= withdraw money | 2= transfer money");
                int numChoice = input.nextInt();
                input.nextLine();

                if (numChoice == 1) { //customer withdraw money
                    customer_user.withdrawMoney(connection,id);
                }
                if (numChoice == 2){ //customer transfer money
                    
                    customer_user.transferMoney(connection, id);
                }

            }
                

            if (userChoice == 2) { //if admin

                System.out.println("Enter Admin ID:\n");
                int id = input.nextInt();
                System.out.println("Enter password:\n");
                String password = input.nextLine();

                Admin admin_user = new Admin(connection, id);
                admin_user.checkLogin(connection, id, password);

                System.out.println("Enter: \n1= add account | 2= remove account");
                int numChoice = input.nextInt();
                input.nextLine();

                if (numChoice == 1) { //admin add account
                    admin_user.addAccount(connection);
                }
                if (numChoice == 2){ // admin remove account
                    admin_user.removeAccount(connection,id);
                }
            }
            }

        
    }

    public static Connection DBConnect() {

        Connection connection;
        try {
            String url = "jdbc:sqlite:./bank.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}


