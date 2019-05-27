package com.coen268.moviemate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coen268.moviemate.data.MateProvider;

public class SignInActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button logIn;
    Button signUp;

    private MateProvider mateProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        logIn = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.link_signup);


        mateProvider = new MateProvider();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = mateProvider.checkUserExist(username.getText().toString(), password.getText().toString());

                if(isExist){
                    Intent intent = new Intent(getApplicationContext(), MoviesActivity.class);
                    //intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                } else {
                    password.setText(null);
                    Toast.makeText(getApplicationContext(), "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    //intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
            }

        });
    }




}
