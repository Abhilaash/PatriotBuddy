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

public class AddClassListAdapter<T> extends BaseAdapter implements ListAdapter {

    private ArrayList<T> list = new ArrayList<T>();
    private Context context;
    private Profile profile;

    public AddClassListAdapter(Context context, ArrayList<T> array, Profile profile) {
        this.list = array;
        this.context = context;
        this.profile = profile;
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

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
             //   list.remove(position); //or some other task
                profile.addCourse((Course) list.get(position));
                notifyDataSetChanged();
            }
        });

        return view;
    }
}