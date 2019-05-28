package com.coen268.moviemate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchPage extends Activity {

    EditText searchBar;
    Button searchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        searchBar = (EditText) findViewById(R.id.search_option);
        searchButton = (Button) findViewById(R.id.btn_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchPage.this, "Searching!",Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }

        });

    }

}
