import java.text.DateFormat;
import java.util.Date;

public class Event {

    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        id = (int) (Math.random()*100);

        this.date = date;
        this.df = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString(){
        return ("Event :"+id +"; "+ msg +"; "+ df.format(date));
    }
}
