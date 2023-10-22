package com.example.smarttravelguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopularCitiesViewallAdapter extends FirebaseRecyclerAdapter<PopularCitiesDataHolder,PopularCitiesViewallAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PopularCitiesViewallAdapter(@NonNull FirebaseRecyclerOptions<PopularCitiesDataHolder> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PopularCitiesDataHolder model) {
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());
        // holder.province..setSelection(model.getProvince());
        Glide.with(holder.img.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.add_missing_place)
                .error(R.drawable.add_missing_place)
                .into(holder.img);


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pcity_user_viewall_card,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,desc;
        Spinner province;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.pcity_image);
            name = (TextView) itemView.findViewById(R.id.pcity_name);
            desc = (TextView) itemView.findViewById(R.id.pcity_desc);
            //province = (Spinner) itemView.findViewById(R.id.p_city_province);
        }
    }
}
