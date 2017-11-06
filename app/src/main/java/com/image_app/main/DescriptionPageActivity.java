package com.image_app.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;




public class DescriptionPageActivity extends Activity {

    String t=null;
    String l=null;
    String d=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.description);


        Bundle b = getIntent().getExtras();
        t= (String) b.get("title");
        l= (String) b.getString("link");
        d= (String) b.getString("desc");



        //TextView title = (TextView)findViewById(R.id.desc_page_heading);
        //title.setText((String)b.get("title"));

        /*TextView link = (TextView)findViewById(R.id.desc_page_link);
        link.setText((String) b.getString("link"));

        TextView snippet = (TextView)findViewById(R.id.desc_page_desc);
        snippet.setText((String) b.getString("desc"));
*/

    }



}
