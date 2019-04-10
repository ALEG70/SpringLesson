import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static  void main(String [] args) throws BeansException {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean ("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for user 1");

        ctx.close();
        //app.client = new Client("1", "John Smith");
        //app.eventLogger = new ConsoleEventLogger();

        //app.logEvent("Some event for user 1");
        //app.logEvent("Some event for user 2");
    }

    private void logEvent(Event event, String msg){
        String message = msg.replaceAll(client.getid(), client.getfullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
