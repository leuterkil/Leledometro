package com.left4dev.leledometrostratou.spinners.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NumbersAdapter extends ArrayAdapter<String> {

    private String[] numbers;
    private Context mContext;

    public NumbersAdapter(@NonNull Context context, String[] numbers) {
        super(context, R.layout.services_spinner);
        this.mContext = context;
        this.numbers = numbers;

    }

    @Override
    public int getCount() {
        return numbers.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NumbersAdapter.ViewHolder mViewHolder = new NumbersAdapter.ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.number_spinner, parent, false);
            mViewHolder.Number = (TextView) convertView.findViewById(R.id.textViewNumber);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (NumbersAdapter.ViewHolder) convertView.getTag();
        }
        mViewHolder.Number.setText(numbers[position]);

        return convertView;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        TextView Number;
    }
}
