import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

// this is a superclass where customer and admin will extend to
public class User {

    int id;

    public User() {
        
    }

    //1. get id from user
    //2. run through the row in database
    //3. when match found, check password
    //4. if password match, print welcome
    //5. return id

    public void checkLogin (Connection connection, int id, String password) {

        String sql_query = String.format("SELECT login_pass FROM user WHERE login_id = %d", id);
      
            try {
                Statement statement = connection.createStatement();
                ResultSet data = statement.executeQuery(sql_query);
                

                while (data.next()) {
                    String foundPass = data.getString("login_pass");
                    if (Objects.equals(password, foundPass)) {
                        System.out.println("Welcome!");
                    }   
                    else {
                        System.out.println("wrong password");
                    }
                    }

                } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
