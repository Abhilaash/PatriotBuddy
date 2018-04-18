package cs321.patriotbuddy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


    }

    public void register(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        EditText nameText=findViewById(R.id.name_register);
        final String name_text2= nameText.getText().toString();
        EditText emailText = findViewById(R.id.email);
        final String email = emailText.getText().toString();
        EditText passwordText = findViewById(R.id.password);
        String password = passwordText.getText().toString();
        Log.e("Email: ", email);
        Log.e("Password: ", password);

        Profile np=new Profile(name_text2);
        myRef=db.getReference("User//"+ np.getPname());
        myRef.setValue(np);

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
                                            Intent intent = new Intent(RegisterActivity.this, ProfileDisplay.class);
                                            intent.putExtra("user", user);
                                            intent.putExtra("username", email);
                                            //Toast.makeText(this,"Please confirm the link in your email and login!",Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                            Toast myT=Toast.makeText(RegisterActivity.this,"Please click the link in your email! " +
                                                    "Welcome Patriot! :D",Toast.LENGTH_SHORT);
                                            myT.setDuration(Toast.LENGTH_LONG);
                                            myT.show();
                                            finish();
                                        }
                                    });
                        } else {
                            // If registration in fails, display a message to the user.
                            Log.e("HELLO", "createUserWithEmail:failure", task.getException());
                            Toast t2=Toast.makeText(RegisterActivity.this, "A valid login already exists!",
                                    Toast.LENGTH_SHORT);
                            t2.setDuration(Toast.LENGTH_LONG);
                            t2.show();
                        }

                        // ...
                    }
                });
    }
}
