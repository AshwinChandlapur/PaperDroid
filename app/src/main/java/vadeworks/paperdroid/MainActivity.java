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

    String[] vijayakarnataka_others_href = new String [12];
    String[] vijayakarnataka_others_headlines = new String [12];



    String[] asianet_headlines = new String[12];
    String[] asianet_pictures = new String[12];


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
                    String website_url="https://vijaykarnataka.indiatimes.com/";
                    Document doc = Jsoup.connect(website_url).get();
                    String title = doc.title();
                    Elements links = doc.getElementsByClass("other_main_news1").select("ul").select("li").select("a");


                    int i;
                    for(i=0;i<=11;i++){
                        vijayakarnataka_href[i]=links.get(i).attr("href");
                        vijayakarnataka_href[i]=website_url+vijayakarnataka_href[i];
                        Log.d("VijayaKar HREF values", vijayakarnataka_href[i]);
                    }


                    for(i=0;i<=11;i++){
                        vijayakarnataka_headlines[i] = links.get(i).text();
                        Log.d("VijayKar Headlines are", vijayakarnataka_headlines[i]);
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
