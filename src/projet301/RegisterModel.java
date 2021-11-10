
package projet301;


import java.sql.*;
import javax.sql.*;

public class RegisterModel {
    
    DataBase DB=new DataBase();
    ResultSet resultat;
    String Query="select * from mydata.users";
    String username,password;
    public RegisterModel() throws SQLException
    {
        resultat =DB.getData(Query);
    }
   
    
    
    public String CheckCredentials(String user,String pass) throws SQLException
    {
        String s="",query="";
        while(resultat.next())
    {  
        username=resultat.getString("username");
        password=resultat.getString("password");
        if(user.equals(username)&& pass.equals(password))
        {
            
            s=resultat.getString("role");
            break;
        }
    }
            return s;
         
    }
    
    
    
    public boolean AddAccount (String firstname,String lastname,String user,String pass,String role) throws SQLException
    {
        
        boolean b=true;
        while(resultat.next())
        {
            username=resultat.getString("username");
            if(user.equals(username))
            {
                b=false;
                break;
            }
            
            
        }
        if(b==true)
        {
            String query="insert into mydata.users value (\""+user+"\",\""+pass+"\",'"+role+"')";
            DB.modifyData(query);
       
        if(role.equals("Student")) 
        {
             String query1="insert into mydata.student (firstname,lastname,username) value (\""+firstname+"\",\""+lastname+"\",'"+user+"')";
             DB.modifyData(query1);
            
        }
        else
        {
             String query1="insert into mydata.teacher (firstname,lastname,username) value (\""+firstname+"\",\""+lastname+"\",'"+user+"')";
             DB.modifyData(query1);
        }
        
        }
        DB.resultat.close();
        DB.instruction.close();
        DB.mycnx.close();
            return b;
        
    }
    
}
