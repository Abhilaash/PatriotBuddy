package cs321.patriotbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//added comment
//zacs test commit
public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    protected void showLoginScreen(View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    protected void showRegisterScreen(View view) {
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
