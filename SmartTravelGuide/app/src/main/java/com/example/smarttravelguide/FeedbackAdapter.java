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

public class FeedbackAdapter extends FirebaseRecyclerAdapter<FeedbackHelperClass,FeedbackAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param optionsfb
     */
    public FeedbackAdapter(@NonNull FirebaseRecyclerOptions<FeedbackHelperClass> optionsfb) {
        super(optionsfb);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull FeedbackHelperClass model) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_card,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,desc;
        Spinner province;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.fb_image);
            name = (TextView) itemView.findViewById(R.id.fb_name);
            desc = (TextView) itemView.findViewById(R.id.fb_desc);
            //province = (Spinner) itemView.findViewById(R.id.p_city_province);
        }
    }
}
