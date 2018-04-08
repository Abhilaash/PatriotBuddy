package cs321.patriotbuddy;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mr.banskota on 3/28/18.
 */

public class Student implements Messagable, Serializable{

    protected String name = "";

    protected ArrayList<Student> friends;
    protected ArrayList<Course> courses;

    public Student(String name) {
        this.name = name;
        friends = new ArrayList<Student>();
        courses = new ArrayList<Course>();
    }

    public void sendMessage(String message){

    }

    public void receiveMessage(String message) {

    }
}
