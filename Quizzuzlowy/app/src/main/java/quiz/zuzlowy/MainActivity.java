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

import static quiz.zuzlowy.R.id.adViewMenu;

public class MainActivity extends Task {

    private ImageButton button;
    private ImageButton buttonranking;
    private ImageButton buttonwyjscie;
    private TextView tekst;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(adViewMenu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        button=(ImageButton) findViewById(R.id.nowaGra);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(NowaGra.class);
            }
        });

        buttonranking=(ImageButton) findViewById(R.id.ranking);
        buttonranking.setOnClickListener(new View.OnClickListener(){
            public void onClick(View d){
                openActivity(Ranking.class);
            }
        });

        buttonwyjscie=(ImageButton) findViewById(R.id.wyjscie);
        buttonwyjscie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View f) {
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }
}