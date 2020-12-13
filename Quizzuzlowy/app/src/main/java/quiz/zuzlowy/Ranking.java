package quiz.zuzlowy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static quiz.zuzlowy.R.id.adViewMenu;
import static quiz.zuzlowy.R.id.adViewRanking;

public class Ranking extends Task {
    private ImageButton wstecz;
    private TextView place1;
    private TextView place2;
    private TextView place3;
    private TextView place4;
    private TextView place5;
    private TextView place6;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(adViewRanking);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        place1 = (TextView) findViewById(R.id.place1);
        place2 = (TextView) findViewById(R.id.place2);
        place3 = (TextView) findViewById(R.id.place3);
        Data aa = new Data();
        List<Rank> rankList = aa.getRanking();

        Collections.sort(rankList, new Comparator() {
            public int compare(Object o1, Object o2) {
                Integer x11 = ((Rank) o1).getWynik();
                Integer x21 = ((Rank) o2).getWynik();
                return x21.compareTo(x11);
            }});
        if (rankList.size() == 0) {
            place1.setTextSize(16);
            place1.setText("\t\t\t\t\t\t\tRanking pusty\nMusisz zagrać by zdobyć punkty!!");
        }
        if(rankList.size() > 0 ) {
            place1.setTextSize(24);
            place1.setText("1. Miejsce: " + rankList.get(0).getName() + " PKT: " + rankList.get(0).getWynik());
        }
        if(rankList.size() > 1 ) {
            place2.setText("2. Miejsce: " + rankList.get(1).getName() + " PKT: " + rankList.get(1).getWynik());
        }
        if(rankList.size() > 2 ) {
            place3.setText("3. Miejsce: " + rankList.get(2).getName() + " Pkt: " + rankList.get(2).getWynik());
        }
        if(rankList.size() > 3 ) {
            place3.setText("4. Miejsce: " + rankList.get(3).getName() + " PKT: " + rankList.get(3).getWynik());
        }
        if(rankList.size() > 4 ) {
            place3.setText("5. Miejsce: " + rankList.get(4).getName() + " PKT: " + rankList.get(4).getWynik());
        }
        if(rankList.size() > 5 ) {
            place3.setText("6. Miejsce: " + rankList.get(5).getName() + " PKT: " + rankList.get(5).getWynik());
        }

        wstecz = (ImageButton) findViewById(R.id.wstecz1);
        wstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View k) {
                openActivity(MainActivity.class);
            }
        });
    }

    private static void sortuj(List<Rank> persons) {

        Collections.sort(persons, new Comparator() {

            public int compare(Object o1, Object o2) {

                Integer x11 = ((Rank) o1).getWynik();
                Integer x21 = ((Rank) o2).getWynik();
                return x11.compareTo(x21);
            }});
    }
}
