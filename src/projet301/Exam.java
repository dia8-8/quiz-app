package projet301;


class Exam {
private int id,teacherId;
private String name;

    public Exam(int id, int teacherId, String name) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name ;
    }
    

}
