package com.coen268.moviemate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private static final String LOG_TAG = MoviesActivity.class.getName();

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
    List<Long> listIds = new ArrayList<>();

    Button nav;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, NarBar.class);

        nav = (Button)findViewById(R.id.nav_button);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }

        });

        new FetchMovies().execute();
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
                getIdList(movieList);
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

        private void getIdList(List<Movie> movieList) {
            for (Movie m : movieList) {
                listIds.add(m.id);
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
            MovieAdapter adapter = new MovieAdapter(litImages, listIds, getBaseContext());
            pager.setAdapter(adapter);
        }
    }

}

