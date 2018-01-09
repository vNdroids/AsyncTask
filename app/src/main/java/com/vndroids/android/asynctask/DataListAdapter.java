package com.vndroids.android.asynctask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android1 on 9/1/18.
 */

public class DataListAdapter extends BaseAdapter {
    ArrayList<PostData_Model> al_data_models;
    public DataListAdapter(ArrayList<PostData_Model> al_data_models) {
        this.al_data_models=al_data_models;

    }

    @Override
    public int getCount() {
        return al_data_models.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        TextView tv_user_id=(TextView)view.findViewById(R.id.tv_user_id);
        TextView tv_post_id=(TextView)view.findViewById(R.id.tv_post_id);
        TextView tv_decription=(TextView)view.findViewById(R.id.tv_decription);
        TextView tv_title=(TextView)view.findViewById(R.id.tv_title);
        tv_user_id.setText("User ID: "+al_data_models.get(position).getUser_id());
        tv_post_id.setText("Post ID"+al_data_models.get(position).getPost_id());
        tv_decription.setText("Description: "+al_data_models.get(position).getPost_description());
        tv_title.setText("Title: "+al_data_models.get(position).getPost_title());





        return view;
    }
}
