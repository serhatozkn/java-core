package tr.com.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * @author serhat.ozkan
 */
public class TempFilesExample {

    /**
     * Note from stackoverflow:
     * One of the purposes of a temporary file is to ensure that you get a uniquely named file so that you don't
     * accidentally overwrite some other program's temporary file and no other program accidentally overwrites
     * your temporary file. Giving a temporary file a specific name defeats this protection.
     */
    public static void main(final String[] args) {
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);
        try {
            Path tempFilePath = Files.createTempFile(null, ".log");
            tempFilePath.toFile().deleteOnExit();
            System.out.println("Created temp file path: " + tempFilePath);
            Files.writeString(tempFilePath, "Program is initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
