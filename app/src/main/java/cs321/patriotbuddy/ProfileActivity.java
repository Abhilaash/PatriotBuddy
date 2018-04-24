package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private Profile profile;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(profile == null) {
            assert user != null;
            Log.e("HELLO", user.getDisplayName());
            profile = new Profile(user.getDisplayName());
            Course c1 = new Course("123", "CS 321", "Dr. S");
            Course c2 = new Course("678", "CS 456", "Dr. K");
            Course c3 = new Course("495", "CS 325", "Dr. G");
            Course c4 = new Course("495", "CS 325", "Dr. G");
            Course c5 = new Course("495", "CS 325", "Dr. G");
            Course c6 = new Course("495", "CS 325", "Dr. G");
            Course c7 = new Course("495", "CS 325", "Dr. G");
            Course c8 = new Course("495", "CS 325", "Dr. G");
   /*         profile.addCourse(c1);
            profile.add(c2);
            profile.add(c3);
            profile.add(c4);
            profile.add(c5);
            profile.add(c6);
            profile.add(c7);
            profile.add(c8);
    */        profile.friends.add(new Student("Jack"));
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

               // profile = (Profile)intent.getSerializableExtra("Profile");
                //TextView editTextName = findViewById(R.id.nameText);
                //editTextName.setText(profile.name);
                //final String name = editTextName.getText().toString().trim();
                //profile= new Profile(name);
                setUpDisplay();
            }
        }
    }

    private void setUpDisplay() {

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(profile.name);

        final ListView classList = findViewById(R.id.classList);
        final ProfileActivity ref = this;

        DatabaseReference mDB = FirebaseDatabase.getInstance().getReference().child("users").child(profile.name);
        mDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Course> courses = new ArrayList<Course>();
                for(DataSnapshot x: dataSnapshot.getChildren()){
                    Course c = x.getValue(Course.class);
                    //profile.myCourse.add(c);
                    courses.add(c);
                }

                profile.myCourse = courses;
                ArrayList<Course> c = profile.myCourse;
                ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(ref, R.layout.listview, c);

                classList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayList<Course> c = profile.myCourse;
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, R.layout.listview, c);

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
        mAuth.signOut();
        finish();
    }
}
