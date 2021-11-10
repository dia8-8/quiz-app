
package projet301;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherModel {
      DataBase DB=new DataBase();
      ResultSet resultat;
      public Teacher getTeacher(String username) throws SQLException
      {
          Teacher t=null;
           String query="Select * from mydata.teacher where username='"+username+"'";
           resultat=DB.getData(query);
           if(resultat.next()){
           t=new Teacher(resultat.getInt("tid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"));
           }           
           return t;
      }
      
      public Student AddStudent(String name,Teacher t) throws SQLException
      {
          Student s=null;
       String query="Select * from mydata.student where username='"+name+"'";
       resultat=DB.getData(query);
       if(resultat.next())
       {
        s=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
       }
       query="Update mydata.student\n" +
            "Set \n" +
            "tid ="+t.getID()+"\n" +
             "where \n" +
            "mydata.student.username='"+name+"'\n";
       DB.modifyData(query);
       query="Select sid,cid from mydata.exam join mydata.student on (mydata.student.tid=mydata.exam.tid) where cid=(Select max(cid) from mydata.exam where tid="+t.getID()+")";
        resultat=DB.getData(query);
        DataBase db=new DataBase();
        while(resultat.next())
        {
            String q="Insert into mydata.class (sid,cid) values("+resultat.getInt("sid")+","+resultat.getInt("cid")+")";
            db.modifyData(q);
            
        }
        query="DELETE S1 FROM mydata.class AS S1  \n" +
              "INNER JOIN mydata.class AS S2   \n" +
              "WHERE S1.idclass > S2.idclass AND S1.sid = S2.sid AND S1.cid=S2.cid;  ";
        DB.modifyData(query);
       return s;
      }
      
      
     public  ObservableList<String> Display() throws SQLException
     {
         String Query="SELECT firstname,lastname,mydata.student.username\n" +
                      "FROM mydata.student\n" 
                 + " where mydata.student.tid is null";
         resultat=DB.getData(Query);
         ObservableList<String> list=FXCollections.observableArrayList();
         while(resultat.next())
         {
             list.add(resultat.getString("username")+": "+resultat.getString("firstname")+" "+resultat.getString("lastname"));
         }
         return list;
     }
     
     
        public  ObservableList<Student> DisplayMyStudents(Teacher t) throws SQLException
     {
         String Query="SELECT *" +
                      "FROM mydata.student\n" +
                      "where tid= "+t.getID();
         resultat=DB.getData(Query);
         ObservableList<Student> list=FXCollections.observableArrayList();
         while(resultat.next())
         {
            Student s=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
             list.add(s);
         }
         return list;
     }
     
     
      public  ObservableList<Student> ViewResults(Teacher t) throws SQLException
     {
         String Query="SELECT *" +
                      "FROM mydata.class\n inner join mydata.student on (mydata.student.sid=mydata.class.sid) " +
                      "where mydata.student.tid= "+t.getID()+" and done=1";
         resultat=DB.getData(Query);
         ObservableList<Student> list=FXCollections.observableArrayList();
         while(resultat.next())
         {
            Student s=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
            s.setGrade(resultat.getString("grade"));
            s.setQuiz(resultat.getString("quiz"));
            list.add(s);
         }
         return list;
     }
       public List SearchMyStudent(String s,Teacher t) throws SQLException
    {
        String Query="SELECT tid,sid,firstname,lastname,mydata.student.username\n" +
                     "FROM mydata.student\n" +
                     "Where username Like '%"+s+"%' AND mydata.student.tid="+t.getID();
         resultat=DB.getData(Query);
         ObservableList<Student> list=FXCollections.observableArrayList();
         while(resultat.next())
         {
            Student S=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
             list.add(S);
         }
         if(list.isEmpty())
         {
             DisplayMyStudents(t);
         }
        
        DB.resultat.close();
        DB.instruction.close();
        DB.mycnx.close();
        return list;
         
            
    }
   
    public List SearchStudent(String s,Teacher t) throws SQLException
    {
        String Query="SELECT firstname,lastname,mydata.student.username\n" +
                     "FROM mydata.student\n" +
                     "INNER JOIN mydata.users ON users.username=student.username\n" +
                     "Where mydata.users.username Like '%"+s+"%' AND mydata.student.tid is NULL";
        resultat =DB.getData(Query);
        List list=new ArrayList();
        while(resultat.next())
        {
      list.add(resultat.getString("username")+": "+resultat.getString("firstname")+" "+resultat.getString("lastname"));        
        }
        if(list.isEmpty())
        {
            Display();
        }
        DB.resultat.close();
        DB.instruction.close();
        DB.mycnx.close();
        return list;
         
            
    }
     public ObservableList<Student> SearchResults(String s,Teacher t) throws SQLException
    {
        String Query="SELECT *" +
                      "FROM mydata.class\n inner join mydata.student on (mydata.student.sid=mydata.class.sid) " +
                      "where mydata.student.tid= "+t.getID()+" and done=1 and mydata.student.username LIKE '%"+s+"%'";
        resultat =DB.getData(Query);
        ObservableList<Student> List=FXCollections.observableArrayList();
        while(resultat.next())
        {
            Student S=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
            S.setGrade(resultat.getString("grade"));
            S.setQuiz(resultat.getString("quiz"));
            List.add(S);
        }
        if(List.isEmpty())
        {
          ViewResults(t);
        }
        DB.resultat.close();
        DB.instruction.close();
        DB.mycnx.close();
        return List;
         
            
    }

   public  void RemoveStudent(Student s) throws SQLException {
        String query="Update mydata.student\n" +
            "Set \n" +
            "tid =NULL\n" +
             "where \n" +
            "username='"+s.getUsername()+"'\n";
       DB.modifyData(query);
        
    }
   


    
    public void createExam(Teacher t,String name) throws SQLException
    {   DataBase db=new DataBase();
        String query="Insert into mydata.exam (name,tid) values ('"+name+"',"+t.getID()+")";
        DB.modifyData(query);
        query="Select sid,cid from mydata.exam join mydata.student on (mydata.student.tid=mydata.exam.tid) where cid=(Select max(cid) from mydata.exam where tid="+t.getID()+")";
        resultat=DB.getData(query);
        while(resultat.next())
        {
            String q="Insert into mydata.class (sid,cid,quiz) values("+resultat.getInt("sid")+","+resultat.getInt("cid")+",'"+name+"')";
            db.modifyData(q);
            
        }
        DB.resultat.close();
        DB.instruction.close();
        DB.mycnx.close();
    } 
    
    
}
