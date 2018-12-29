import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandling {

    // gets the text from the input file
    public static String getResourceAsString(String inFile) {
        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        try {
            return new String(Files.readAllBytes(Paths.get(resources + inFile)));
        } catch (IOException e) {
            e.printStackTrace();
            return "An error has occurred";
        }
    }

    public static String getFileAsString(String inFile) {
        try {
            return new String(Files.readAllBytes(Paths.get(inFile)));
        } catch (IOException e) {
            e.printStackTrace();
            return "An error has occurred";
        }
    }

    public static String getResourcesDir() {
        return System.getProperty("user.dir") + "/src/main/resources/";
    }

    public static boolean hasExtension(String path, String extToMatch) {
        String fileArray[] = path.split("\\.");
        String ext = fileArray[fileArray.length-1];
        return ext.equals(extToMatch);
    }

    public static String getLocation(String filePath) {
        String fileArray[] = filePath.split("/");
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < (fileArray.length - 1); i++) {
            path.append(fileArray[i]).append("/");
        }
        return path.toString();
    }

    public static String getFileName(String filePath) {
        String fileArray[] = filePath.split("/");
        String fileNameWithExtension = fileArray[fileArray.length-1];
        return fileNameWithExtension.split("\\.")[0];
    }
}
