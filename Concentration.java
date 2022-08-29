import java.util.ArrayList;

class Player {
    private String name;
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void addPoint() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }
}

public class Concentration {
    public static void main(String[] args) {
        String[] options = {"1. Start regular game", "2. Play auto game"};
        String menu = Util.generateMenu("Concentration demo", options);

        int selection;
        boolean quitProg = false;
        do {
            selection = Util.getInt(menu);
            switch (selection) {
                case 1:
                    playGame(false);
                    quitProg = !Util.getYorN("Would you like to play again? (Y/N)", true);
                    break;
                case 2:
                    playGame(true);
                    quitProg = !Util.getYorN("Would you like to play again? (Y/N)", true);
                    break;
                case 0:
                    quitProg = true;
                    break;
                default:
                    Util.println("Invalid selection. Please try again.");
            }
        } while (!quitProg);
    }

    private static void playGame(boolean autoPlay) {
        int playerCount;
        ArrayList<Player> playerList = new ArrayList<>();
        boolean isOneFlip;
        if (!autoPlay) {
            playerCount = Util.getInt("Please enter how many players", true);
            for (int i = 0; i < playerCount; i++) {
                String name = Util.getString("Please enter a name for Player " + (i+1));
                Player p = new Player(name);
                playerList.add(p);
            }
            isOneFlip = Util.getYorN("Would you like the 'one-flip' rule? (Y/N)");
        } else {
            playerCount = 2;
            playerList.add(new Player("Bill"));
            playerList.add(new Player("Ted"));
            isOneFlip = false;
        }

        Board board = new Board(isOneFlip);

        if (!autoPlay) {
            int turn = 0;
            boolean result;
            while (board.canChoose()) {
                board.draw();

                int space;
                do {
                    space = Util.getInt(playerList.get(turn).getName() + ", please choose a space", true);
                } while (!board.isValidSpace(space));
                board.revealSpace(space);

                do {
                    space = Util.getInt(playerList.get(turn).getName() + ", please choose another space", true);
                } while (!board.isValidSpace(space));
                result = board.revealSpace(space);
                if (result) {
                    playerList.get(turn).addPoint();
                    if (!isOneFlip) {
                        turn = (turn + 1) % playerList.size();
                    }
                } else {
                    turn = (turn + 1) % playerList.size();
                }

                displayScore(playerList);
            }
        }
    }

    public static void displayScore(ArrayList<Player> playerList) {
        Util.println("Score");
        Util.println("---------");
        for (int i = 0; i < playerList.size(); i++) {
            Util.println(playerList.get(i).getName() + ": " + playerList.get(i).getScore());
        }
    }
}
