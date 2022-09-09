package concentration;

public class Board {
    private int cardCount;
    private boolean isOneFlip;
    private int[] board;
    private boolean[] isRevealed;
    private int firstRevealI = -1;
    private int firstRevealJ = -1;

    public Board(int cardCount, boolean isOneFlip) {
        this.isOneFlip = isOneFlip;
        this.cardCount = cardCount;
        board = new int[cardCount];
        isRevealed = new boolean[cardCount];
        this.resetBoard();
    }

    public void resetBoard() {
        int cardKinds = (cardCount)/2;
        int card;

        int[] kindUsed = new int[cardKinds];
        for (int i = 0; i < cardCount; i++) {
            do {
                card = Util.getRandomInt(0, cardKinds-1);
            } while (kindUsed[card] >= 2);
            board[i] = card;
            kindUsed[card]++;
        }

    }

    public boolean isValidSpace(int space) {
        if (space < 0 || space > cardCount-1) {
            Util.println("Invalid space selection");
            return false;
        }
        else if (isRevealed[space]) {
            Util.println("Square has already been revealed. Please choose again.");
            return false;
        }

        return true;
    }

    /*
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
    */
}
