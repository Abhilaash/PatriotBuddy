package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private Profile profile;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getInstance().getCurrentUser();


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
            profile.courses.add(c1);
            profile.courses.add(c2);
            profile.courses.add(c3);
            profile.courses.add(c4);
            profile.courses.add(c5);
            profile.courses.add(c6);
            profile.courses.add(c7);
            profile.courses.add(c8);
            profile.friends.add(new Student("Jack"));
            profile.friends.add(new Student("John"));
            profile.friends.add(new Student("Jill"));
            profile.friends.add(new Student("Joe"));
            profile.friends.add(new Student("Jeff"));
            profile.friends.add(new Student("Jace"));
            profile.friends.add(new Student("Jen"));
            profile.friends.add(new Student("Jenson"));
            profile.friends.add(new Student("Jeremiah"));
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
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("Profile", profile);
        startActivityForResult(intent, 1);
    }

    protected void showFriends(View view){
        Intent intent = new Intent(this, FriendsDisplayActivity.class);
        intent.putExtra("Profile", profile);
        startActivityForResult(intent, 1);
    }

    protected void signOut(View view){

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
