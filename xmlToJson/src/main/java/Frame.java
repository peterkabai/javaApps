import java.io.File;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Frame {

    private static MyJButton img = new MyJButton();

    private static void pickAFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.showOpenDialog(null);
        File selectedFile = jfc.getSelectedFile();
        FileTransferHandler.fileName = selectedFile.getAbsolutePath();
    }

    public static void main(String[] args) {

        // create the frame and panel
        JFrame frame = new JFrame("XML ⇨ JSON");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        Container contentPane = frame.getContentPane();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        // drop area for the file
        constraints.gridx = 0;
        constraints.gridy = 0;
        img.setPreferredSize(new Dimension(200, 200));
        img.setTransferHandler(new FileTransferHandler());
        String imagePath = FileHandling.getResourcesDir() + "images/drop.png";
        img.useImage(imagePath);
        img.setBorder(BorderFactory.createEmptyBorder());
        img.setTransferHandler(new FileTransferHandler());
        img.addActionListener(ae -> pickAFile());
        contentPane.add(img, constraints);

        // submit button
        constraints.gridy = 1;
        JButton enter = new JButton("Submit File");
        enter.setPreferredSize(new Dimension(100, 30));
        contentPane.add(enter, constraints);
        enter.addActionListener(ae -> System.out.println(FileTransferHandler.fileName));

        // set the frame parameters
        frame.setSize(260, 270);
        frame.setVisible(true);
    }
}