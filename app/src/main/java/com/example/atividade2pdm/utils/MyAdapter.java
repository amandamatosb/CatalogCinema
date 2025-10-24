package com.example.atividade2pdm.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.atividade2pdm.R;
import com.example.atividade2pdm.models.Movie;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Movie> list;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public MyAdapter(Context context, ArrayList<Movie> list) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie currentMovie = list.get(position);
        holder.data.setText(currentMovie.getTitle());

        int radius = 16;

        Glide.with(holder.itemView.getContext())
                .load(currentMovie.getPoster())
                .placeholder(R.drawable.placeholder)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data;
        ImageView poster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            poster = itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
}
