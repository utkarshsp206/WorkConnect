package com.example.workconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edPassword, edEmail, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.regbtn);
        tv = findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"workconnect",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(RegisterActivity.this, "Fields cannot be Empty!!😁", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Registration Successfull🥳", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Password must be of 8 length, must have a Capital, small character, a number, a symbol🥱", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Passwords don't match!!😢", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    public static boolean isValid(String passwordhere) {

        int f1 = 0, f2 = 0, f3 = 0;

        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < passwordhere.length(); i++) {
                if (Character.isLetter(passwordhere.charAt(i))) {
                    f1 = 1;
                }
            }

            for (int i = 0; i < passwordhere.length(); i++) {
                if (Character.isDigit(passwordhere.charAt(i))) {
                    f2 = 1;
                }
            }

            for (int i = 0; i < passwordhere.length(); i++) {
                char c = passwordhere.charAt(i);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }

            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }

}