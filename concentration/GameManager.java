package concentration;

import java.util.concurrent.TimeUnit;

public class GameManager {
    final static int ROWS = 3;
    final static int COLS = 4;
    final static int PLAYER_COUNT = 3;
    final static String[] SAMPLE_NAMES = {"Lennon", "Kaycee", "Yunus"};
    final static boolean IS_ONE_FLIP = false;

    static GUI gui;
    static Player[] players = new Player[PLAYER_COUNT];
    static int currentPlayerIdx = 0;
    static Board board;
    static String statusMsg = "";

    public static void main(String[] args) {
        gui = new GUI(ROWS, COLS);

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(SAMPLE_NAMES[i]);
        }

        board = new Board(ROWS*COLS, IS_ONE_FLIP);
        statusMsg = "Welcome. It's " + players[currentPlayerIdx].getName() + "'s turn";
        gui.show(board, statusMsg);
    }

    public static void revealCard(int space) {
        if (!board.canChoose()) {
            return;
        }
        if (!board.isValidSpace(space)) {
            return;
        }

        int result = board.revealSpace(space);
        if (result == 1) {
            statusMsg = "Match! for " + players[currentPlayerIdx].getName();
            players[currentPlayerIdx].addPoint();
            if (!board.canChoose()) {
                statusMsg = "";
                for (int i = 0; i < players.length; i++) {
                    statusMsg += players[i].getName() + ": " + players[i].getScore();
                    if (i < players.length-1) {
                        statusMsg += ", ";
                    }
                }
                gui.show(board, statusMsg);
                gui.enableContinueButton();
            }
        } else if (result == 0) {
            statusMsg = "No match...";
            gui.enableContinueButton();
        } else {
            statusMsg = "Ok, please choose another card";
        }

        gui.show(board, statusMsg);
    }

    public static void revertBoard() {
        if (board.canChoose()) {
            board.revert();
            currentPlayerIdx = (currentPlayerIdx + 1) % players.length;
        } else {
            board.resetBoard();
        }

        statusMsg = "It's " + players[currentPlayerIdx].getName() + "'s turn";
        gui.show(board, statusMsg);
    }
}
