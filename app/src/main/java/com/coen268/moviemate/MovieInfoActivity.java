package com.coen268.moviemate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MovieInfoActivity extends AppCompatActivity {

    public static final String LOG_TAG = MovieInfoActivity.class.getSimpleName();

    private static final String API_KEY = "24e5ade166fa5bca1990279da2746ba3";

    private static String movie_id = "";

    Movie movie;

//    @Override
//    protected void onNewIntent(Intent intent)
//    {
//        super.onNewIntent(intent);
//
//        // set the string passed from the service to the original intent
//        setIntent(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            movie_id = bundle.getString("id");
        }
        Log.i(LOG_TAG, "id is " + movie_id);
    }

    //AsyncTask
    public class FetchTrailer extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if(networkStatus(MovieInfoActivity.this)){
                /** URL for one movie data */
                String MOVIE_TRAILER_URL =
                        "https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=" + API_KEY + "&append_to_response=videos";

                movie = QueryUtils.fetchMovieWithId(MOVIE_TRAILER_URL);
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(MovieInfoActivity.this);
                dialog.setTitle("No network connection");
                dialog.setMessage("Haha");
                dialog.setCancelable(false);
                dialog.show();
            }
            return null;
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

        }
    }

}
