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

public class TrendingCitiesViewallAdapter extends FirebaseRecyclerAdapter<TrendingCitiesDataHolder,TrendingCitiesViewallAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options2
     */
    public TrendingCitiesViewallAdapter(@NonNull FirebaseRecyclerOptions<TrendingCitiesDataHolder> options2) {
        super(options2);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TrendingCitiesDataHolder model) {
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());
        //holder.province.setText(model.getProvince());

        Glide.with(holder.img.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.add_missing_place)
                .error(R.drawable.add_missing_place)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tcity_user_viewall_card,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,desc,province;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.tcity_image);
            name = (TextView) itemView.findViewById(R.id.tcity_name);
            desc = (TextView) itemView.findViewById(R.id.tcity_desc);
            // province = (TextView) itemView.findViewById(R.id.pcity_province);
        }
    }
}
