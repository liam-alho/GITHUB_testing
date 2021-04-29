import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Creating Database login info */
/** NEW CODE */

public class DatabaseHandler {
    private Connection con;
    private String DBURL = "jdbc:mysql://localhost:3308/statements?autoReconnect=true&useSSL=false";
    private String DBUser = "root";
    private String DBPassword = "root";
    
    public DatabaseHandler()
    {
        try { 
            connection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void connection() throws ClassNotFoundException,SQLException
    { 
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(DBURL, DBUser, DBPassword);
    } 

/** Sending login info to Database and checking if its true or false */
    
    public boolean login(String username,String password)
    {
        try{
            String query = "select * from login where username ='" + username + "'  and password='" + password + "'";
            Statement stmt = null;
            stmt = (Statement) con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if (rs.next())
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            System.err.println(e);
            return false;
        }
    }
}