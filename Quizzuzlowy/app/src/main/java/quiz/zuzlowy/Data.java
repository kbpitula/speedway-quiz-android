package quiz.zuzlowy;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static String nick;
    private static int wynik;
    private static List<Rank> rankData = new ArrayList<>();

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getWynik() {
        return wynik;
    }

    public void setWynik(int wynik) {
        this.wynik = wynik;
    }

    public void setPosition(String name, int wynik) {
        Rank a = new Rank();
        a.setWynik(wynik);
        a.setName(name);
        rankData.add(a);
    }

    public List<Rank> getRanking() {
        return rankData;
    }

}