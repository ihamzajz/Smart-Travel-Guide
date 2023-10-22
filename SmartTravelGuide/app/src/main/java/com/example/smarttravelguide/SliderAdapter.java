package com.example.smarttravelguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int[] images = {
            R.drawable.adventure,
            R.drawable.religious,
//            R.drawable.popular_cities,
//            R.drawable.mountain_hiking
    };
    int[] headings = {
            R.string.adventure_tourism,
            R.string.religious_tourism,
//            R.string.third_slide_title,
//            R.string.fourth_slide_title
    };
    int[] descriptions = {
            R.string.adventure_tourism,
            R.string.religious_tourism,
//            R.string.third_slide_desc,
//            R.string.fourth_slide_desc
    };

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //To request the system that we want to use layout
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Going to the view that we created
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        //Hooks
        ImageView imageView = view.findViewById(R.id.slider_image);
       // TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);


        imageView.setImageResource(images[position]);
        //heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
