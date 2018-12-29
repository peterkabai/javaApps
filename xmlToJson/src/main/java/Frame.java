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
        if (selectedFile != null) {
            FileTransferHandler.fileName = selectedFile.getAbsolutePath();
            Convert.fileChosen(FileTransferHandler.fileName);
        } else {
            System.out.println("No file selected");
        }
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

        // set the frame parameters
        int frameWidth = 260;
        int frameHeight = 270;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frame.setBounds(
                (int) Math.round(width / 2 - frameWidth / 2),
                (int) Math.round(height / 2 - frameHeight / 2),
                frameWidth,
                frameHeight
        );
        frame.setResizable(false);
        frame.setVisible(true);
    }
}