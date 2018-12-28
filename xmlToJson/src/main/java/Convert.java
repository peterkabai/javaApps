import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Convert {


    public static void main(String[] args) throws IOException {

        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        String filename = "data.xml";
        String text = new String(Files.readAllBytes(Paths.get(resources + filename)));

        int indents = 4;

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(text);
            String jsonPrettyPrintString = xmlJSONObj.toString(indents);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}


