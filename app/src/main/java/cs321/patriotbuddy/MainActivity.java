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


    protected void showLoginScreen(View view) {
        intent = new Intent(this, Login.class);
        startActivity(intent);
    }


    protected Profile attemptToLogin() {
        return null;
    }


    protected void loginSuccess() {


    }

    protected void showRegisterScreen(View view) {
        intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
