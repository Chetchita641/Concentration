public class Board {
    private final int WIDTH = 5;
    private final int HEIGHT = 6;
    private boolean isOneFlip;
    private int[][] board;
    private boolean[][] isRevealed;
    private int firstRevealI = -1;
    private int firstRevealJ = -1;

    public Board(boolean isOneFlip) {
        this.isOneFlip = isOneFlip;
        board = new int[HEIGHT][WIDTH];
        isRevealed = new boolean[HEIGHT][WIDTH];
        this.resetBoard();
    }

    public void resetBoard() {
        int cards = (this.HEIGHT*this.WIDTH)/2;
        int card;

        int[] cardPlayed = new int[cards];
        for (int h = 0; h < this.HEIGHT; h++) {
            for (int w = 0; w < this.WIDTH; w++) {
                do {
                    card = Util.getRandomInt(0, cards-1);
                } while (cardPlayed[card] >= 2);
                board[h][w] = card;
                cardPlayed[card]++;
            }
        }
    }

    public boolean isValidSpace(int space) {
        int i = space / 10;
        int j = space % 10;
        if (i < 1 || i > this.HEIGHT || j < 1 || j > this.WIDTH) {
            Util.println("Invalid space selection");
            return false;
        }
        else if (isRevealed[i][j]) {
            Util.println("Square has already been revealed. Please choose again.");
            return false;
        }

        return true;
    }

    public boolean revealSpace(int space) {
        int i = space / 10 - 1;
        int j = space % 10 - 1;
        boolean isMatch = false;

        if (firstRevealI == -1 && firstRevealJ == -1) {
            firstRevealI = i;
            firstRevealJ = j;
            isRevealed[i][j] = true;
            draw();
            return false;
        }

        isRevealed[i][j] = true;

        draw();
        if (board[i][j] == board[firstRevealI][firstRevealJ]) {
            Util.println("MATCH!");
            isMatch = true;
        } else {
            Util.println("no match");
            isRevealed[i][j] = false;
            isRevealed[firstRevealI][firstRevealJ] = false;
            isMatch = false;
        }

        firstRevealI = -1;
        firstRevealJ = -1;
        return isMatch;
    }

    public boolean canChoose() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                if (!isRevealed[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw() {
        String line = "";
        for (int w = 0; w < this.WIDTH; w++) {
            line += "+----";
        }
        line += "+";
        Util.println(line);

        String spaces = "";
        for (int i = 0; i < this.HEIGHT; i++) {
            spaces = "|";
            for (int j = 0; j < this.WIDTH; j++) {
                if (isRevealed[i][j]) {
                    spaces += String.format(" %2d |", board[i][j]);
                } else {
                    spaces += " ## |";
                }
            }
            Util.println(spaces);
            Util.println(line);
        }
    }

}
