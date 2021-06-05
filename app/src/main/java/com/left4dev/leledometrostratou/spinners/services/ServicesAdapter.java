package com.left4dev.leledometrostratou.spinners.services;

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

public class ServicesAdapter extends ArrayAdapter<String> {

    private String[] types;
    private Context mContext;

    public ServicesAdapter(@NonNull Context context, String[] types) {
        super(context, R.layout.services_spinner);
        this.mContext = context;
        this.types = types;

    }

    @Override
    public int getCount() {
        return types.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ServicesAdapter.ViewHolder mViewHolder = new ServicesAdapter.ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.services_spinner, parent, false);
            mViewHolder.Type = (TextView) convertView.findViewById(R.id.textViewServiceType);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ServicesAdapter.ViewHolder) convertView.getTag();
        }
        mViewHolder.Type.setText(types[position]);

        return convertView;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        TextView Type;
    }
}
