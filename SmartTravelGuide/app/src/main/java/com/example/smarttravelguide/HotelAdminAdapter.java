package com.example.smarttravelguide;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class HotelAdminAdapter extends FirebaseRecyclerAdapter<HotelDataHolder,HotelAdminAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param optionshoteladmin
     */
    public HotelAdminAdapter(@NonNull FirebaseRecyclerOptions<HotelDataHolder> optionshoteladmin) {
        super(optionshoteladmin);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull HotelDataHolder model) {
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());
        Glide.with(holder.img.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.add_missing_place)
                .error(R.drawable.add_missing_place)
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.hotels_update))
                        .setExpanded(true,1200)
                        .create();



                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.hotel_name_update);
                EditText desc = view.findViewById(R.id.hotel_desc_update);
                EditText imageurl = view.findViewById(R.id.hotel_imagerurl_update);

                Button btn = view.findViewById(R.id.hotel_update_btn);

                name.setText(model.getName());
                desc.setText(model.getDesc());
                imageurl.setText(model.getImageUrl());

                dialogPlus.show();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("desc",desc.getText().toString());
                        map.put("imageUrl",imageurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("hotels")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data cant bo Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("hotels")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_admin_viewall_card,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,desc;
        Spinner province;
        Button btnEdit,btnDelete;

        ProgressBar progressBar1;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.hotel_image);
            name = (TextView) itemView.findViewById(R.id.hotel_name);
            desc = (TextView) itemView.findViewById(R.id.hotel_desc);
            btnEdit = (Button) itemView.findViewById(R.id.hotel_edit);
            btnDelete = (Button) itemView.findViewById(R.id.hotel_delete);

            //province = (Spinner) itemView.findViewById(R.id.p_city_province);
        }
    }
}
