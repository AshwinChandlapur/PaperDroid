package vadeworks.paperdroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.ECField;

public class ReadNews extends AppCompatActivity {
    public WebView webView;
    String website_url,headline,img_url,body_texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);

      website_url = getIntent().getStringExtra("url");
      headline = getIntent().getStringExtra("headline");


        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

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
                    Log.d("body is", body_texts);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView headline_text = (TextView)findViewById(R.id.headline);
                            TextView body_text = (TextView)findViewById(R.id.body);
                            headline_text.setText(headline);
                            body_text.setText(body_texts);

                            ImageView imageView = (ImageView)findViewById(R.id.image1);
                            Picasso.with(getApplicationContext()).load(img_url).into(imageView);
                        }
                    });





//                    Elements links = doc.getElementsByClass("other_main_news1").select("ul").select("li").select("a");



                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }


            }
        }).start();





//        webView = (WebView) findViewById(R.id.webview_askaway);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.setWebViewClient(new WebViewClient(){
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_LONG).show();
////                view.loadUrl("javascript:(function() { " +
////                        "document.getElementsByTagName(\"h1\")[0].style.display = \"none\"; })()");
//
//
//
//                // hide element by class name
//                view.loadUrl("javascript:(function() { " +
//                        "document.getElementsByClassName(\"contentarea\")[0].style.display=\"none\"; })()");
////                // hide element by id
////                view.loadUrl("javascript:(function() { " +
////                        "document.getElementById('fixedMenu').style.display='none';})()"+
////                        "document.getElementById('fixedMenu').style.display='none';})()");
//
//                Toast.makeText(getApplicationContext(),"Login Only",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        webView.loadUrl(url);


    }

}
