package cs321.patriotbuddy;

import java.io.Serializable;

public class Profile extends Student implements Serializable {

    public Profile(String name){
        super(name);
    }

    public void changeName(String newName){
        name = newName;
    }

    public Course[] getCourses(){
        Course[] c = new Course[courses.size()];
        return courses.toArray(c);
    }
}
