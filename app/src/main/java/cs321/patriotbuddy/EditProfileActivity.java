package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    private Profile profile;
    private EditText nameText;
    private SearchView searchView;
    private ListView classList;
    private TextButtonListAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        profile = (Profile)getIntent().getSerializableExtra("Profile");

        nameText = findViewById(R.id.editText);
        nameText.setText(profile.name);

        searchView = findViewById(R.id.searchView);
        searchView.setOnSearchClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                searchParamEdit("");
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String crn) {
                searchParamEdit(crn);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String crn) {
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose(){
                classList.setAdapter(adapter);
                searchView.setQuery("", false);
                searchView.clearFocus();
                searchView.onActionViewCollapsed();
                setRegisteredCourseAdapter();
                return true;
            }
        });

        classList = findViewById(R.id.classList);
        setRegisteredCourseAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                profile = (Profile)intent.getSerializableExtra("Profile");
                searchView.onActionViewCollapsed();
                setRegisteredCourseAdapter();
            }
        }
    }

    private void searchParamEdit(String crn){

        //TODO: replace with database queries
        ArrayList<Course> allCourses = new ArrayList<Course>();
        allCourses.add(new Course("123", "CS 321", "Dr. S"));
        allCourses.add(new Course("678", "CS 465", "Dr. K"));
        allCourses.add(new Course("495", "CS 325", "Dr. G"));
        allCourses.add(new Course("657", "CS 101", "Dr. S"));
        allCourses.add(new Course("168", "CS 105", "Dr. K"));
        allCourses.add(new Course("6413", "CS 310", "Dr. C"));
        allCourses.add(new Course("12", "CS 262", "Dr. N"));
        allCourses.add(new Course("37896", "CS 367", "Dr. K"));
        allCourses.add(new Course("2318", "CS 475", "Dr. D"));
        allCourses.add(new Course("156", "CS 450", "Dr. L"));
        allCourses.add(new Course("761", "CS 480", "Dr. M"));
        allCourses.add(new Course("685", "CS 451", "Dr. P"));
        allCourses.add(new Course("438", "CS 471", "Dr. R"));
        allCourses.add(new Course("963", "CS 425", "Dr. T"));

        ArrayList<Course> searches = new ArrayList<Course>();
        for(int i = 0; i < allCourses.size(); i++){
            if(allCourses.get(i).crn.contains(crn) && !profile.courses.contains(allCourses.get(i))){
                searches.add(allCourses.get(i));
            }
        }

        if(searches.size() == 0){
            showCourseCreation(crn);
            return;
        }

        TextButtonListAdapter<Course> searchAdapter = new TextButtonListAdapter<Course>(this,
                searches, "Add");

        class AddItemTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new AddItemTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                profile.courses.add((Course) adapter.list.get(position));
                adapter.notifyDataSetChanged();
            }
        }

        searchAdapter.listener = new AddItemTextButtonListener();
        classList.setAdapter(searchAdapter);
    }

    private void showCourseCreation(final String crn){

        ArrayList<String> message = new ArrayList<String>();
        message.add("CRN not found, would you like to create and join class: " + crn);

        TextButtonListAdapter<String> creationAdapter = new TextButtonListAdapter<String>(this,
                message, "Create");

        final EditProfileActivity ref = this;

        class CreateCourseTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new CreateCourseTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ref, CourseCreationActivity.class);
                intent.putExtra("Profile", profile);
                intent.putExtra("CRN", crn);
                startActivityForResult(intent, 1);
                adapter.notifyDataSetChanged();
            }
        }

        creationAdapter.listener = new CreateCourseTextButtonListener();
        classList.setAdapter(creationAdapter);
    }

    private void setRegisteredCourseAdapter(){

        adapter = new TextButtonListAdapter<Course>(this,
                profile.courses, "Delete");

        class DeleteItemTextButtonListener extends TextButtonListAdapter.TextButtonListener {
            @Override
            public TextButtonListAdapter.TextButtonListener clone(){
                return new DeleteItemTextButtonListener();
            }

            @Override
            public void onClick(View v) {
                profile.courses.remove(position);
                adapter.notifyDataSetChanged();
            }
        }

        adapter.listener = new DeleteItemTextButtonListener();
        classList.setAdapter(adapter);
    }

    protected void save(View view){

        profile.name = nameText.getText().toString();

        Course[] c = profile.getCourses();
        for(int i = 0; i < c.length; i++){
            if(!adapter.contains(c[i])){
                profile.courses.remove(c[i]);
            }
        }

        Intent intent = new Intent();
        intent.putExtra("Profile", profile);
        setResult(RESULT_OK, intent);

        finish();
    }
}
