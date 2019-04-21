import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private Map<EventType,EventLogger> loggers;
    private EventLogger defaultLogger;

    public App(Client client,EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static  void main(String [] args) throws BeansException {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean ("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent("Some event for user 1", EventType.ERROR, event);

        ctx.close();

    }

    private void logEvent(String msg, EventType type, Event event){
        EventLogger logger = loggers.get(type);
        event.setMsg(msg);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
