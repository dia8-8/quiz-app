
package projet301;

import java.util.ArrayList;

public class Teacher {
   private String firstname,lastname,username;
   private int id;
   private ArrayList<Student> Students=new ArrayList<>();
    public Teacher(int id,String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.id=id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }
    
    public int getID()
    {
        return id;
    }
     public void AddStudent(Student s)
     {
         Students.add(s);
         
     }
    
    
}
