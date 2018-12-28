import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Convert {

    public static void main(String[] args) throws IOException {

        // pick the data and json settings
        String inFile = "data.xml";
        String outFile = "data.json";
        int indents = 4;

        // gets the text from the input file
        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        String text = new String(Files.readAllBytes(Paths.get(resources + inFile)));

        try {
            // convert to pretty json
            JSONObject obj = XML.toJSONObject(text);
            String json = obj.toString(indents);
            System.out.println(json);

            // save to json file
            PrintWriter writer = new PrintWriter(resources + outFile, "UTF-8");
            writer.println(json);
            writer.close();

        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}


