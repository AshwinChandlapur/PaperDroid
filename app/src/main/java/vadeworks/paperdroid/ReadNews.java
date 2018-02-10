package vadeworks.paperdroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import vadeworks.paperdroid.Vertical_News.VerticalViewPager;
import vadeworks.paperdroid.Vertical_News.VerticlePagerAdapter;

public class ReadNews extends AppCompatActivity {
    public WebView webView;
    String website_url,headline,img_url,body_texts;

    ArrayList<String> vijayakarnataka_headlines_news = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);

        android.support.v7.app.ActionBar AB = getSupportActionBar();
        AB.hide();

      website_url = getIntent().getStringExtra("url");
      headline = getIntent().getStringExtra("headline");
      vijayakarnataka_headlines_news = getIntent().getStringArrayListExtra("all_headlines") ;
        Log.d("Read News Headlines", "onCreate: "+vijayakarnataka_headlines_news);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Document doc = Jsoup.connect(website_url).get();
                    Elements image_url = doc.getElementsByClass("thumbImage").select("img");
                        try{
                            img_url= image_url.get(0).attr("src");
                            }catch (Exception e){

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "This is my Toast message!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                    img_url="https://vijaykarnataka.indiatimes.com"+img_url;

                    Elements body = doc.getElementsByTag("arttextxml");
                    body_texts = body.toString();
                    body_texts=Jsoup.parse(body_texts).text();
//                    body_texts=body_texts.replaceFirst("\\.","\n\n");
                    Log.d("body is", body_texts);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView headline_text = findViewById(R.id.headline);
                            TextView body_text = findViewById(R.id.body);
                            TextView full_news = findViewById(R.id.fullnews);
                            headline_text.setText(headline);
                            body_text.setText(body_texts);

                            full_news.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(website_url));
                                    startActivity(i);
                                }
                            });

                            ImageView imageView = findViewById(R.id.image1);
                            Picasso.with(getApplicationContext()).load(img_url).into(imageView);
                        }
                    });





//                    Elements links = doc.getElementsByClass("other_main_news1").select("ul").select("li").select("a");



                } catch (IOException e) {

                }


            }
        }).start();

    }


}
