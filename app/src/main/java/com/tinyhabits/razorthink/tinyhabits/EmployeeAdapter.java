package com.tinyhabits.razorthink.tinyhabits;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Deepak Detni on 02-02-2017.
 */

public class EmployeeAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<EmployeesDetails> employeesDetails = new ArrayList<>();
    String uid;
    ViewHolder viewHolder;

    public EmployeeAdapter(Context context, ArrayList<EmployeesDetails> employeesDetails, String uid){
        mContext = context;

        if (employeesDetails != null) {
            this.employeesDetails = employeesDetails;
        }

        this.uid = uid;
    }

    @Override
    public int getCount() {
        return employeesDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return employeesDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new ViewHolder();

        View customView;

        if (convertView == null){
            customView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }
        else{
            customView = convertView;
        }

        viewHolder.employeeName = (TextView)customView.findViewById(R.id.employee_name_text);
        viewHolder.employeeImageView = (CircleImageView)customView.findViewById(R.id.employee_pic);

        viewHolder.employeeName.setText(employeesDetails.get(position).getName());
        Picasso.with(mContext).load(employeesDetails.get(position).getPhotoUrl()).resize(480, 480).into(viewHolder.employeeImageView);

        return customView;
    }

    public static class ViewHolder{
        TextView employeeName;
        CircleImageView employeeImageView;
    }
}
