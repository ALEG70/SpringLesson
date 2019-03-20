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
        //App app = new App();

        //app.client = new Client("1", "John Smith");
        //app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    private void logEvent(String msg){
        String message = msg.replaceAll(client.getid(), client.getfullName());
        eventLogger.logEvent(message);
    }
}
