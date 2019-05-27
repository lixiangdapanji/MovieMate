package com.coen268.moviemate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends BaseActivity {

    private static final String TAG = "MoviesActivity";

    private static final String API_KEY = "24e5ade166fa5bca1990279da2746ba3";

    private static final String movie_id = "";

    /** URL for popular movies data */
    private static final String POPULAR_MOIVES_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

    /** URL for similar movies data */
    private static final String SIMILAR_MOIVES_URL =
            "https://api.themoviedb.org/3/movie/" + movie_id +"/similar?api_key=" + API_KEY + "&page=2";


    List<Movie> movieList = new ArrayList<>();
    List<String> litImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        activateToolBar(false);
        new FetchMovies().execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu: starts");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search1){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // mProgressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... voids) {

            movieList = new ArrayList<>();

            if(networkStatus(MoviesActivity.this)){
                /** Get popular movies*/
                movieList = QueryUtils.fetchPopularMovieDate(POPULAR_MOIVES_URL);
                /**Get all the posters from the movie list*/
                getPosterList(movieList);

            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(MoviesActivity.this);
                dialog.setTitle("No network connection");
                dialog.setMessage("Haha");
                dialog.setCancelable(false);
                dialog.show();
            }
            return null;
        }

        private void getPosterList(List<Movie> movieList) {
            String  IMAGE_BASE_URI = "https://image.tmdb.org/t/p/w500/";
            for (Movie m : movieList) {
                litImages.add( IMAGE_BASE_URI + m.posterPath);
            }
        }

        public  Boolean networkStatus(Context context){
            ConnectivityManager manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()){
                return true;
            }
            return false;
        }


        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            //mProgressBar.setVisibility(View.INVISIBLE);
            //Load popular movies by default
            HorizontalInfiniteCycleViewPager pager = findViewById(R.id.horiontal_cycle);
            MovieAdapter adapter = new MovieAdapter(litImages, getBaseContext());
            pager.setAdapter(adapter);
        }
    }

}

