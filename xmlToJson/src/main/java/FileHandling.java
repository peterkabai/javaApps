import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandling {

    // gets the text from the input file
    public static String getFileAsString(String inFile) {
        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        try {
            return new String(Files.readAllBytes(Paths.get(resources + inFile)));
        } catch (IOException e) {
            e.printStackTrace();
            return "An error has occurred";
        }
    }

    public static String getResourcesDir() {
        return System.getProperty("user.dir") + "/src/main/resources/";
    }
}
