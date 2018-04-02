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

public class DeleteItemListAdapter<T> extends BaseAdapter implements ListAdapter {

    private ArrayList<T> list = new ArrayList<T>();
    private Context context;

    public DeleteItemListAdapter(Context context, T[] array) {
        this.list = new ArrayList<T>(Arrays.asList(array));
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
            view = inflater.inflate(R.layout.listviewdelete, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}