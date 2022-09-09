package concentration;

public class GameManager {
    final static int ROWS = 2;
    final static int COLS = 4;

    public static void main(String[] args) {
        GUI gui = new GUI(ROWS, COLS);
        gui.show();
    }

    public static int getRows() {
        return ROWS;
    }

    public static int getCols() {
        return COLS;
    }
}
