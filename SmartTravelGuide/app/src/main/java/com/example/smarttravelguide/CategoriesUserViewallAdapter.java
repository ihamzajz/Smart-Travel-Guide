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

public class CategoriesUserViewallAdapter extends FirebaseRecyclerAdapter<CategoriesDataHolder,CategoriesUserViewallAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param optionsc
     */
    public CategoriesUserViewallAdapter(@NonNull FirebaseRecyclerOptions<CategoriesDataHolder> optionsc) {
        super(optionsc);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CategoriesDataHolder model) {
        holder.name.setText(model.getName());
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_user_viewall_card,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        Spinner province;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.catt_image);
            name = (TextView) itemView.findViewById(R.id.catt_name);
            //province = (Spinner) itemView.findViewById(R.id.p_city_province);
        }
    }
}
