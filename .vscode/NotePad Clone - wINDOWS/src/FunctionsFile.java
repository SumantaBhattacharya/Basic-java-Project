//2

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionsFile {
    // creating an instance of Gui class
    Gui gui;
    String fileName;
    String fileAddress;

    // constructor
    public FunctionsFile(Gui gui) {// instance of Gui class
        this.gui = gui;

    }

    // fuctions to create a new file
    public void newFile() {
        gui.textArea.setText("Write Your text here");// erase the current text
        gui.window.setTitle("Untitled");// set the default name of the new file

    }

    // function to open a existing file
    public void newOpen() {
        // to display dialog file
        // FileDialog Deprecated:

        // Note that FileDialog is deprecated in newer Java versions. Consider using
        // JFileChooser for a more modern file dialog.
        // JFileChooser fD = new JFileChooser(gui.window,"Open",JFileChooser.LOAD);
        FileDialog fD = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fD.setVisible(true);
        // logic to Open any files
        if (fD.getFile() != null) {
            fileName = fD.getFile();
            fileAddress = fD.getDirectory();// return the location of the file directory
            System.out.println(fileName + fileAddress);
            gui.window.setTitle(fileName);
        }
        // display the content of the file
        // use buffer reader
        try {
            // you need to read the address of the file
            BufferedReader bR = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line = null;
            while ((line = bR.readLine()) != null) {// to read the previous line of the file
                gui.textArea.append(line + "\n");
            }
            bR.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // function to save
    public void newSave() {
        // Add the logic to save the file here
        // For simplicity, let's assume you want to save the current content
        // without prompting for a new file name.
        if (fileName != null && fileAddress != null) {
            try {
                FileWriter fW = new FileWriter(fileAddress + fileName);
                fW.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fW.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {// if (fileName == null)
            // Handle the case where the file name or address is not set
            newSave_as();
            System.out.println("Cannot save. File name or address is not set.");
        }
    }

    // function to save as
    public void newSave_as() {
        FileDialog fD = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fD.setVisible(true);
        // logic to Open any files
        if (fD.getFile() != null) {
            fileName = fD.getFile();
            fileAddress = fD.getDirectory();// return the location of the file directory
            gui.window.setTitle(fileName);
            System.out.println(fileName + fileAddress);
        }
        // display the content of the file
        // use buffer reader
        try {
            // you need to read the address of the file
            FileWriter fW = new FileWriter(fileAddress + fileName);
            fW.write(gui.textArea.getText());
            fW.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
// function to create exit operation
    public void newExit() {
        System.exit(0);
    }
}
