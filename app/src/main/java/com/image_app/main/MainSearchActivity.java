package com.image_app.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.ruhiya.awsomeimageapp.R;
import com.image_app.adapters.ImageAdapter;
import com.image_app.models.Url;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;


public class MainSearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.searchBar)
    public EditText searchText;

    @BindView(R.id.gridview)
    public GridView grid;

    private ImageAdapter imageAdapter;
    private String keyWord="";
    Handler handler = new Handler();
    List<String> imageUrls;
    List<Url> listUrls = new ArrayList<>();
    ImageLoaderTask imageLoaderTask;
    private MyClickHandler mKeyhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        imageUrls = new ArrayList<>();
        mKeyhandler=new MyClickHandler();
        imageLoaderTask = new ImageLoaderTask();
        imageAdapter = new ImageAdapter(this, imageUrls);
        grid.setAdapter(imageAdapter);
        grid.setOnItemClickListener(this);
        imageAdapter.notifyDataSetChanged();
    }

    protected void updateItemsInUI() {
        imageUrls.clear();
        for(Url url : listUrls){
            imageUrls.add(url.getUrl());
        }
        imageAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        keyWord = searchText.getText().toString();

        try {
            imageLoaderTask.cancel(true);
            handler.removeCallbacks(mKeyhandler);
        }catch (Exception e){}

        handler.postDelayed(mKeyhandler, 100);

            return false;
    }

    class MyClickHandler implements Runnable{
        @Override
        public void run() {
            if(imageLoaderTask != null  && (imageLoaderTask.getStatus() ==  AsyncTask.Status.RUNNING
                    || imageLoaderTask.getStatus() ==  AsyncTask.Status.PENDING )){

                try {        imageLoaderTask.cancel(true);
                }catch (Exception e){}

                try {
                    imageLoaderTask = new ImageLoaderTask();
                    imageLoaderTask.execute(keyWord);
                }catch (Exception e){}
            }
            else {
                try {
                    imageLoaderTask = new ImageLoaderTask();
                    imageLoaderTask.execute(keyWord);
                }catch (Exception e){}
                }
        }
    }

     class ImageLoaderTask extends AsyncTask<String, Void, Void> {

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateItemsInUI();
                }
            });

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String urlStr=  (String) view.getTag();
        Url imgObj=null;
        for(Url url : listUrls){
            if(url.getUrl().equals(urlStr)){
                imgObj = url;
            }
        }
        Intent intent = new Intent(MainSearchActivity.this,DescriptionPageActivity.class);
        Bundle b=new Bundle();
        b.putString("title", keyWord);
        b.putString("url", imgObj.get_largeUrl());
        b.putString("site", imgObj.getSite());
        intent.putExtras(b);
        startActivity(intent);

    }


}
