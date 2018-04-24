package cs321.patriotbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    private boolean isEmailValid(String email) {
        return email.toLowerCase().contains("@gmu.edu") || email.toLowerCase().contains("@masonlive.gmu.edu");
    }

    private boolean attemptRegister(String email,String password, String confirmPassword) {

        // Store values at the time of the login attempt.
        View focusView;

//        // Check for a valid email address.
        if (!isEmailValid(email) || TextUtils.isEmpty(email)) {
            focusView = mEmailView;
            mEmailView.setError("Email is not valid");
            focusView.requestFocus();
            return false;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || password.length() < 4) {
            focusView = mPasswordView;
            mPasswordView.setError("Password is not valid");
            focusView.requestFocus();
            return false;
        }

        if(!confirmPassword.equals(password)) {
            focusView = mPasswordView;
            mPasswordView.setError("Passwords are not the same");
            focusView.requestFocus();
            return false;
        }
        return true;
    }


    public void register(View view) {
        EditText editTextName = findViewById(R.id.name_register);
        final String name = editTextName.getText().toString();
        mEmailView = findViewById(R.id.email);
        final String email = mEmailView.getText().toString();
        mPasswordView = findViewById(R.id.password);
        String password = mPasswordView.getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.confirmPassword)).getText().toString();
//        Log.e("Email: ", email);
//        Log.e("Password: ", password);
        if(attemptRegister(email, password, confirmPassword)) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
            HashMap<String, String> dataMap = new HashMap<>();
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
                }
            });
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
                    assert user != null;
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
                        View focusView = mEmailView;
                        mEmailView.setError("Email is already being used");
                        focusView.requestFocus();
                    }
                }
            });
        }
    }
}

