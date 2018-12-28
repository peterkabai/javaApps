import java.awt.datatransfer.UnsupportedFlavorException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.File;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

class MyJButton extends JButton {
    MyJButton(String label) {
        new JButton(label);
    }
    MyJButton() {
        new JButton();
    }
    void useImage(String url) {
        try {
            Image img = ImageIO.read(new File(url));
            this.setIcon(new ImageIcon(img));
        }
        catch (Exception e) {
            System.out.println("ERROR: image file not found");
        }
    }
}

public class Frame {

    public static MyJButton img = new MyJButton();

    private static void pickAFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.showOpenDialog(null);
        File selectedFile = jfc.getSelectedFile();
        FileTransferHandler.fileName = selectedFile.getAbsolutePath();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("XML ⇨ JSON");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        Container contentPane = frame.getContentPane();

        JButton enter = new JButton("Submit File");
        enter.setPreferredSize(new Dimension(100, 30));
        contentPane.add(enter);
        enter.addActionListener(ae -> System.out.println(FileTransferHandler.fileName));

        img.setPreferredSize(new Dimension(200, 200));
        img.setTransferHandler(new FileTransferHandler());
        String imagePath = FileHandling.getResourcesDir() + "images/drop.png";
        img.useImage(imagePath);
        img.setBorder(BorderFactory.createEmptyBorder());
        img.setTransferHandler(new FileTransferHandler());
        img.addActionListener(ae -> pickAFile());
        contentPane.add(img);

        frame.setSize(550, 550);
        frame.setVisible(true);
    }
}

class FileTransferHandler extends TransferHandler {
    public static String fileName = "";

    public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
        for (DataFlavor flavor : arg1) {
            if (flavor.equals(DataFlavor.javaFileListFlavor) || flavor.equals(DataFlavor.stringFlavor)) {
                return true;
            }
        }
        return false;
    }

    public boolean importData(JComponent comp, Transferable trans) {
        DataFlavor[] flavors = trans.getTransferDataFlavors();
        for (DataFlavor flavor : flavors) {
            try {
                if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                    List lists = (List) trans.getTransferData(DataFlavor.javaFileListFlavor);
                    for (Object list : lists) {
                        File file = (File) list;
                        fileName = file.getCanonicalPath();
                    }
                    return true;
                } else if (flavor.equals(DataFlavor.stringFlavor)) {
                    String fileOrURL = (String) trans.getTransferData(flavor);
                    try {
                        new URL(fileOrURL);
                        return true;
                    } catch (MalformedURLException ex) {
                        return false;
                    }
                }
            } catch (IOException ex) {
                System.err.println("IOError getting data: " + ex);
            } catch (UnsupportedFlavorException e) {
                System.err.println("Unsupported Flavor: " + e);
            }
        }
        Toolkit.getDefaultToolkit().beep();
        return false;
    }
}
