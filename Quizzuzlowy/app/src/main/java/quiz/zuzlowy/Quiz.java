package quiz.zuzlowy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static quiz.zuzlowy.R.id.adViewMenu;
import static quiz.zuzlowy.R.id.adViewQuiz;
import static quiz.zuzlowy.R.id.adViewRanking;


public class Quiz extends Task {
    private List<Pytanie> sample= new ArrayList<>();
    private TextView pytanie;
    private TextView odpowiedzA;
    private TextView odpowiedzB;
    private TextView odpowiedzC;
    private TextView punkty;
    private TextView zycia;
    private int pkt;
    private int life;
    private String correct;
    ImageButton brawoOdpA;
    ImageButton bladOdpA;
    ImageButton brawoOdpB;
    ImageButton bladOdpB;
    ImageButton brawoOdpC;
    ImageButton bladOdpC;
    ImageButton pyt;
    private boolean blocker;
    private int liczbaPytan = 51;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(adViewQuiz);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8888258329683719/2607651778");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        pyt = (ImageButton) findViewById(R.id.nextPyt);
        pytanie = (TextView) findViewById(R.id.pytania);
        odpowiedzA = (TextView) findViewById(R.id.textOdpA);
        odpowiedzB = (TextView) findViewById(R.id.textOdpB);
        odpowiedzC = (TextView) findViewById(R.id.textOdpC);
        punkty = (TextView) findViewById(R.id.punkty);
        zycia = (TextView) findViewById(R.id.zycie);
        odczytajPytanie();
        int idA = (int) (liczbaPytan*Math.random());
        setNowePytanie(idA);
        blocker = true;
        pkt = 0;
        life = 3;

        brawoOdpA = (ImageButton) findViewById(R.id.brawoOdpA);
        bladOdpA = (ImageButton) findViewById(R.id.bladOdpA);
        brawoOdpB = (ImageButton) findViewById(R.id.brawoOdpB);
        bladOdpB = (ImageButton) findViewById(R.id.bladOdpB);
        brawoOdpC = (ImageButton) findViewById(R.id.brawoOdpC);
        bladOdpC = (ImageButton) findViewById(R.id.bladOdpC);
        final ImageButton odpa = (ImageButton) findViewById(R.id.odpa);
        odpa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int b = (int) (liczbaPytan*Math.random());
//                setNowePytanie(b);
                if(correct.equals("a") && blocker) {
                    pkt++;
                    punkty.setText("Punkty: " + pkt);
                    brawoOdpA.setVisibility(View.VISIBLE);
                    blocker = false;
                    pyt.setVisibility(View.VISIBLE);
                } else if(blocker) {
                    bladOdpA.setVisibility(View.VISIBLE);
                    blocker = false;
                    life--;
                    zycia.setText("Zycia: " + life);
                    if(life==0) {
                        Data a = new Data();
                        a.setWynik(pkt);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(LoadAdError adError) {
                                    // Code to be executed when an ad request fails.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClicked() {
                                    // Code to be executed when the user clicks on an ad.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdLeftApplication() {
                                    // Code to be executed when the user has left the app.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    openActivity(Koniec.class);
                                    finish();
                                }
                            });
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                            openActivity(Koniec.class);
                            finish();
                        }
                    }
                    pyt.setVisibility(View.VISIBLE);
                }
            }
        });

        final ImageButton odpb = (ImageButton) findViewById(R.id.odpb);
        odpb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int b = (int) (liczbaPytan*Math.random());
