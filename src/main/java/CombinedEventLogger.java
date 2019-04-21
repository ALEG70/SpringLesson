import java.util.ArrayList;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(ArrayList<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for(EventLogger el : loggers){
            el.logEvent(event);
        }
    }
}
