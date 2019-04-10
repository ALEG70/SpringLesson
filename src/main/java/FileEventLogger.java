import java.io.IOException;
import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    private void init() throws IOException {
        this.file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e){
                throw new IllegalArgumentException("Can't create file!", e);
            }

        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
