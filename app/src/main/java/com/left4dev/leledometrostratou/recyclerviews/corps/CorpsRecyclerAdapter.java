package com.left4dev.leledometrostratou.recyclerviews.corps;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.spinners.corps.CorpsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CorpsRecyclerAdapter extends RecyclerView.Adapter<CorpsRecyclerAdapter.ViewHolder> {

    private String[] Titles;
    private int[] Images;
    private Context mContext;

    public CorpsRecyclerAdapter(@Nullable Context context,String[] Titles1,int[] Images1) {
        this.Titles = Titles1;
        this.Images = Images1;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View convertView = mInflater.inflate(R.layout.corps_recycler,parent,false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getFlag().setImageResource(Images[position]);
        holder.getTitleCorp().setText(Titles[position]);

    }

    @Override
    public int getItemCount() {
        return Titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private  final ImageView Flag;
        private final TextView TitleCorp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Flag = itemView.findViewById(R.id.imageViewCorpBadge);
            TitleCorp = itemView.findViewById(R.id.textViewCorpName);
        }

        public ImageView getFlag()
        {
            return Flag;
        }

        public TextView getTitleCorp()
        {
            return TitleCorp;
        }
    }
}
