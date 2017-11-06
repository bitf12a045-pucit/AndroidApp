package com.image_app.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ruhiya.awsomeimageapp.R;
import com.image_app.utility.ImageLoader;

import java.util.List;

import butterknife.BindView;

public class ImageAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    private Context mContext;

    List<String> data;



    public ImageAdapter(Context contxt,List<String> urls) {
        super();
        mContext = contxt;
        imageLoader=new ImageLoader(mContext);
        data = urls;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        if (convertView == null) {
            inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.image_layout, null);

            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            imageLoader.DisplayImage(data.get(position), image);

        }

        return convertView;
    }

}
