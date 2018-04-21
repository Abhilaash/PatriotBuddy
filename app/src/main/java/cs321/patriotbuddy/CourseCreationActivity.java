package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CourseCreationActivity extends AppCompatActivity {

    private Profile profile;
    private EditText crn;
    private EditText code;
    private EditText professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);

        profile = (Profile)getIntent().getSerializableExtra("Profile");

        crn = findViewById(R.id.createCourseCRN);
        code = findViewById(R.id.createCourseCode);
        professor = findViewById(R.id.createCourseProf);

        String crnString = (String)getIntent().getSerializableExtra("CRN");
        crn.setText(crnString);
    }

    protected void createCourse(View view){

        if(crn.getText().toString().isEmpty() || code.getText().toString().isEmpty() ||
                professor.getText().toString().isEmpty()){
            return;
        }

        Course c = new Course(crn.getText().toString(), code.getText().toString(),
                professor.getText().toString());
        profile.courses.add(c);

        Intent intent = new Intent();
        intent.putExtra("Profile", profile);
        setResult(RESULT_OK, intent);

        finish();
    }
}
