package com.left4dev.leledometrostratou.spinners.penalty;

import android.content.Context;
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

public class PenaltyAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] Types;

    public PenaltyAdapter(@NonNull Context context, String[] Types)
    {
        super(context, R.layout.spinner_penalty);
        this.Types = Types;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Types.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PenaltyAdapter.ViewHolder mViewHolder = new PenaltyAdapter.ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_penalty, parent, false);
            mViewHolder.TypeOfPenalty = (TextView) convertView.findViewById(R.id.textViewTypeOfPenalty);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (PenaltyAdapter.ViewHolder) convertView.getTag();
        }
        mViewHolder.TypeOfPenalty.setText(Types[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        TextView TypeOfPenalty;
    }


}
