import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static  void main(String [] args){

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event evt = (Event) ctx.getBean("event");
        app.logEvent(evt, "First message");

        evt = (Event) ctx.getBean("event");
        app.logEvent(evt, "Second message");
        //app.client = new Client("1", "John Smith");
        //app.eventLogger = new ConsoleEventLogger();


    }

    private void logEvent(Event evt, String msg){
        //String message = msg.replaceAll(client.getid(), client.getfullName());
        evt.setMsg(msg);
        eventLogger.logEvent(evt);
    }
}
