package com.coen268.moviemate;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    void activateToolBar(Boolean enableHome) {
        Log.d(TAG, "activateToolBar: starts");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            if(toolbar != null) {
                setSupportActionBar(toolbar);//? setActionBar
                actionBar = getSupportActionBar();//? getActionBar
            }
        }
        if(actionBar != null) {
            // 顶上返回的箭头
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}
