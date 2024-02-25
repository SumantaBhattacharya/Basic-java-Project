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
        // More additional fonts
        // symbol = new Font("Symbol", Font.PLAIN, fontSize);
        // wingdings = new Font("Wingdings", Font.PLAIN, fontSize);
        // lucidaSans = new Font("Lucida Sans", Font.PLAIN, fontSize);
        // tahoma = new Font("Tahoma", Font.PLAIN, fontSize);
        // palatino = new Font("Palatino", Font.PLAIN, fontSize);
        // garamond = new Font("Garamond", Font.PLAIN, fontSize);
        // centuryGothic = new Font("Century Gothic", Font.PLAIN, fontSize);

        // call
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
