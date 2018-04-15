package cs321.patriotbuddy;

import android.widget.ListAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.Arrays;

public class TextButtonListAdapter<T> extends BaseAdapter implements ListAdapter {

    public ArrayList<T> list = new ArrayList<T>();
    private Context context;
    private String buttonText = "";
    public TextButtonListener listener;

    public TextButtonListAdapter(Context context, ArrayList<T> array, String buttonText) {
        this.list = array;
        this.context = context;
        this.buttonText = buttonText;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    public boolean contains(T item) { return list.contains(item); }

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
            view = inflater.inflate(R.layout.listviewadd, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        Button addBtn = (Button)view.findViewById(R.id.add_btn);

        TextButtonListener tempListener = listener.clone();
        tempListener.position = position;
        tempListener.adapter = this;

        addBtn.setOnClickListener(tempListener);
        addBtn.setText(buttonText);

        return view;
    }

    public static abstract class TextButtonListener implements View.OnClickListener {
        public int position;
        public TextButtonListAdapter adapter;
        public abstract TextButtonListener clone();
    }
}