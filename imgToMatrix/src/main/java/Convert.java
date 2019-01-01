import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Convert {

    // gets the resources directory
    private static String resources = System.getProperty("user.dir") + "/src/main/resources/";

    // prints 2D array from image
    private static void printForImage(String image) throws IOException {
        // load image
        BufferedImage img = ImageIO.read(new File(resources + "images/" + image));

        // get the filename
        String name = image.split("\\.")[0];

        //  starts building the output string
        StringBuilder output = new StringBuilder("int[] " + name + " = [\n");

        // loop for each row
        for (int i = 7; i >= 0; i--) {
            output.append("[");

            // loop for each item in row
            for (int j = 0; j <= 7; j++) {

                // gets the color of the pixel
                Color pixel = new Color(img.getRGB(j,i));

                // builds the string
                if (pixel.equals(Color.black)) {
                    output.append("1");
                } else {
                    output.append("0");
                }
                if (j!=7) {
                    output.append(",");
                }
            }
            output.append("]");
            if (i!=0) { output.append(",\n"); }
        }
        output.append("]");

        // prints the output
        System.out.println(output);
    }

    public static void main(String[] args) throws IOException {

        File folder = new File(resources + "images/");
        File[] listOfFiles = folder.listFiles();
        int fileCount = 0;

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() &&  file.getName().split("\\.")[1].equals("png")) {
                printForImage(file.getName());
                fileCount += 1;
                System.out.print("\n");
            }
        }
        System.out.println(fileCount + " images processed");
    }

}
