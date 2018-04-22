package cs321.patriotbuddy;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile extends Student implements Serializable {

    ArrayList<Course> myCourse = new ArrayList<Course>();
    String pname="";

    public Profile(String name){
        super(name);
        this.pname=name;

    }
    public void addCourse(Course course){
        for(Course x:myCourse) {
            if(!x.equals(course))
            {
                myCourse.add(course);
            }
            else
            {
                //Check to see if the print statement is supposed to be displayed like this
                System.out.println("The course already exists in the schedule");
            }
        }

    }
    public void removeCourse(Course course){
        for(Course x:myCourse)
        {
            if(x.equals(course))
            {
                myCourse.remove(x);

            }
            else
            {
                //check this to make sure how it needs to be displayed
                System.out.println("The course you are trying to remove does not exist");
            }

        }

    }
    public ArrayList<Course> getCourses(){
        return myCourse;
    }

    public String getPname() {
        return this.pname;
    }

    public void changeName(String newName){
        name = newName;

    }

    public void setPname(String temp)
    {
        this.pname=temp;
    }
}
