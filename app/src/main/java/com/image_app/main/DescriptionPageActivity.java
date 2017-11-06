package com.image_app.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ruhiya.awsomeimageapp.R;
import com.image_app.cache.ImageLoader;


public class DescriptionPageActivity extends Activity {
    public ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.description);
        imageLoader=new ImageLoader(this);

        Bundle bundle = getIntent().getExtras();

       TextView title = (TextView)findViewById(R.id.desc_page_heading);
       title.setText((String)bundle.get("title"));

        TextView site = (TextView)findViewById(R.id.desc_page_res);
        site.setText((String) bundle.getString("site"));

        TextView url = (TextView)findViewById(R.id.desc_page_content_type);
        url.setText((String) bundle.getString("url"));

        ImageView image = (ImageView)findViewById(R.id.large_img);
        imageLoader.DisplayImage((String) bundle.getString("url"), this,image,R.drawable.no_image);

    }

}
