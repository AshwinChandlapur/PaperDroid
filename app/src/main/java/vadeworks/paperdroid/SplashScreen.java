package vadeworks.paperdroid;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    LinearLayout l1;
    Button read;
    ImageView space;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        if (Build.VERSION.SDK_INT >= 21) {
            // Call some material design APIs here
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.statusbar)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar)); //status bar or the time bar at the top

        } else {
            Log.d("Lesser than 21 SDK","Lesser than 21 SDK");
            Log.d("Lesser than 21 SDK","Lesser than 21 SDK");
            // Implement this feature without material design
        }





        read = findViewById(R.id.read);
        l1 =  findViewById(R.id.l1);
        space = findViewById(R.id.space);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        read.setAnimation(downtoup);
        space.setAnimation(downtoup);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            }
        });




    }
}
