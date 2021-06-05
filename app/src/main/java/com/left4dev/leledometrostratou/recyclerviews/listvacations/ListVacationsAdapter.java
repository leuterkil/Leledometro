package com.left4dev.leledometrostratou.recyclerviews.listvacations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.recyclerviews.ranks.RanksRecyclerAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListVacationsAdapter extends RecyclerView.Adapter<ListVacationsAdapter.ViewHolder> {

    private String[] Titles,from,to;
    private Context mContext;

    public ListVacationsAdapter(Context context,String[] titles, String[] from, String[] to) {
        this.mContext = context;
        Titles = titles;
        this.from = from;
        this.to = to;
    }

    @NonNull
    @Override
    public ListVacationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View convertView = mInflater.inflate(R.layout.vacation_recycler_view,parent,false);
        return new ListVacationsAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListVacationsAdapter.ViewHolder holder, int position) {
        holder.getTitleVacation().setText(Titles[position]);
        holder.getStartDate().setText(from[position]);
        holder.getEndDate().setText(to[position]);
    }

    @Override
    public int getItemCount() {
        return Titles.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView TitleVacation,startDate,endDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            endDate = itemView.findViewById(R.id.textViewEndDate);
            startDate = itemView.findViewById(R.id.textViewStartDate);
            TitleVacation = itemView.findViewById(R.id.textViewTitleOfVacation);
        }


        public TextView getTitleVacation()
        {
            return TitleVacation;
        }
        public TextView getStartDate()
        {
            return startDate;
        }
        public TextView getEndDate()
        {
            return endDate;
        }
    }
}
