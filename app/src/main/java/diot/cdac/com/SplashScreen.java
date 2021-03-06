package diot.cdac.com;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Created by Atul Upadhye
 **/
public class SplashScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN_TIMEOUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), ParkingActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
