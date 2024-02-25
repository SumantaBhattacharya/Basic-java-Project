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
