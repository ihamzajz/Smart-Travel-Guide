package com.example.smarttravelguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AirportAdapter extends FirebaseRecyclerAdapter<AirportDataHolder,AirportAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param optionsairport
     */
    public AirportAdapter(@NonNull FirebaseRecyclerOptions<AirportDataHolder> optionsairport) {
        super(optionsairport);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull AirportDataHolder model) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_rv_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name, desc;

        // Spinner province;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.airport_image);
            name = (TextView) itemView.findViewById(R.id.airport_name);
            desc = (TextView) itemView.findViewById(R.id.airport_desc);
            //province = (Spinner) itemView.findViewById(R.id.p_city_province);
        }
    }
}
