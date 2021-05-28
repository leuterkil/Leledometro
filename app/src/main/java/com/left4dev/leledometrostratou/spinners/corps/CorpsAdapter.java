package com.left4dev.leledometrostratou.spinners.corps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CorpsAdapter extends ArrayAdapter<String> {
    private String[] Titles;
    private int[] Images;
    private Context mContext;

    public CorpsAdapter(@NonNull Context context,String[] Titles1,int[] Images1) {
        super(context, R.layout.corps_spinner);
        this.Images = Images1;
        this.Titles = Titles1;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Titles.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.corps_spinner, parent, false);
            mViewHolder.Flag = (ImageView) convertView.findViewById(R.id.Flag);
            mViewHolder.TitleCorp = (TextView) convertView.findViewById(R.id.TypeNameCorp);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.Flag.setImageResource(Images[position]);
        mViewHolder.TitleCorp.setText(Titles[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        ImageView Flag;
        TextView TitleCorp;
    }
}
