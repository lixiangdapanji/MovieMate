package com.coen268.moviemate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfilePage extends Activity {

    Button watchLater, likedMovies, dislikedMovies;
    Button nav;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        nav = (Button) findViewById(R.id.nav_button);

        watchLater = (Button) findViewById(R.id.watch_later);
        likedMovies = (Button) findViewById(R.id.liked_movies);
        dislikedMovies = (Button) findViewById(R.id.disliked_movies);

        intent = new Intent(this, NarBar.class);

        nav = (Button) findViewById(R.id.nav_button);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }

        });

        watchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show user's watch later list
                Toast.makeText(ProfilePage.this, "Watch Later",Toast.LENGTH_SHORT).show();
            }
        });

        likedMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show user's likes list
                Toast.makeText(ProfilePage.this, "Liked Movies",Toast.LENGTH_SHORT).show();
            }
        });

        dislikedMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show user's dislikes list
                Toast.makeText(ProfilePage.this, "Disliked Movies",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
