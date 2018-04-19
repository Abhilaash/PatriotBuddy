package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FriendsDisplayActivity extends AppCompatActivity {

    private Profile profile;
    private ListView friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_display);

        profile = (Profile)getIntent().getSerializableExtra("Profile");
        friendsList = findViewById(R.id.friendsList);

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

    private void setUpDisplay(){

        TextButtonListAdapter<Student> adapter = new TextButtonListAdapter<Student>(this,
                profile.friends, "Chat");

        class ChatFriendTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new ChatFriendTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                //TODO: chat with friends
                //profile.courses.remove(position);
                //adapter.notifyDataSetChanged();
            }
        }

        adapter.listener = new ChatFriendTextButtonListener();
        friendsList.setAdapter(adapter);
    }

    protected void addFriends(View view){
        Intent intent = new Intent(this, AddFriendsActivity.class);
        intent.putExtra("Profile", profile);
        startActivityForResult(intent, 1);
    }
}
