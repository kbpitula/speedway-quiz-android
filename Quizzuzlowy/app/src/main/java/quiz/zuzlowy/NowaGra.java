package quiz.zuzlowy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static quiz.zuzlowy.R.id.adViewMenu;
import static quiz.zuzlowy.R.id.adViewNowaGra;

public class NowaGra extends Task {
    private ImageButton start;
    private EditText pole;
    private ImageButton wstecz;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowa_gra);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(adViewNowaGra);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        pole=(EditText) findViewById(R.id.poletekstowe);
        start=(ImageButton) findViewById(R.id.imagestart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View k)
            {
                String pobierznick=pole.getText().toString();
                if(pobierznick.length()>0){
                    Data d = new Data();
                    d.setNick(pobierznick);
                    openActivity(Quiz.class);
                }
            }
        });

        wstecz = (ImageButton) findViewById(R.id.wstecz1);
        wstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View k) {
                openActivity(MainActivity.class);
            }
        });

    }
}
