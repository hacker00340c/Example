package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
public class RegisterActivity extends AppCompatActivity {
public EditText inputMail;
public EditText inputName;
public EditText inputPassword;
public String name;
public String mail;
public String password;
public TextView login;
public Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
      register = (Button) findViewById(R.id.register);
      inputMail = (EditText) findViewById(R.id.mail);
        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.pass);
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (inputMail == null ){
                Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_LONG).show();
            }
             else if (inputName == null ){
                  Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_LONG).show();
              }
             else if (inputPassword == null ){
                Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_LONG).show();
              }
             else {
                 name = inputName.getText().toString();
                 mail = inputMail.getText().toString();
                 password = inputPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(RegisterActivity.this , StartActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this , "We ran into a problem" ,Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}
        }
        );
    }
}