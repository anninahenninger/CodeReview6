public class Teacher {
    private int teacherID;
    private String teacherName;
    private String teacherSurname;
    private String teacherEmail;

    public Teacher(int teacherID, String teacherName, String teacherSurname, String teacherEmail) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherEmail = teacherEmail;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
    public String toString(){
        return this.teacherName + ", " + this.teacherSurname;
    }
}
