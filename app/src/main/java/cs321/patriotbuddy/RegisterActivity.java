package cs321.patriotbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        EditText editTextName = findViewById(R.id.name_register);
        final String name = editTextName.getText().toString();
        EditText editTextEmail = findViewById(R.id.email);
        final String email = editTextEmail.getText().toString();
        EditText editTextPassword = findViewById(R.id.password);
        String password = editTextPassword.getText().toString();
        Log.e("Email: ", email);
        Log.e("Password: ", password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("HELLO", "createUserWithEmail:success");
                            final FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            //Toast.makeText(this,"Please confirm the link in your email and login!",Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                            Toast verifyEmailToast = Toast.makeText(RegisterActivity.this,"Please click the link in your email! " +
                                                    "Welcome Patriot! :D",Toast.LENGTH_LONG);
                                            verifyEmailToast.show();
                                        }
                                    });
                        } else {
                            // If registration in fails, display a message to the user.
                            Log.e("HELLO", "createUserWithEmail:failure", task.getException());
                            Toast emailAlreadyUsed = Toast.makeText(RegisterActivity.this, "This email is already being used!",
                                    Toast.LENGTH_LONG);
                            emailAlreadyUsed.show();
                        }

                        // ...
                    }
                });
    }
}
