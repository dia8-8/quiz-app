
package projet301;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;

public class DataBase {
    ResultSet resultat =null;
    Statement instruction;
    Connection mycnx;
    
    public DataBase()
    {
       String url="jdbc:mysql://localhost:3306/mydata";
       String user="root";
       String pass="Abed.yehya123";
        try {
             mycnx= DriverManager.getConnection(url,user,pass);
             instruction=mycnx.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error!");
        }     
    }
     public ResultSet getData(String Query) throws SQLException
        {
            resultat=instruction.executeQuery(Query); 
            return resultat;
        }
     public void modifyData(String Query) throws SQLException{
         instruction.executeUpdate(Query);
     }
     
    
    
}
