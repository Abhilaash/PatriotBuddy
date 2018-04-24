package cs321.patriotbuddy;

import java.io.Serializable;

/**
 * Created by mr.banskota on 3/28/18.
 */

public class Course implements Serializable {

    public String crn = "";
    public String courseCode = "";
    public String professor = "";

    public Course(String crn, String courseCode, String professor){
        this.crn = crn;
        this.courseCode = courseCode;
        this.professor = professor;
    }

    public boolean equals(Object o){
        Course c = (Course) o;
        return crn.equals(c.crn);
    }

    public String toString(){
        return courseCode + ", " + professor + ", CRN: " + crn;
    }
}
