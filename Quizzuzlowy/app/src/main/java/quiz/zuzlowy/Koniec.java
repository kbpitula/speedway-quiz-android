package quiz.zuzlowy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;

import static quiz.zuzlowy.R.id.adViewKoniec;
import static quiz.zuzlowy.R.id.adViewMenu;

public class Koniec extends Task {
    private TextView nick;
    private TextView punkty;
    private ImageButton menu;
    private ImageButton again;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koniec);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(adViewKoniec);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        nick = (TextView) findViewById(R.id.Nick);
        punkty = (TextView) findViewById(R.id.KoniecPKT);
        Data aa = new Data();
        nick.setText("Twój nick: " + aa.getNick());
        punkty.setText("Punkty: " + aa.getWynik());
        aa.setPosition(aa.getNick(), aa.getWynik());

        menu=(ImageButton) findViewById(R.id.menu);
        again=(ImageButton) findViewById(R.id.again);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View k)
            {
                openActivity(MainActivity.class);
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View k)
            {
                openActivity(NowaGra.class);
            }
        });
    }
}
