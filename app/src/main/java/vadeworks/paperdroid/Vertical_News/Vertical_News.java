package vadeworks.paperdroid.Vertical_News;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import vadeworks.paperdroid.R;

public class Vertical_News extends AppCompatActivity {

    String website_url,headline,img_url,body_texts;

    ArrayList<String> vijayakarnataka_headlines_news = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical__news);

        website_url = getIntent().getStringExtra("url");
        headline = getIntent().getStringExtra("headline");
        vijayakarnataka_headlines_news = getIntent().getStringArrayListExtra("all_headlines") ;
        Log.d("Read News Headlines", "onCreate: "+vijayakarnataka_headlines_news);

        initSwipePager();
    }



    private void initSwipePager(){
        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.vPager);


        verticalViewPager.setAdapter(new VerticlePagerAdapter(this, vijayakarnataka_headlines_news,headline,website_url) );
    }

}
