package cs321.patriotbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CourseCreationActivity extends AppCompatActivity {

    private EditText crn;
    private EditText code;
    private EditText professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);

        crn = findViewById(R.id.createCourseCRN);
        code = findViewById(R.id.createCourseCode);
        professor = findViewById(R.id.createCourseProf);

        String crnString = (String)getIntent().getSerializableExtra("CRN");
        crn.setText(crnString);
    }

    private void createCourse(View view){

        Course c = new Course(crn.getText().toString(), code.getText().toString(),
                professor.getText().toString());
    }
}
