package com.coen268.moviemate;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * An {@link MovieAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Movie} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class MovieAdapter extends PagerAdapter {
    List<Integer> listImages;
    Context context;
    LayoutInflater layoutInflater;

    public MovieAdapter(List<Integer> listImages, Context context) {
        this.listImages = listImages;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.moive_list_item, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(listImages.get(position));
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "memeda", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}