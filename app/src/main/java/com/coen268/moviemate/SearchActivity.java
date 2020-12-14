package com.coen268.moviemate;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import android.widget.SearchView;

//Guide:
//https://developer.android.com/guide/topics/search/search-dialog
public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activateToolBar(true);
        Log.d(TAG, "onCreate: ends");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: starts");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView  = (SearchView) menu.findItem(R.id.action_search2).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        mSearchView.setSearchableInfo(searchableInfo);
        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             Log.d(TAG, "onQueryTextSubmit: called" + query);
             Toast.makeText(SearchActivity.this, "USER SEARCHED " + query, Toast.LENGTH_SHORT).show();
             finish();
             return true;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
             return false;
         }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();
                return false;
            }
        });


        Log.d(TAG, "onCreateOptionsMenu: ends");

        return true;
    }
}
