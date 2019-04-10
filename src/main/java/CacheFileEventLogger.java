import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int chacheSize) {
        super(filename);
        this.cacheSize = chacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);

        if (cache.size() == cacheSize){
            writeEventFromCache();
            cache.clear();
        }
    }

    public void destroy(){
        if (!cache.isEmpty()) writeEventFromCache();
    }

    private void writeEventFromCache() {
        cache.stream().forEach(super::logEvent);
    }
}
