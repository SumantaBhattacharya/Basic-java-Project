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