//                setNowePytanie(b);
                if(correct.equals("b") && blocker) {
                    pkt++;
                    punkty.setText("Punkty: " + pkt);
                    brawoOdpB.setVisibility(View.VISIBLE);
                    blocker = false;
                    pyt.setVisibility(View.VISIBLE);
                } else if(blocker){
                    bladOdpB.setVisibility(View.VISIBLE);
                    blocker = false;
                    life--;
                    zycia.setText("Zycia: " + life);
                    if(life==0) {
                        Data a = new Data();
                        a.setWynik(pkt);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(LoadAdError adError) {
                                    // Code to be executed when an ad request fails.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClicked() {
                                    // Code to be executed when the user clicks on an ad.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdLeftApplication() {
                                    // Code to be executed when the user has left the app.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    openActivity(Koniec.class);
                                    finish();
                                }
                            });
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                            openActivity(Koniec.class);
                            finish();
                        }
                    }
                    pyt.setVisibility(View.VISIBLE);
                }
            }
        });

        final ImageButton odpc = (ImageButton) findViewById(R.id.odpc);
        odpc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int b = (int) (liczbaPytan*Math.random());
//                setNowePytanie(b);
                if(correct.equals("c") && blocker) {
                    pkt++;
                    punkty.setText("Punkty: " + pkt);
                    brawoOdpC.setVisibility(View.VISIBLE);
                    blocker = false;
                    pyt.setVisibility(View.VISIBLE);
                } else if(blocker){
                    bladOdpC.setVisibility(View.VISIBLE);
                    blocker = false;
                    life--;
                    zycia.setText("Zycia: " + life);
                    if(life==0) {
                        Data a = new Data();
                        a.setWynik(pkt);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(LoadAdError adError) {
                                    // Code to be executed when an ad request fails.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClicked() {
                                    // Code to be executed when the user clicks on an ad.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdLeftApplication() {
                                    // Code to be executed when the user has left the app.
                                    openActivity(Koniec.class);
                                    finish();
                                }

                                @Override
                                public void onAdClosed() {
                                    // Code to be executed when the interstitial ad is closed.
                                    openActivity(Koniec.class);
                                    finish();
                                }
                            });
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                            openActivity(Koniec.class);
                            finish();
                        }
                    }
                    pyt.setVisibility(View.VISIBLE);
                }
            }
        });

        pyt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                blocker = true;
                pyt.setVisibility(View.INVISIBLE);
                brawoOdpC.setVisibility(View.INVISIBLE);
                brawoOdpB.setVisibility(View.INVISIBLE);
                brawoOdpA.setVisibility(View.INVISIBLE);
                bladOdpA.setVisibility(View.INVISIBLE);
                bladOdpB.setVisibility(View.INVISIBLE);
                bladOdpC.setVisibility(View.INVISIBLE);
                int b = (int) (liczbaPytan*Math.random());
                setNowePytanie(b);
            }
        });
    }

    private void setNowePytanie(int id) {
        String question = sample.get(id).getPytanie();
        if (question.length() > 150 ) {
            pytanie.setTextSize(18);
        } else {
            pytanie.setTextSize(20);
        }
        pytanie.setText(question);

        String a = sample.get(id).getOdpa();
        String b = sample.get(id).getOpdb();
        String c = sample.get(id).getOpdc();
        if(a.length() < 80) {
            odpowiedzA.setTextSize(22);
        } else if(a.length() < 180){
            odpowiedzA.setTextSize(17);
        } else {
            odpowiedzA.setTextSize(14);
        }

        if(b.length() < 80) {
            odpowiedzB.setTextSize(22);
        } else if(b.length() < 180){
            odpowiedzB.setTextSize(17);
        } else {
            odpowiedzB.setTextSize(14);
        }

        if(c.length() < 80) {
            odpowiedzC.setTextSize(22);
        } else if(c.length() < 180){
            odpowiedzC.setTextSize(17);
        } else {
            odpowiedzC.setTextSize(14);
        }
        odpowiedzA.setText(a);
        odpowiedzB.setText(b);
        odpowiedzC.setText(c);
        correct = sample.get(id).getAnswer();
    }
    private void odczytajPytanie() {
        InputStream id = getResources().openRawResource(R.raw.data3);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(id, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");

                // Read dat
                Pytanie samp = new Pytanie();
                samp.setId(tokens[0]);
                samp.setPytanie(tokens[1]);
                samp.setOdpa(tokens[2]);
                samp.setOpdb(tokens[3]);
                samp.setOpdc(tokens[4]);
                samp.setAnswer(tokens[5]);
                sample.add(samp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
