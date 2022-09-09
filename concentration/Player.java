package concentration;

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
