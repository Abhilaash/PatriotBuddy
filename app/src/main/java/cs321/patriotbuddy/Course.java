package cs321.patriotbuddy;

/**
 * Created by mr.banskota on 3/28/18.
 */

public class Course {


    String crn;
    String courseCode;
    String professor;



    public String getCrn()
    {
        return this.crn;
    }

    public void setCrn(String crs)
    {
        this.crn=crs;

    }

    public String getCourseCode()
    {

        return this.courseCode;

    }

    public void setCourseCode(String crsCode)
    {

        this.courseCode=crsCode;
    }

    public String getProfessor()
    {

        return this.professor;

    }
    public String setProfessor(String prf)
    {
        this.professor=prf;
    }

}
