package vadeworks.paperdroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vadeworks.paperdroid.Vertical_News.Vertical_News;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    Elements vijayakarnataka_headlines_elem;
    Document vijayakarnataka_doc;
    String vijayakarnataka_url;


    ArrayList<String> vijayakarnataka_href = new ArrayList<String>();
    ArrayList<String> vijayakarnataka_headlines = new ArrayList<String>();



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
        listview_init();

        //For VijayaKarnataka Main Headlines//
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Run", "run: Start Running");
                try {
                    vijayakarnataka_url="https://vijaykarnataka.indiatimes.com/";//this is a string
                    vijayakarnataka_doc = Jsoup.connect(vijayakarnataka_url).get();//this is of type Document
                    vijayakarnataka_headlines_elem = vijayakarnataka_doc.getElementsByClass("other_main_news1").select("ul").select("li").select("a");//this has the headline
                    //vijayakarnataka_headlines_elem is of type Elements


                    int i;
                    for(i=0;i<vijayakarnataka_headlines_elem.size();i++){

                        vijayakarnataka_href.add(vijayakarnataka_url+vijayakarnataka_headlines_elem.get(i).attr("href"));
                        Log.d("VijayaKarnataka", "vijayakarnataka Href " +vijayakarnataka_href.get(i));


                        vijayakarnataka_headlines.add(vijayakarnataka_headlines_elem.get(i).text());
                        Log.d("VijayaKarnataka", "Vijayakarnataka Headlines "+ vijayakarnataka_headlines.get(i));

                    }Log.d("Run", "run: End Running");


                    for(i=0;i<vijayakarnataka_headlines_elem.size();i++){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                CustomAdapter customAdapter = new CustomAdapter();
                                listView.setAdapter(customAdapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                                        Intent i = new Intent(MainActivity.this, ReadNews.class);
//                                        i.putExtra("all_headlines",vijayakarnataka_headlines);
//                                        i.putExtra("url", vijayakarnataka_href.get(position));
//                                        i.putExtra("headline",vijayakarnataka_headlines.get(position));
//                                        startActivity(i);

                                        Intent i = new Intent(MainActivity.this, Vertical_News.class);
                                        i.putExtra("all_headlines",vijayakarnataka_headlines);
                                        i.putExtra("url", vijayakarnataka_href.get(position));
                                        i.putExtra("headline",vijayakarnataka_headlines.get(position));
                                        startActivity(i);
                                    }
                                });
                            }
                        });

                    }

                } catch (IOException e) {

                }
            }
        }).start();
// VijayaKarnataka Main Headlines Ends Here





//
//        //for AsiaNet News Headlines
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final StringBuilder builder = new StringBuilder();
//                try {
//
//                    String asianet_url = "http://kannada.asianetnews.com/news";
//                    String asianet = "http://kannada.asianetnews.com";
//                    Document asianet_doc = Jsoup.connect(asianet_url).get();
//                    Elements asianet_headlines_elem = asianet_doc.getElementsByClass("cl-block hidden-xs").select("h3");
//
//                    Elements asianet_links_elem = asianet_doc.getElementsByClass("cluster_news_block");
//
//                    Elements asianet_image_url_elem = asianet_doc.getElementsByClass("cluster_news_block").select("img:lt(1)");
//                    //lt(n) --->elements whose sibling index is less than n
//
//
//
//                    int i;
//                    for(i=0;i<=27;i++){
//                        asianet_headlines[i] = asianet_headlines_elem.get(i).text();
//                        Log.d("asianet","asianet_headlines"+asianet_headlines[i]);
//
//
//                        asianet_href[i]=asianet_links_elem.get(i).attr("href");
//                        Log.d("asianet","asianet_href"+" "+asianet+asianet_href[i]);
//
//                        asianet_image_url[i]=asianet_image_url_elem.get(i).attr("data-original");
//                        Log.d("asianet","asianet_image_url"+" "+asianet_image_url[i]);
//
//
//                        Document asianet_article_url = Jsoup.connect(asianet+asianet_href[i]).get();
//                        Elements asianet_article_elem = asianet_article_url.getElementsByClass("article-wrap new-article-desc");
//                        asianet_article[i] = asianet_article_elem.toString();
//                        asianet_article[i]=Jsoup.parse(asianet_article[i]).text();
//                        Log.d("asianet","asianet_article"+" "+asianet_article[i]);
//
//                    }
//
//
//
//
//                } catch (IOException e) {
//                    builder.append("Error : ").append(e.getMessage()).append("\n");
//                }
//
//
//            }
//        }).start();



    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount(){
            return vijayakarnataka_headlines_elem.size();
        }

        @Override
        public Object getItem(int i){
            return vijayakarnataka_headlines_elem.get(i);
        }

        @Override
        public long getItemId(int i){
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup){

            view = getLayoutInflater().inflate(R.layout.custom_layout,null);
            TextView news = (TextView)view.findViewById(R.id.news);
            news.setText(vijayakarnataka_headlines.get(i));

            return view;
        }
    }





    public void listview_init()
    {
        listView = (ListView) findViewById(R.id.vk_news);
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
