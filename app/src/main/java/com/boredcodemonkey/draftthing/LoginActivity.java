package com.boredcodemonkey.draftthing;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();


        firebaseAuth = FirebaseAuth.getInstance();

    }

    private void initUI() {
        txtUsername = (EditText) this.findViewById(R.id.login_username);
        txtPassword = (EditText) this.findViewById(R.id.login_password);
        btnLogin = (Button) this.findViewById(R.id.login_attemptLogin);
        btnLogin.setOnClickListener(onBtnLoginClicked);
    }

    private final View.OnClickListener onBtnLoginClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String userName = txtUsername.getText().toString();
            String password = txtPassword.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(LoginActivity.this, onLoginAttemptComplete);
        }
    };

    private final OnCompleteListener<AuthResult> onLoginAttemptComplete = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "wooo!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(LoginActivity.this, "booo!", Toast.LENGTH_LONG).show();
            }
        }
    };
}
