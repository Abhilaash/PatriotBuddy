package cs321.patriotbuddy;

/**
 * Created by mr.banskota on 3/28/18.
 */

public class Profile extends Student {

    public Profile(){
        super("");
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
}
