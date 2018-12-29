import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.io.IOException;
import java.io.PrintWriter;

public class Convert {

    public static void fileChosen(String filePath) {

        // checks to see if the extension matches
        if (FileHandling.hasExtension(filePath, "xml")) {
            System.out.println("The file is an XML file");
            String fileLocation = FileHandling.getLocation(filePath);
            String inputFileName = FileHandling.getFileName(filePath);
            System.out.println(fileLocation);
            System.out.println(inputFileName);
        } else {
            System.out.println("The file is NOT an XML file");
        }
    }

    public static void main(String[] args) throws IOException {

        // pick the data and json settings
        int indents = 4;
        String inFile = FileHandling.getFileAsString("data.xml");
        String outFile = FileHandling.getResourcesDir() + "data.json";

        try {
            // convert to pretty json
            JSONObject obj = XML.toJSONObject(inFile);
            String json = obj.toString(indents);
            System.out.println(json);

            // save to json file
            PrintWriter writer = new PrintWriter(outFile, "UTF-8");
            writer.println(json);
            writer.close();

        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}


