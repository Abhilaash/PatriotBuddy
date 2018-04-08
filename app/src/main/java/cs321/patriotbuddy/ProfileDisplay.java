package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

public class ProfileDisplay extends AppCompatActivity {

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);

        if(profile == null) {
            profile = new Profile("Zac");
            Course c1 = new Course("123", "CS 321", "Dr. S");
            Course c2 = new Course("678", "CS 456", "Dr. K");
            Course c3 = new Course("495", "CS 325", "Dr. G");
            Course c4 = new Course("495", "CS 325", "Dr. G");
            Course c5 = new Course("495", "CS 325", "Dr. G");
            Course c6 = new Course("495", "CS 325", "Dr. G");
            Course c7 = new Course("495", "CS 325", "Dr. G");
            Course c8 = new Course("495", "CS 325", "Dr. G");
            profile.addCourse(c1);
            profile.addCourse(c2);
            profile.addCourse(c3);
            profile.addCourse(c4);
            profile.addCourse(c5);
            profile.addCourse(c6);
            profile.addCourse(c7);
            profile.addCourse(c8);
        }

        setUpDisplay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                profile = (Profile)intent.getSerializableExtra("Profile");
                setUpDisplay();
            }
        }
    }

    private void setUpDisplay() {

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(profile.name);

        Course[] c = profile.getCourses();
        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this,
                R.layout.listview, c);

        ListView classList = findViewById(R.id.classList);
        classList.setAdapter(adapter);
    }

    protected void edit(View view){
        Intent intent = new Intent(this, ProfileEdit.class);
        intent.putExtra("Profile", profile);
        startActivityForResult(intent, 1);
    }
}
