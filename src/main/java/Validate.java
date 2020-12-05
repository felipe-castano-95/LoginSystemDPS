import com.lambdaworks.crypto.SCryptUtil;
import java.sql.*;
import java.sql.DriverManager;


public class Validate {
    public static boolean checkUser(String email, char[] pass) 
    {
        boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","loginuser","loginpass");
            PreparedStatement ps = con.prepareStatement("select pass from register where email=?");
            ps.setString(1, email);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                st =  SCryptUtil.check(getString(pass), rs.getString(1));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   

    private static String getString(char[] pass) {
       String result = "";
        for (int i = 0; i < pass.length; i++) {
            result += pass[i];
        }
        return result;
    }
}