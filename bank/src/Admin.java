import java.sql.*;
import java.sql.Connection;
import java.util.Scanner; //import scanner class

public class Admin extends User {

    public Admin(Connection connection, int id) {

        this.id = id;
    }

    public void addAccount(Connection connection) {


        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter login id");
            int loginID = input.nextInt();
            input.nextLine();

            System.out.println("Enter login password");
            String loginPass = input.nextLine();

            System.out.println("Enter role");
            String role = input.nextLine();


            System.out.println("Enter accNum(type NULL if the role is admin)");
            int accNum = input.nextInt();
            input.nextLine();


            String sql_query = String.format("INSERT INTO user (login_pass,login_id, role, acc_num) VALUES (\"%s\",%d,\"%s\",%d", loginPass, loginID, role, accNum);
            try {
                Statement statement = connection.createStatement();
                statement.executeQuery(sql_query);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeAccount(Connection connection, int id) {

        String sql_query = String.format("DELETE FROM user WHERE login_id = %d", id);
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql_query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
