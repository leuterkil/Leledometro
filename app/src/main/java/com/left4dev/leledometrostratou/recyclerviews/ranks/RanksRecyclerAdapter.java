package com.left4dev.leledometrostratou.recyclerviews.ranks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.recyclerviews.corps.CorpsRecyclerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RanksRecyclerAdapter extends RecyclerView.Adapter<RanksRecyclerAdapter.ViewHolder> {

    private String[] Titles;
    private int[] Images;
    private Context mContext;

    public RanksRecyclerAdapter(@Nullable Context context, String[] Titles1, int[] Images1) {
        this.Titles = Titles1;
        this.Images = Images1;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RanksRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View convertView = mInflater.inflate(R.layout.ranks_recycler,parent,false);
        return new RanksRecyclerAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull RanksRecyclerAdapter.ViewHolder holder, int position) {
        holder.getBadge().setImageResource(Images[position]);
        holder.getTitleRank().setText(Titles[position]);

    }

    @Override
    public int getItemCount() {
        return Titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private  final ImageView Badge;
        private final TextView TitleRank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Badge = itemView.findViewById(R.id.imageViewRanksRecycler);
            TitleRank = itemView.findViewById(R.id.textViewRanksRecycler);
        }

        public ImageView getBadge()
        {
            return Badge;
        }

        public TextView getTitleRank()
        {
            return TitleRank;
        }
    }
}
