package com.example.myapplication;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {
    private Context mCtx;

    ItemAdapter(Context mCtx) {
        super(diffCallback);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        // Binding ViewHolder
        final Item items = getItem(position);
        if (items != null) {
            holder.tv1.setText(items.display_name);
            holder.tv2.setText(items.location);
            //     holder.tv3.setText(String.valueOf(items.reputation_change_year));
            holder.tv4.setText(String.valueOf(items.badge_counts.gold));
            holder.tv5.setText(String.valueOf(items.badge_counts.silver));
            holder.tv6.setText(String.valueOf(items.badge_counts.bronze));
            // On Click button jumps to new activity and passes data through Parcelable Library(Serialization)
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mCtx, SecAct.class);

                    i.putExtra("name", items);
                    mCtx.startActivity(i);
                }
            });
            // To set Image from api
            Glide.with(mCtx)
                    .load(items.profile_image)
                    .into(holder.imageView);
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_SHORT).show();
        }

    }


    // Callback is done when item call already exist else false
    private static DiffUtil.ItemCallback<Item> diffCallback = new DiffUtil.ItemCallback<Item>() {

        @Override
        public boolean areItemsTheSame(Item oldItem, Item newItem) {
            return oldItem.account_id == newItem.account_id;
        }

        @Override
        public boolean areContentsTheSame(Item oldItem, Item newItem) {
            return false;
        }
    };

    // Defining ViewHolder
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv6;
        ImageView imageView;
        Button button;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mCtx = itemView.getContext();

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            //tv3=itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
            tv6 = itemView.findViewById(R.id.tv6);
            imageView = itemView.findViewById(R.id.imageView);
            button = (Button) itemView.findViewById(R.id.button);


        }

    }
}
