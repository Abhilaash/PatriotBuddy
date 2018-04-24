package cs321.patriotbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cs321.patriotbuddy.R;

public class ChatAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<ChatMessage> list;
    private Context context;

    public ChatAdapter(Context context, ArrayList<ChatMessage> chats) {
        this.list = chats;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    public boolean contains(ChatMessage item) {
        return list.contains(item);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chatviewitem, null);
        }

        TextView user = view.findViewById(R.id.message_user);
        TextView message = view.findViewById(R.id.message_text);
        TextView time = view.findViewById(R.id.message_time);

////        user.setText(list.get(position).user);
////        message.setText(list.get(position).message);
////        Date d = new Date(list.get(position).time);
//        new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)").format(d);
        //DateFormat.format("dd-MM-yyyy (HH:mm:ss)", list.get(position).time);

//        time.setText(new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)").format(d));

        return view;
    }
}
