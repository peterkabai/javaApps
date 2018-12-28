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

        String xml = "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";


        String filename = "/Users/peter/Desktop/GitHub/javaApps/xmlToJson/src/main/resources/test.txt";
        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        filename = "test.txt";



        //URL url = Convert.class.getClassLoader().getResource("resources/test.txt");
        //File file = new File(url);

        String text = new String(Files.readAllBytes(Paths.get(resources + filename)));
        System.out.println(text);


        int indents = 4;

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            String jsonPrettyPrintString = xmlJSONObj.toString(indents);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}


