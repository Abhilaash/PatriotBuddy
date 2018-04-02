package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ProfileEdit extends AppCompatActivity {

    private Profile profile;
    private EditText nameText;
    private DeleteItemListAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        profile = (Profile)getIntent().getSerializableExtra("Profile");

        nameText = findViewById(R.id.editText);
        nameText.setText(profile.name);

        Course[] c = profile.getCourses();
        adapter = new DeleteItemListAdapter<Course>(this, c);

        ListView classList = findViewById(R.id.classList);
        classList.setAdapter(adapter);
    }

    protected void save(View view){

        profile.name = nameText.getText().toString();

        Course[] c = profile.getCourses();
        for(int i = 0; i < c.length; i++){
            if(!adapter.contains(c[i])){
                profile.removeCourse(c[i]);
            }
        }

        Intent intent = new Intent();
        intent.putExtra("Profile", profile);
        setResult(RESULT_OK, intent);

        finish();
    }
}
