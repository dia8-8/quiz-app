package projet301;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
 DataBase DB=new DataBase();
 ResultSet resultat;
  
 
 
 public ObservableList<Exam> getExams(Student s) throws SQLException {
     Exam e=null;
      String query="Select mydata.exam.cid,mydata.exam.tid,name from mydata.class "
              + "join mydata.exam on (mydata.exam.cid=mydata.class.cid)"
              + " where sid="+s.getID()+" and done=0";
      resultat=DB.getData(query);
      ObservableList<Exam> list=FXCollections.observableArrayList();
     while(resultat.next())
      {
         e=new Exam(resultat.getInt("cid"),resultat.getInt("tid"),resultat.getString("name"));
         list.add(e);
      }
      return list;
 }
     public Student getStudent(String username) throws SQLException {
           Student s;
           String query="Select * from mydata.student where username='"+username+"'";
           resultat=DB.getData(query);
           if(resultat.next());
           s=new Student(resultat.getInt("sid"),resultat.getString("firstname"),resultat.getString("lastname"),resultat.getString("username"),resultat.getInt("tid"));
           return s;
    }
 public void submit(Student s,String grade,Exam e) throws SQLException
 {
     String query ="Update mydata.class set done=1,Grade="+grade+" where sid="+s.getID()+" and cid="+e.getId();
     DB.modifyData(query);
 }
    
}
