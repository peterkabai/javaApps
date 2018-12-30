import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Convert {

    public static void fileChosen(String filePath) {

        // checks to see if the extension matches
        if (FileHandling.hasExtension(filePath, "xml")) {

            // gets file name and location
            String fileLocation = FileHandling.getLocation(filePath);
            String inputFileName = FileHandling.getFileName(filePath);

            // change image color to green
            Frame.img.useImage(FileHandling.getResourcesDir() + "images/dropGreen.png");

            // convert and save to the same location
            convertXML(FileHandling.getFileAsString(filePath), fileLocation+inputFileName);

        } else {
            // change image color to red
            Frame.img.useImage(FileHandling.getResourcesDir() + "images/dropRed.png");
            JOptionPane.showMessageDialog(null, "Please choose an XML file!");
        }
    }

    public static void main(String[] args) {

        // pick the data and json settings
        String inFile = FileHandling.getResourceAsString("data.xml");
        String outFile = FileHandling.getResourcesDir() + "data";
        convertXML(inFile, outFile);

    }

    private static void convertXML(String xmlString, String outFile) {
        int indents = 4;

        try {
            // convert to pretty json
            JSONObject obj = XML.toJSONObject(xmlString);
            String json = obj.toString(indents);

            // save to json file
            PrintWriter writer = new PrintWriter(outFile + ".json", "UTF-8");
            writer.println(json);
            writer.close();

        } catch (JSONException je) {
            System.out.println(je.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


