package com.example.firebasesignup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

//public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
//
//    EditText editTextEmail,editTextPassword;
//    private FirebaseAuth mAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        editTextEmail=findViewById(R.id.editTextEmail);
//        editTextPassword=findViewById(R.id.editTextPassword);
//
//        mAuth = FirebaseAuth.getInstance();
//        findViewById(R.id.buttonSignup).setOnClickListener(this);
//
//        findViewById(R.id.textViewSignUp).setOnClickListener(this);
//    }
//
//    private void registerUser(){
//        String email=editTextEmail.getText().toString().trim();
//        String password=editTextPassword.getText().toString().trim();
//
//if(email.isEmpty())
//{
//    editTextEmail.setError("Email is Required");
//    editTextEmail.requestFocus();
//    return;
//}
//
//    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//        editTextEmail.setError("Please enter a valid email");
//        editTextEmail.requestFocus();
//        return;
//    }
//if(password.isEmpty()){
//    editTextPassword.setError("password is Required");
//    editTextPassword.requestFocus();
//    return;
//}
//if(password.length()<6){
//    editTextPassword.setError("Minimum Length of password must be 6");
//    editTextPassword.requestFocus();
//    return;
//}
//
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if(task.isSuccessful())
//                {
//                    finish();
//                    startActivity(new Intent(SignupActivity.this,ProfileActivity.class));
//
//                    Toast.makeText(getApplicationContext(),"UserRegistration Successful", Toast.LENGTH_SHORT).show();
//                }else {
//
//                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            }
//        });
//
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId())
//        {
//            case R.id.buttonSignup:
//
//                registerUser();
//                break;
//
//            case R.id.textViewLogin:
//                finish();
//                startActivity(new Intent(this,MainActivity.class));
//                break;
//        }
//    }
//}
public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(SignupActivity.this,ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;

            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}