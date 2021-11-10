
package projet301;

public class ClassRoom {
    private int id,studentid,examid;
    public ClassRoom(int id,int studentid,int examid,int teacherid)
    {
        this.id=id;
        this.studentid=studentid;
        this.examid=examid;
    }

    public int getId() {
        return id;
    }

    public int getStudentid() {
        return studentid;
    }

    public int getExamid() {
        return examid;
    }
    
}
