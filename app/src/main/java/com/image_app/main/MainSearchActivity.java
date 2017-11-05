package com.image_app.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ruhiya.awsomeimageapp.R;
import com.image_app.adapters.ImageAdapter;
import com.image_app.models.Url;
import com.image_app.utility.HttpRequest;
import com.image_app.utility.ImageLoader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


public class MainSearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnKeyListener {

    @BindView(R.id.searchBar)
    public EditText searchText;

    @BindView(R.id.gridview)
    public GridView grid;

    private final Search search = new Search();
    private ImageAdapter imageAdapter;
    private String keyWord="";
    Handler handler = new Handler();
    List<String> imageUrls;
    List<Url> listUrls = new ArrayList<>();
    ImageLoaderTask imageLoaderTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        imageUrls = new ArrayList<>();
        searchText.setOnKeyListener(this);

        imageLoaderTask = new ImageLoaderTask();
        imageAdapter = new ImageAdapter(this, imageUrls);
        grid.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

    }

    protected void updateItemsInUI() {
        for(Url url : listUrls){
            imageUrls.add(url.getUrl());
        }
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKey (View v,int keyCode, KeyEvent event){
        keyWord = searchText.getText().toString();
        handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    if(imageLoaderTask != null  && imageLoaderTask.getStatus() ==  AsyncTask.Status.RUNNING){
                        imageLoaderTask.cancel(true);
                        imageLoaderTask.execute(keyWord);
                    }
                    else
                        imageLoaderTask.execute(keyWord);
                }
        }, 1*1000);
            return false;
    }


     class ImageLoaderTask extends AsyncTask<String, Void, Void> {

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            updateItemsInUI();
        }

        @Override
        protected Void doInBackground(String... params) {

            String keyword = params[0];
            Search search = new Search();
            try{
                listUrls = search.doSearch(keyword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }
    }




    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
        Intent intent = new Intent(MainSearchActivity.this,DescriptionPageActivity.class);
        Bundle b=new Bundle();
        b.putString("keyword", keyWord);
        intent.putExtras(b);
        startActivity(intent);

    }


}
