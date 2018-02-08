package vadeworks.paperdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    String[] vijayakarnataka_href = new String[12];
    String[] vijayakarnataka_headlines = new String[12];
    String[] vijayakarnataka_article = new String[12];
    String[] vijayakarnataka_image_url= new String[12];

    String[] vijayakarnataka_others_href = new String [12];
    String[] vijayakarnataka_others_headlines = new String [12];



    String[] asianet_headlines = new String[28];
    String[] asianet_image_url= new String[28];
    String[] asianet_href = new String[28];
    String[] asianet_article = new String[28];


    TextView []t=new TextView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        textviews_init();




        //For VijayaKarnataka Main Headlines//
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                try {
                    Log.d("Run", "run: Start Running");
                    String vijayakarnataka_url="https://vijaykarnataka.indiatimes.com/";
                    Document vijayakarnataka_doc = Jsoup.connect(vijayakarnataka_url).get();
                    String title = vijayakarnataka_doc.title();

                    Elements vijayakarnataka_headlines_elem = vijayakarnataka_doc.getElementsByClass("other_main_news1").select("ul").select("li").select("a");//this has the headline


                    int i;
                    for(i=0;i<=11;i++){


                        vijayakarnataka_href[i]=vijayakarnataka_headlines_elem.get(i).attr("href");//this has the href
                        vijayakarnataka_href[i]=vijayakarnataka_url+vijayakarnataka_href[i];
                        Log.d("VijayaKarnataka", "vijayakarnataka HREF " + vijayakarnataka_href[i]);


                        vijayakarnataka_headlines[i] = vijayakarnataka_headlines_elem.get(i).text();
                        Log.d("VijayaKarnataka", "Vijayakarnataka Headlines "+vijayakarnataka_headlines[i]);



                        Document vijayakarnataka_article_url_doc = Jsoup.connect(vijayakarnataka_href[i]).get();
                        Elements vijayakarnataka_article_elem = vijayakarnataka_article_url_doc.getElementsByTag("arttextxml");
                        vijayakarnataka_article[i] = vijayakarnataka_article_elem.toString();
                        vijayakarnataka_article[i]=Jsoup.parse(vijayakarnataka_article[i]).text();
                        Log.d("Vijayakarnataka","Vijayakarnataka Articles "+vijayakarnataka_article[i]);


                        Elements vijayakarnataka_image_url_elem = vijayakarnataka_article_url_doc.getElementsByClass("thumbImage").select("img");
                        try{
                            vijayakarnataka_image_url[i]= vijayakarnataka_image_url_elem.get(0).attr("src");
                            Log.d("Vijayakarnataka","Vijayakarnataka Image URL "+vijayakarnataka_url+vijayakarnataka_image_url[i]+"\n\n");
                        }catch (Exception e){

                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Unable to fetch",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        }











                    }Log.d("Run", "run: End Running");


                    for(i=0;i<=11;i++){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                int i;
                                for(i=0;i<=11;i++){
                                    final String url = vijayakarnataka_href[i];
                                    final String headline = vijayakarnataka_headlines[i];
                                    t[i].setText(vijayakarnataka_headlines[i]);
                                    t[i].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i = new Intent(getApplicationContext(), ReadNews.class);
                                            i.putExtra("url", url);
                                            i.putExtra("headline",headline);
                                            startActivity(i);
                                        }
                                    });
                                }

                            }
                        });

                    }

                    builder.append(title).append("\n");

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }


            }
        }).start();
// VijayaKarnataka Main Headlines Ends Here






        //for AsiaNet News Headlines
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                try {

                    String asianet_url = "http://kannada.asianetnews.com/news";
                    String asianet = "http://kannada.asianetnews.com";
                    Document asianet_doc = Jsoup.connect(asianet_url).get();
                    Elements asianet_headlines_elem = asianet_doc.getElementsByClass("cl-block hidden-xs").select("h3");

                    Elements asianet_links_elem = asianet_doc.getElementsByClass("cluster_news_block");

                    Elements asianet_image_url_elem = asianet_doc.getElementsByClass("cluster_news_block").select("img:lt(1)");
                    //lt(n) --->elements whose sibling index is less than n



                    int i;
                    for(i=0;i<=27;i++){
                        asianet_headlines[i] = asianet_headlines_elem.get(i).text();
                        Log.d("asianet","asianet_headlines"+asianet_headlines[i]);


                        asianet_href[i]=asianet_links_elem.get(i).attr("href");
                        Log.d("asianet","asianet_href"+" "+asianet+asianet_href[i]);

                        asianet_image_url[i]=asianet_image_url_elem.get(i).attr("data-original");
                        Log.d("asianet","asianet_image_url"+" "+asianet_image_url[i]);


                        Document asianet_article_url = Jsoup.connect(asianet+asianet_href[i]).get();
                        Elements asianet_article_elem = asianet_article_url.getElementsByClass("article-wrap new-article-desc");
                        asianet_article[i] = asianet_article_elem.toString();
                        asianet_article[i]=Jsoup.parse(asianet_article[i]).text();
                        Log.d("asianet","asianet_article"+" "+asianet_article[i]);
                    }




                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }


            }
        }).start();
//AsiaNet News Headlines Ends Here











    }

    public void textviews_init()
    {
        t[0] = (TextView)findViewById(R.id.t1);
        t[1] = (TextView)findViewById(R.id.t2);
        t[2] = (TextView)findViewById(R.id.t3);
        t[3] = (TextView)findViewById(R.id.t4);
        t[4] = (TextView)findViewById(R.id.t5);
        t[5] = (TextView)findViewById(R.id.t6);
        t[6] = (TextView)findViewById(R.id.t7);
        t[7] = (TextView)findViewById(R.id.t8);
        t[8] = (TextView)findViewById(R.id.t9);
        t[9] = (TextView)findViewById(R.id.t10);
        t[10]= (TextView)findViewById(R.id.t11);
        t[11] = (TextView)findViewById(R.id.t12);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
