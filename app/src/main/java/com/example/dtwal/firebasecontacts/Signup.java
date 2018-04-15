package com.example.dtwal.firebasecontacts;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    EditText firstName, lastName, signUpEmail, signUpPassword, confirmPassword;
    Button signUpConfirmButton, cancelButton;
    ImageButton camera;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        signUpEmail = findViewById(R.id.emailEditText);
        signUpPassword = findViewById(R.id.newPasswordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        signUpConfirmButton = findViewById(R.id.signUpConfirmButton);
        cancelButton = findViewById(R.id.cancelButton);
        camera = findViewById(R.id.imageButton);

        mAuth = FirebaseAuth.getInstance();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUpConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() ||
                        signUpEmail.getText().toString().isEmpty() || signUpPassword.getText().toString().isEmpty() ||
                        confirmPassword.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else if (!(signUpPassword.getText().toString().equals(confirmPassword.getText().toString()))) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(Signup.this, "we good", Toast.LENGTH_SHORT).show();

                    mAuth.createUserWithEmailAndPassword(signUpEmail.getText().toString().trim(), signUpPassword.getText().toString().trim())
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                       // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Signup.this, "Sign Up Successful!",Toast.LENGTH_SHORT).show();
                                       // updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }

                //finish();
            }
        });


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePicture.resolveActivity(getPackageManager()) != null) {
                   // startActivityForResult);
                }
            }
        });

    }
}
