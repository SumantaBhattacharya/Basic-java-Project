## Hello viewers 
### Here I post some of the projects
```java

import javax.swing.text.BadLocationException;
import javax.swing.text.View;
import javax.swing.undo.UndoManager;

import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// global variables
public class Gui implements ActionListener {// Interface
    // Creating an object for JFrame globally
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    // top menu bar
    JMenuBar menuBar;
    JMenu menuFile; // Added to define menuFile
    JMenu menuEdit;
    JMenu menuFormat;
    JMenu menuColor;
    JMenu menuView;
    // file menu items
    JMenuItem iNew, iOpen;
    JMenuItem iSave;
    JMenuItem iSaveAs;
    JMenuItem iExit;
    // format menu items
    JMenuItem iwrap;
    JMenuItem iFontArial, iFontCSMS, iFontTNR, iFontCalibri, iFontCourierNew, iFontimpact, iFontgeorgia, iFontverdana,
            iFontoldEnglishText;// Times New Roman
    JMenuItem iFontSize_8, iFontSize_12, iFontSize_16, iFontSize_20, iFontSize_24, iFontSize_28;

    JMenu menuFont, menuFontSize;

    FunctionsFile file = new FunctionsFile(this);
    FunctionFormat format = new FunctionFormat(this);
    FunctionColor color = new FunctionColor(this);
    FunctionEdit edit = new FunctionEdit(this);

    // color menu items
    JMenuItem iColor_1, iColor_2, iColor_3;
    // edit
    JMenuItem iUndo, iRedo;
    UndoManager un = new UndoManager();

    boolean wordWrapON = false;
    JLabel statusBar;

    // Constructor
    public Gui() {
        // JFrame calling all the methods
        createWindow();
        // JTextArea calling all the methods
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createColorMenu();
        createEditMenu();
        // Add the "View" functionality
        addViewFunctionality();

        // set default font and size
        format.selectedFont = "Arial";// the text need to be first in some font and size
        format.createFont(18);

        // Set custom icon
        // Set custom icon// Set custom icon
        ImageIcon image = new ImageIcon("C:\\Users\\Sumanta Bhattacharya\\OneDrive\\Documents\\Desktop\\.java\\n" + //
                "otepad.png");
        window.setIconImage(image.getImage());


    }
    // public void setFontSize(int size) {
    // Font currentFont = textArea.getFont();
    // textArea.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(),
    // size));
    // }

    // Define the create window method
    public void createWindow() {
        // JFrame - Swing interface/class-help in displaying the components in the
        // window
        window = new JFrame("Notepad Clone");// giving a name to the window
        // Set the window to be visible
        window.setVisible(true);
        // Set the default close operation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the window
        window.setSize(800, 600);// width and height
        // (Prevent frame from being resized)We set it false because we don't want it to
        // change the size of the frame
        // But we can move the frame through our screen
        // window.setResizable(false);

        // Center the window on the screen
        window.setLocationRelativeTo(null);
        // Remove window decorations
        // window.setUndecorated(true);

        // window.setLayout(true);
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        window.add(statusBar, BorderLayout.SOUTH);
    }

    public void createTextArea() {
        // Define the text area method, which helps us to write our text.
        // Add a JTextArea for writing
        textArea = new JTextArea();
        // undo and redo manager
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                un.addEdit(e.getEdit());
            }
        });
        
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStatus();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStatus();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStatus();
            }
        });
        window.add(scrollPane);
    }

    private void updateStatus() {
        int caretPosition = textArea.getCaretPosition();
        int lineNumber = 1;

        try {
            lineNumber = textArea.getLineOfOffset(caretPosition) + 1;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        String text = textArea.getText();
        int wordCount = countWords(text);

        String statusText = "Line: " + lineNumber + " | Position: " + caretPosition + " | Word Count: " + wordCount;
        statusBar.setText(statusText);
    }

    private int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // create menu bar which creates the menu for our gui
    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        // File menu
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        // Edit menu
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        // Format menu
        // menuFormat = new JMenu("Format");
        // menuBar.add(menuFormat);

        // view menu
        menuView = new JMenu("View");
        menuBar.add(menuView);

        // Color menu
        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
    }

    // create iItems
    public void createFileMenu() {
        iNew = new JMenuItem("New tab");
        menuFile.add(iNew);
        iNew.addActionListener(this);
        iNew.setActionCommand("New");

        iOpen = new JMenuItem("Open");
        menuFile.add(iOpen);
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");

        iSave = new JMenuItem("Save");
        menuFile.add(iSave);
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");

        iSaveAs = new JMenuItem("Save as");
        menuFile.add(iSaveAs);
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("Save as");

        iExit = new JMenuItem("Close tab");
        menuFile.add(iExit);
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");// action command
    }

    public void addViewFunctionality() {
        // Create menu items
        JMenuItem toggleFullScreenItem = new JMenuItem("Toggle");

        menuView.add(toggleFullScreenItem);

        // Create a submenu for Zoom
        menuFont = new JMenu("Zoom");

        JMenuItem increaseFontSizeItem = new JMenuItem("Zoom in");
        JMenuItem decreaseFontSizeItem = new JMenuItem("Zoom out");

        // Add Zoom In and Zoom Out items to the Zoom submenu
        menuFont.add(increaseFontSizeItem);
        menuFont.add(decreaseFontSizeItem);

        // Add the Zoom submenu to the View menu
        menuView.add(menuFont);

        // Add action listeners
        toggleFullScreenItem.addActionListener(e -> {
            window.setExtendedState(
                    window.getExtendedState() == JFrame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
        });

        increaseFontSizeItem.addActionListener(e -> {
            textArea.setFont(textArea.getFont().deriveFont((float) (textArea.getFont().getSize() + 4)));
        });

        decreaseFontSizeItem.addActionListener(e -> {
            textArea.setFont(textArea.getFont().deriveFont((float) (textArea.getFont().getSize() - 2)));
        });
    }

    // create a method to create format menu
    // create iItems
    public void createFormatMenu() {
        iwrap = new JMenuItem("Word wrap: OFF");
        menuEdit.add(iwrap);
        iwrap.addActionListener(this);
        iwrap.setActionCommand("Word wrap");

        menuFont = new JMenu("Font");
        menuEdit.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("F_Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("F_CSMS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("F_TNR");
        menuFont.add(iFontTNR);

        // Add other fonts as needed
        JMenuItem iFontCalibri = new JMenuItem("Calibri");
        iFontCalibri.addActionListener(this);
        iFontCalibri.setActionCommand("F_Calibri");
        menuFont.add(iFontCalibri);

        JMenuItem iFontCourierNew = new JMenuItem("Courier New");
        iFontCourierNew.addActionListener(this);
        iFontCourierNew.setActionCommand("F_CourierNew");
        menuFont.add(iFontCourierNew);

        JMenuItem iFontImpact = new JMenuItem("Impact");
        iFontImpact.addActionListener(this);
        iFontImpact.setActionCommand("F_Impact");
        menuFont.add(iFontImpact);

        // Add other fonts as needed

        JMenuItem iFontGeorgia = new JMenuItem("Georgia");
        iFontGeorgia.addActionListener(this);
        iFontGeorgia.setActionCommand("F_Georgia");
        menuFont.add(iFontGeorgia);

        JMenuItem iFontVerdana = new JMenuItem("Verdana");
        iFontVerdana.addActionListener(this);
        iFontVerdana.setActionCommand("F_Verdana");
        menuFont.add(iFontVerdana);

        JMenuItem iFontOldEnglishText = new JMenuItem("Old English Text");
        iFontOldEnglishText.addActionListener(this);
        iFontOldEnglishText.setActionCommand("F_OldEnglishText");
        menuFont.add(iFontOldEnglishText);

        menuFontSize = new JMenu("Font-size");
        menuEdit.add(menuFontSize);

        iFontSize_8 = new JMenuItem("8");
        iFontSize_8.addActionListener(this);
        iFontSize_8.setActionCommand("FS_8");
        menuFontSize.add(iFontSize_8);

        iFontSize_12 = new JMenuItem("12");
        iFontSize_12.addActionListener(this);
        iFontSize_12.setActionCommand("FS_12");
        menuFontSize.add(iFontSize_12);

        iFontSize_16 = new JMenuItem("16");
        iFontSize_16.addActionListener(this);
        iFontSize_16.setActionCommand("FS_16");
        menuFontSize.add(iFontSize_16);

        iFontSize_20 = new JMenuItem("20");
        iFontSize_20.addActionListener(this);
        iFontSize_20.setActionCommand("FS_20");
        menuFontSize.add(iFontSize_20);

        iFontSize_24 = new JMenuItem("24");
        iFontSize_24.addActionListener(this);
        iFontSize_24.setActionCommand("FS_24");
        menuFontSize.add(iFontSize_24);

        iFontSize_28 = new JMenuItem("28");
        iFontSize_28.addActionListener(this);
        iFontSize_28.setActionCommand("FS_28");
        menuFontSize.add(iFontSize_28);

    }

    public void createColorMenu() {
        iColor_1 = new JMenuItem("White");
        iColor_1.addActionListener(this);
        iColor_1.setActionCommand("White");
        menuColor.add(iColor_1);
        iColor_2 = new JMenuItem("Black");
        iColor_2.addActionListener(this);
        iColor_2.setActionCommand("Kalo");
        menuColor.add(iColor_2);
        iColor_3 = new JMenuItem("Blue");
        iColor_3.addActionListener(this);
        iColor_3.setActionCommand("Nil");
        menuColor.add(iColor_3);
    }

    public void createEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);
        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.newOpen();
                break;
            case "Save":
                file.newSave();
                break;
            case "Save as":
                file.newSave_as();
                break;
            case "Exit":
                file.newExit();
                break;
            case "Word wrap":
                format.wordwrap();// iwrap.setActionCommand("Word wrap");
                break;

            case "F_Arial":
                format.setFont("Arial");
                break;
            case "F_CSMS":
                format.setFont("Comic Sans MS");
                break;
            case "F_TNR":
                format.setFont("Times New Roman");
                break;
            case "F_Calibri":
                format.setFont("Calibri");
                break;
            case "F_CourierNew":
                format.setFont("Courier New");
                break;
            case "F_Impact":
                format.setFont("Impact");
                break;
            case "F_Georgia":
                format.setFont("Georgia");
                break;
            case "F_Verdana":
                format.setFont("Verdana");
                break;
            case "F_OldEnglishText":
                format.setFont("Old English Text");
                break;
            case "FS_8":
                format.createFont(8);
                break;
            case "FS_12":
                format.createFont(12);
                break;
            case "FS_16":
                format.createFont(16);
                break;
            case "FS_20":
                format.createFont(20);
                break;
            case "FS_24":
                format.createFont(24);
                break;
            case "FS_28":
                format.createFont(28);
                break;
            case "White":
                color.changeColor(command);
                break;
            case "Kalo":
                color.changeColor(command);
                break;
            case "Nil":
                color.changeColor(command);
                break;
            case "Undo":
                edit.Undo();
                break;
            case "Redo":
                edit.Redo(); // Call the Redo method
                break;

            // Add cases for other fonts as needed
            // default:
            // Handle other cases or do nothing
            // break;

            // default:
            // break;
        }

    }

    public static void main(String[] args) {
        // Created an instance of the class
        new Gui();
    }
}


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

import java.awt.Color;

public class FunctionColor {
    Gui gui;

    // constructor
    public FunctionColor(Gui gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {
        switch (color) {
            case "White":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.BLACK);
                break;
            case "Kalo":
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
                break;
            case "Nil":
                gui.window.getContentPane().setBackground(Color.blue);
                gui.textArea.setBackground(Color.blue);
                gui.textArea.setForeground(Color.white);
                break;

        }
    }
}
public class FunctionEdit {
    Gui gui;

    public FunctionEdit(Gui gui) {
        this.gui = gui;
    }

    public void Undo() {
        gui.un.undo();
    }

    public void Redo() { // Specify return type void
        gui.un.redo();
    }
}

import java.awt.*;
import java.awt.Font;

public class FunctionFormat {
    Gui gui;
    Font arial, comicSansMs, timesNewRoman, calibri, courierNew, impact, georgia, verdana, oldEnglishText;
    String selectedFont;

    public FunctionFormat(Gui gui) {
        this.gui = gui;
    }

    

    // word wrap
    public void wordwrap() {
        if (gui.wordWrapON == false) {
            gui.wordWrapON = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iwrap.setText("Word wrap: ON");
        } else {
            gui.wordWrapON = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iwrap.setText("Word wrap: OFF");
        }
    }

    public void createFont(int fontSize) {// local variable
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMs = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        // Add more fonts
        calibri = new Font("Calibri", Font.PLAIN, fontSize);
        courierNew = new Font("Courier New", Font.PLAIN, fontSize);
        impact = new Font("Impact", Font.PLAIN, fontSize);
        // Add more fonts as needed
        georgia = new Font("Georgia", Font.PLAIN, fontSize);
        verdana = new Font("Verdana", Font.PLAIN, fontSize);
        // Additional font with different styles
        oldEnglishText = new Font("Old English Text MT", Font.PLAIN, fontSize);
        setFont(selectedFont);
    }

    // set font method
    public void setFont(String font) {
        selectedFont = font;

        switch (selectedFont) {
            case "Arial":
                gui.textArea.setFont(arial);
                break;

            case "Comic Sans MS":
                gui.textArea.setFont(comicSansMs);
                break;

            case "Times New Roman":
                gui.textArea.setFont(timesNewRoman);
                break;

            case "Calibri":
                gui.textArea.setFont(calibri);
                break;

            case "Courier New":
                gui.textArea.setFont(courierNew);
                break;

            case "Impact":
                gui.textArea.setFont(impact);
                break;

            case "Georgia":
                gui.textArea.setFont(georgia);
                break;

            case "Verdana":
                gui.textArea.setFont(verdana);
                break;

            case "Old English Text":
                gui.textArea.setFont(oldEnglishText);
                break;

            // Add more cases for additional fonts

            default:
                // If the selected font is not recognized, set it to a default font (e.g.,
                // Arial)
                gui.textArea.setFont(arial);
                break;
        }
    }

}


```
