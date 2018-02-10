package vadeworks.paperdroid.Vertical_News;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vadeworks.paperdroid.MainActivity;
import vadeworks.paperdroid.R;

/**
 * Created by rizvan on 12/13/16.
 */

public class VerticlePagerAdapter extends PagerAdapter {

    String mResources[] = {"Spider-Man is a fictional superhero appearing in American comic books published by Marvel Comics. The character was created by writer-editor Stan Lee and writer-artist Steve Ditko, and first appeared in the anthology comic book Amazing Fantasy #15 (Aug. 1962) in the Silver Age of Comic Books. Lee and Ditko conceived the character as an orphan being raised by his Aunt May and Uncle Ben, and as a teenager, having to deal with the normal struggles of adolescence in addition to those of a costumed crime-fighter. Spider-Man's creators gave him super strength and agility, the ability to cling to most surfaces, shoot spider-webs using wrist-mounted devices of his own invention, which he calls \"web-shooters\", and react to danger quickly with his \"spider-sense\", enabling him to combat his foes.",
    "Iron Man is a 2008 American superhero film based on the Marvel Comics character of the same name, produced by Marvel Studios and distributed by Paramount Pictures.1 It is the first film in the Marvel Cinematic Universe. The film was directed by Jon Favreau, with a screenplay by Mark Fergus & Hawk Ostby and Art Marcum & Matt Holloway. It stars Robert Downey Jr., Terrence Howard, Jeff Bridges, Shaun Toub and Gwyneth Paltrow. In Iron Man, Tony Stark, an industrialist and master engineer, builds a powered exoskeleton and becomes the technologically advanced superhero Iron Man.\n" +
            "\n"};

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> mvijayakarnataka_headlines_news;
    String mHeadline,mWebsite_url;


    ListView listView;

    public VerticlePagerAdapter(Context context, ArrayList<String> vijayakarnataka_headlines_news, String headline, String website_url) {
        mContext = context;
        mvijayakarnataka_headlines_news = vijayakarnataka_headlines_news;
        mHeadline = headline;
        mWebsite_url = website_url;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mvijayakarnataka_headlines_news.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.content_main_read_news, container, false);
//        listView = (ListView) itemView.findViewById(R.id.news_headlines);


        Log.d("Pager","pager"+mvijayakarnataka_headlines_news);

        TextView label = (TextView) itemView.findViewById(R.id.headline);


        int i;
//        for(i=0;i<mvijayakarnataka_headlines_news.size();i++){
//            CustomAdapter customAdapter = new CustomAdapter();
//            listView.setAdapter(customAdapter);
//        }


//        for(position=0;position<mvijayakarnataka_headlines_news.size();position++)
//            {
//                if(position == position)
//                {
//                    label.setText(mvijayakarnataka_headlines_news.get(position));
//                    Log.d("Check","check"+position);
//                    Log.d("Check","check"+mvijayakarnataka_headlines_news.get(0));
//                    Log.d("Check","check"+mvijayakarnataka_headlines_news.get(1));
//                    Log.d("Check","check"+mvijayakarnataka_headlines_news.get(2));
//                }
//
//            }




        if(position == 0){
            label.setText(mHeadline);
        }else if (position ==1){
            label.setText(mvijayakarnataka_headlines_news.get(1));
        }else if(position==2)
        {label.setText(mvijayakarnataka_headlines_news.get(2));}
        else if(position==3){
            label.setText(mvijayakarnataka_headlines_news.get(3));
        }else{
            label.setText(mvijayakarnataka_headlines_news.get(4));
        }


        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        if (position % 2 == 0) {
//            label.setText(mResources[0]);
            imageView.setImageResource(R.drawable.background);
        }
        else{
//            label.setText(mResources[1]);
            imageView.setImageResource(R.drawable.spaceullustration);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

//    class CustomAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount(){
//            return mvijayakarnataka_headlines_news.size();
//        }
//
//        @Override
//        public Object getItem(int i){
//            return mvijayakarnataka_headlines_news.get(i);
//        }
//
//        @Override
//        public long getItemId(int i){
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup){
//
//            view =LayoutInflater.from(mContext).inflate(R.layout.custom_layout,null);
//            TextView news = (TextView)view.findViewById(R.id.news);
//
//            for(i=0;i<mvijayakarnataka_headlines_news.size();i++)
//            {
////                label.setText(mvijayakarnataka_headlines_news.get(i));
//                news.setText(mvijayakarnataka_headlines_news.get(i));
//                Log.d("Check","check"+i);
//                Log.d("Check","check"+mvijayakarnataka_headlines_news.get(0));
//                Log.d("Check","check"+mvijayakarnataka_headlines_news.get(1));
//                Log.d("Check","check"+mvijayakarnataka_headlines_news.get(2));
//            }
//
//
//            return view;
//        }
//    }
}
