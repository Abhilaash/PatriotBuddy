package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseCreationActivity extends AppCompatActivity {

    private Profile profile;
    private EditText crn;
    private EditText code;
    private EditText professor;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;


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
        profile.addCourse(c);
    //    mDatabase= FirebaseDatabase.getInstance().getReference().child("users").child(profile.name);
    //    mDatabase.setValue(profile.myCourse);
        Intent intent = new Intent();
        intent.putExtra("Profile", profile);
        setResult(RESULT_OK, intent);

        finish();
    }
}
