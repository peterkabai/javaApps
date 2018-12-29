import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

class FileTransferHandler extends TransferHandler {
    public static String fileName = "";

    public boolean canImport(JComponent arg0, DataFlavor[] argument) {
        for (DataFlavor flavor : argument) {
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
                    Convert.fileChosen(fileName);
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
