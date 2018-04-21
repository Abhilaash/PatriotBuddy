package cs321.patriotbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<ChatMessage> chats = new ArrayList<ChatMessage>();
    private ChatAdapter adapter;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        adapter = new ChatAdapter(this, chats);
        text = findViewById(R.id.input);

        ListView chat = findViewById(R.id.list_of_messages);
        chat.setAdapter(adapter);
    }

    protected void sendMessage(View view){

        if(text.getText().toString().equals("")){
            return;
        }

        ChatMessage c = new ChatMessage(text.getText().toString(), "Zac", System.currentTimeMillis());
        chats.add(c);
        adapter.notifyDataSetChanged();

        text.setText("");
    }
}
