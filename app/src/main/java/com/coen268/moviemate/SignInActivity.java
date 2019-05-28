package com.coen268.moviemate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coen268.moviemate.data.MateContract.MateEntry;


public class SignInActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button logIn;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        logIn = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.login_signup);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = checkUserExist(username.getText().toString(), password.getText().toString());

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

    public boolean checkUserExist(String username, String password) {
        String[] projection = {
                MateEntry.COLUMN_MATE_NAME,
                MateEntry.COLUMN_MATE_PASSWORD,
        };

        String selection = "name=? and password = ?";
        String[] selectionArgs = {username, password};

        // Perform a query on the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContentResolver().query(
                MateEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                selection,                   // Selection criteria
                selectionArgs,                   // Selection criteria
                null);                  // The sort order for the returned rows
        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }

}
