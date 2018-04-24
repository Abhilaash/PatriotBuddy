package cs321.patriotbuddy;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile extends Student implements Serializable {

    ArrayList<Course> myCourse = new ArrayList<Course>();
    ArrayList<Course> courses = myCourse;

    String pname="";

    public Profile(String name){
        super(name);
        this.pname=name;

    }
    public void addCourse(Course course){

        myCourse.add(course);

    }

    public void add(Course course) {
        myCourse.add((course));
    }
    public void remove(int index){

//        for(Course x : myCourse) {
//            if (x.equals(course)) {
//                myCourse.remove(course);
//            }
            myCourse.remove(index);
        }
    public void remove(Course course) {

        for (Course x : myCourse) {
            if (x.equals(course)) {
                myCourse.remove(course);
            }

        }
    }


    public ArrayList<Course> getCourses(){
        return myCourse;
    }

    public String getPname() {
        return this.name;
    }

    public void changeName(String newName){
        name = newName;

    }

    public void setPname(String temp)
    {
        this.pname=temp;
    }
}
