package concentration;

import java.awt.*;
import java.awt.event.*;
import java.util.random.RandomGenerator;
import javax.swing.*;

public class GUI {
    final static int WINDOW_WIDTH = 600;
    final static int WINDOW_HEIGHT = 650;
    final static String CARD_TYPE = "food";
    final static String[] FOOD_CARDS =
            {"apple.jpg", "avocado.jpg", "greenapple.jpg",
             "peach.jpg", "pear.jpg", "pineapple.jpg"};
    private static JFrame mainFrame;
    private static JPanel boardPanel;
    private static JLabel statusLabel;
    private static JButton continueButton;

    private static int rows;
    private static int cols;

    private JButton[] cards;
    private ImageIcon[] cardFaces;
    private boolean canClickCard = true;

    public GUI(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("My Concentration Game");
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));

        cards = new JButton[rows*cols];
        for (int i = 0; i < rows*cols; i++) {
            cards[i] = new JButton("Card");
            cards[i].addActionListener(new CardListener());
            cards[i].setActionCommand(""+i);
            boardPanel.add(cards[i]);
        }

        initCardFaces();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize((int)(WINDOW_WIDTH*0.9), Math.min((int)(WINDOW_HEIGHT*0.2), 100));

        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ContinueListener());
        continueButton.setEnabled(false);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        bottomPanel.add(statusLabel, c);

        c.weightx = 0.5;
        c.gridx = 1;
        bottomPanel.add(continueButton, c);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 5;
        c.ipady = 300;
        mainFrame.add(boardPanel, c);

        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.ipady = 50;
        mainFrame.add(bottomPanel, c);

        mainFrame.setVisible(true);
    }

    private void initCardFaces() {
        cardFaces = new ImageIcon[(rows*cols)/2];

        java.net.URL imgUrl;
        String path = "";
        for (int i = 0; i < cardFaces.length; i++) {
            if (CARD_TYPE.equals("food")) {
                path = "../images/" + FOOD_CARDS[i];
            }
            imgUrl = GUI.class.getResource(path);
            if (imgUrl != null) {
                cardFaces[i] = new ImageIcon(imgUrl);
            } else {
                System.err.println("Unable to find file " + path);
                System.exit(1);
            }
        }
    }

    public void enableContinueButton() {
        canClickCard = false;
        continueButton.setEnabled(true);
    }

    public void show(Board board, String statusMsg) {
        statusLabel.setText(statusMsg);


        for (int i = 0; i < cards.length; i++) {
            if (board.getRevealed(i)) {
                cards[i].setIcon(cardFaces[board.getSpace(i)]);
                cards[i].setText("");
            } else {
                cards[i].setIcon(null);
                cards[i].setText("Card " + i);
            }
        }

        mainFrame.setVisible(true);
    }

    private class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canClickCard) {
                String cardNum = (String)e.getActionCommand();
                GameManager.revealCard(Integer.parseInt(cardNum));
            }
        }
    }

    private class ContinueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canClickCard = true;
            continueButton.setEnabled(false);
            GameManager.revertBoard();
        }
    }
}
