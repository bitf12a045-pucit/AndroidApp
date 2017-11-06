package com.image_app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ruhiya.awsomeimageapp.R;
import com.image_app.cache.ImageLoader;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    private Activity mContext;

    List<String> data;



    public ImageAdapter(Activity contxt, List<String> urls) {
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

            String imgPath=data.get(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            imageLoader.DisplayImage(imgPath, mContext,image,R.drawable.no_image);

            convertView.setTag(imgPath);

        }

        return convertView;
    }

}
