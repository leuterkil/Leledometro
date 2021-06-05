package com.left4dev.leledometrostratou.spinners.vacation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.spinners.services.ServicesAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VacationsAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] vacations;

    public VacationsAdapter(@NonNull Context context, String[] vacations)
    {
        super(context, R.layout.vacation_spinner);
        this.context = context;
        this.vacations = vacations;
    }

    @Override
    public int getCount() {
        return vacations.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VacationsAdapter.ViewHolder mViewHolder = new VacationsAdapter.ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.vacation_spinner, parent, false);
            mViewHolder.Type = (TextView) convertView.findViewById(R.id.textViewTypeOfVacation);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (VacationsAdapter.ViewHolder) convertView.getTag();
        }
        mViewHolder.Type.setText(vacations[position]);

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
