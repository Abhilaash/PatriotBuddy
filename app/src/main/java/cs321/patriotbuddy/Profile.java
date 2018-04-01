package cs321.patriotbuddy;

import java.io.Serializable;

/**
 * Created by mr.banskota on 3/28/18.
 */

public class Profile extends Student implements Serializable {

    public Profile(String name){
        super(name);
    }

    public void addCourse(Course course){
        courses.add(course);



    }

    public void removeCourse(Course course){
        courses.remove(course);
    }

    public void changeName(String newName){
        name = newName;
    }

    public Course[] getCourses(){
        Course[] c = new Course[courses.size()];
        return courses.toArray(c);
    }
}
