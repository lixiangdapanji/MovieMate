package com.coen268.moviemate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MovieInfoActivity extends AppCompatActivity {

    public static final String LOG_TAG = MovieInfoActivity.class.getSimpleName();

    Intent intent;
    Button nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        intent = new Intent(this, NarBar.class);

        nav = (Button) findViewById(R.id.nav_button);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }

        });

    }

}
