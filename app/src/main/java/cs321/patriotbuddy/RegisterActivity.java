package cs321.patriotbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button rtoFirebase;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

    }

    private boolean isEmailValid(String email) {
        return email.contains("@gmu.edu");
    }

    private boolean attemptRegister(String email,String password) {

        // Store values at the time of the login attempt.
        boolean cancel = false;
        View focusView = null;

//        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            focusView = mEmailView;
            mEmailView.setError("Email is required!");
            focusView.requestFocus();
            return false;
        } else if (!isEmailValid(email)) {
            focusView = mEmailView;
            mEmailView.setError("Please enter a valid email!");
            focusView.requestFocus();
            return false;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            focusView = mEmailView;
            mEmailView.setError("password  is not valid");
            focusView.requestFocus();
            return false;
        }
        return true;
    }


    public void register(View view) {
        EditText editTextName = findViewById(R.id.name_register);
        final String name = editTextName.getText().toString();
        EditText editTextEmail = findViewById(R.id.email);
        final String email = editTextEmail.getText().toString();
        EditText editTextPassword = findViewById(R.id.password);
        String password = editTextPassword.getText().toString();
//        Log.e("Email: ", email);
//        Log.e("Password: ", password);
        if(attemptRegister(email, password)) {
            rtoFirebase = (Button) findViewById(R.id.register_button); //when register button clicked
            mDatabase = FirebaseDatabase.getInstance().getReference().child("users");//stores the user to the database
            HashMap<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("Name", name);
            dataMap.put("Email", email);
            //mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            mDatabase.child(name).setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
           /*
           Sends a message to the user to confirm that the data has been succesffully stored into the
           firebase database
            */
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Success!!..", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "something went wrong..", Toast.LENGTH_LONG).show();
                    }
                }
            });
            //});
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                Log.e("HELLO", "createUserWithEmail:success");
                                final FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("Name set", "User profile updated.");
                                                }
                                            }
                                        });
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                //Toast.makeText(this,"Please confirm the link in your email and login!",Toast.LENGTH_SHORT).show();
                                                FirebaseAuth.getInstance().signOut();
                                                Toast verifyEmailToast = Toast.makeText(RegisterActivity.this, "Please click the link in your email! " +
                                                        "Welcome Patriot!", Toast.LENGTH_LONG);
                                                verifyEmailToast.show();
                                                finish();
                                            }
                                        });
                            } else if (!(task.isSuccessful())) {
                                // If registration in fails, display a message to the user.
                                Log.e("HELLO", "createUserWithEmail:failure", task.getException());
                                Toast emailAlreadyUsed = Toast.makeText(RegisterActivity.this, "This email is already being used",
                                        Toast.LENGTH_LONG);
                                emailAlreadyUsed.show();
                            }
                        }
            });
        }
    }
}

