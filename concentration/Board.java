package concentration;

public class Board {
    private int cardCount;
    private boolean isOneFlip;
    private int[] board;
    private boolean[] isRevealed;
    private int firstReveal = -1;
    private int secondReveal = -1;

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
            isRevealed[i] = false;
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

    public int revealSpace(int space) {
        isRevealed[space] = true;
        if (firstReveal == -1) {
            firstReveal = space;
            return -1;
        } else {
            secondReveal = space;
            if (board[firstReveal] == board[secondReveal]) {
                firstReveal = -1;
                secondReveal = -1;
                return 1;
            } else {
                return 0;
            }
        }
    }

    public void revert() {
        if (firstReveal == -1 || secondReveal == -1) {
            return;
        }

        isRevealed[firstReveal] = false;
        isRevealed[secondReveal] = false;

        firstReveal = secondReveal = -1;
    }

    public boolean canChoose() {
        for (int i = 0; i < isRevealed.length; i++) {
            if (!isRevealed[i]) {
                return true;
            }
        }
        return false;
    }

    public int getSpace(int space) {
        if (space > cardCount-1) {
            return -1;
        }

        return board[space];
    }

    public boolean getRevealed(int space) {
        if (space > cardCount-1) {
            return false;
        }

        return isRevealed[space];
    }
    /*
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
