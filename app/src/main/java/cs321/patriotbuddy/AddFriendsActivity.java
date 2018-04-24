package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddFriendsActivity extends AppCompatActivity {

    private Profile profile;
    private ListView browseList;
    private TextButtonListAdapter<Course> coursesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        profile = (Profile)getIntent().getSerializableExtra("Profile");
        browseList = findViewById(R.id.browseList);

        coursesAdapter = new TextButtonListAdapter<Course>(this,
                profile.courses, "Select");

        class SelectCourseTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new SelectCourseTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                showPeopleInCourse(profile.courses.get(position));
            }
        }

        coursesAdapter.listener = new SelectCourseTextButtonListener();
        browseList.setAdapter(coursesAdapter);
    }

    private void showPeopleInCourse(Course course) {

        //TODO: database queries to get people in courses
        Course c1 = new Course("123", "CS 321", "Dr. S");
        Course c2 = new Course("678", "CS 465", "Dr. K");
        Course c3 = new Course("495", "CS 325", "Dr. G");
        Course c4 = new Course("657", "CS 101", "Dr. S");
        Course c5 = new Course("168", "CS 105", "Dr. K");
        Course c6 = new Course("6413", "CS 310", "Dr. C");
        Course c7 = new Course("12", "CS 262", "Dr. N");
        Course c8 = new Course("37896", "CS 367", "Dr. K");
        Course c9 = new Course("2318", "CS 475", "Dr. D");
        Course c10 = new Course("156", "CS 450", "Dr. L");
        Course c11 = new Course("761", "CS 480", "Dr. M");
        Course c12 = new Course("685", "CS 451", "Dr. P");
        Course c13 = new Course("438", "CS 471", "Dr. R");
        Course c14 = new Course("963", "CS 425", "Dr. T");

        Student trevor = new Student("Trevor");
        Student andy = new Student("Andy");
        Student steven = new Student("Steven");
        Student cooper = new Student("Cooper");
        Student cody = new Student("Cody");
        Student zoe = new Student("Zoe");
        Student ally = new Student("Ally");
        Student rafidh = new Student("Rafidh");
        Student eric = new Student("Eric");
        Student mauro = new Student("Mauro");

        trevor.courses.add(c1);
        trevor.courses.add(c2);
        trevor.courses.add(c6);

        andy.courses.add(c2);
        andy.courses.add(c3);
        andy.courses.add(c7);

        steven.courses.add(c3);
        steven.courses.add(c4);
        steven.courses.add(c8);

        cooper.courses.add(c4);
        cooper.courses.add(c5);
        cooper.courses.add(c9);

        cody.courses.add(c5);
        cody.courses.add(c6);
        cody.courses.add(c11);

        zoe.courses.add(c6);
        zoe.courses.add(c7);
        zoe.courses.add(c12);

        ally.courses.add(c7);
        ally.courses.add(c8);
        ally.courses.add(c13);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(trevor);
        students.add(andy);
        students.add(steven);
        students.add(cooper);
        students.add(cody);
        students.add(ally);
        students.add(zoe);
        students.add(eric);
        students.add(mauro);
        students.add(rafidh);

        final ArrayList<Student> studentsInCourse = new ArrayList<Student>();
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).courses.contains(course)){
                studentsInCourse.add(students.get(i));
            }
        }

        TextButtonListAdapter<Student> studentAdapter = new TextButtonListAdapter<Student>(this,
                studentsInCourse, "ADD");

        class AddFriendTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new AddFriendTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                profile.friends.add(studentsInCourse.get(position));
            //    showPeopleInCourse(profile.courses.get(position));
            }
        }

        studentAdapter.listener = new AddFriendTextButtonListener();
        browseList.setAdapter(studentAdapter);
    }

    public void back(View view){

        if(browseList.getAdapter() == coursesAdapter){
            Intent intent = new Intent();
            intent.putExtra("Profile", profile);
            setResult(RESULT_OK, intent);

            finish();

        }else{
            browseList.setAdapter(coursesAdapter);
        }
    }
}
