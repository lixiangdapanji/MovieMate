package com.coen268.moviemate;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * An {@link MovieAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Movie} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class MovieAdapter extends PagerAdapter {

    private static final String LOG_TAG = MovieAdapter.class.getName();

    List<String> listImages;
    List<Long> movieIds;
    Context context;
    LayoutInflater layoutInflater;

    public MovieAdapter(List<String> listImages, List<Long> movieIds, Context context) {
        this.listImages = listImages;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.movieIds = movieIds;
    }

    @Override
    public int getCount() {

        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**fetch the images with Picasso from URL
     * */

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.moive_list_item,null);
        ((ViewPager) container).addView(view);
        final ImageView img = (ImageView) view.findViewById(R.id.imageView);
        Picasso.get()
                .load(listImages.get(position))
                //.placeholder()
                .centerCrop()
                .fit()
                .into(img);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, MovieInfoActivity.class);
                long id = movieIds.get(position);
                Log.i(LOG_TAG, "id is" + id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        return view;
    }
}