
package projet301;

public class Student {
    private String firstname,lastname,username,quiz,grade;
    private int id, teacherID,cid;

    public Student(int id,String firstname, String lastname, String username,int TeacherID) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.teacherID=TeacherID;
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
    public int getTeacherID()
    {
        return teacherID;
    }
    public int getCid()
    {
        return this.cid;
    }
    public void setCid(int cid)
    {
        this.cid=cid;
    }
    public String getQuiz()
    {
        return this.quiz;
    }
    public void setQuiz(String Quiz)
    {
       this.quiz=Quiz;
    }
    public String getGrades()
    {
        return this.grade;
    }
    public void setGrade(String grade)
    {
           this.grade=grade;
    }
    
}
