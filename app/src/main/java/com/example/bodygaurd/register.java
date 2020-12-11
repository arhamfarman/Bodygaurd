package com.example.bodygaurd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private Button register;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        firstName = findViewById(R.id.editFristName);
        lastName = findViewById(R.id.editLastName);
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_firstName = firstName.getText().toString();
                String txt_lastName = lastName.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email)|| TextUtils.isEmpty(txt_password)){
                    Toast.makeText(register.this,"Empty Credential", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6){
                    Toast.makeText(register.this, "Password  Too Short", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txt_email,txt_firstName,txt_lastName,txt_password );
                }
            }
        });

    }

    private void registerUser(String txt_email,String txt_firstName,String txt_lastName,String txt_password ) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(txt_email,txt_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful( )) {
                            User user = new User(txt_email, txt_firstName, txt_lastName);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child((FirebaseAuth.getInstance().getCurrentUser().getUid()))
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, "User has been registered succefully.",
                                                Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(register.this, "User Not registered.",
                                                Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {

                            Toast.makeText(register.this, "User registration Failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}





