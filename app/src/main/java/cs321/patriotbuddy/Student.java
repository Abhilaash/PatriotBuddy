package cs321.patriotbuddy;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Messagable, Serializable{

    protected String name = "";

    public ArrayList<Student> friends;
    public ArrayList<Course> courses;

    public Student(String name) {
        this.name = name;
        friends = new ArrayList<Student>();
        courses = new ArrayList<Course>();
    }

    public void sendMessage(String message){

    }

    public void receiveMessage(String message) {

    }

    public String toString(){
        return name;
    }
}
