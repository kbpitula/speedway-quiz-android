package quiz.zuzlowy;

public class Pytanie {
    private String id;
    private String pytanie;
    private String odpa;
    private String opdb;
    private String opdc;
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public String getOdpa() {
        return odpa;
    }

    public void setOdpa(String odpa) {
        this.odpa = odpa;
    }

    public String getOpdb() {
        return opdb;
    }

    public void setOpdb(String opdb) {
        this.opdb = opdb;
    }

    public String getOpdc() {
        return opdc;
    }

    public void setOpdc(String opdc) {
        this.opdc = opdc;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
