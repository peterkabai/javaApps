import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Convert {

    public static void main(String[] args) {

        String xml = "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";
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


