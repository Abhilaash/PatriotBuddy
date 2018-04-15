package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FriendsDisplay extends AppCompatActivity {

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_display);

        profile = (Profile)getIntent().getSerializableExtra("Profile");

    }

    protected void addFriends(View view){
        Intent intent = new Intent(this, FriendsDisplay.class);
        intent.putExtra("Profile", profile);
        startActivityForResult(intent, 1);
    }
}
