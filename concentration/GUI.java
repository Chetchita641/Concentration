package concentration;

import java.awt.*;
import java.awt.event.*;
import java.util.random.RandomGenerator;
import javax.swing.*;

public class GUI {
    final static int WINDOW_WIDTH = 400;
    final static int WINDOW_HEIGHT = 400;
    private static JFrame mainFrame;
    private static JPanel boardPanel;
    private static JLabel statusLabel;

    private static int rows;
    private static int cols;

    public GUI(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        prepareGUI();
    }

    private static void prepareGUI() {
        mainFrame = new JFrame("My Concentration Game");
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize((int)(WINDOW_WIDTH*0.9), Math.min((int)(WINDOW_HEIGHT*0.2), 100));

        mainFrame.add(boardPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void show() {
        statusLabel.setText("Welcome to Computer Concentration");

        JButton[] cards = new JButton[rows*cols];
        for (int i = 0; i < rows*cols; i++) {
            cards[i] = new JButton("Card");
            cards[i].addActionListener(new CardListener());
            cards[i].setActionCommand(""+i);
            boardPanel.add(cards[i]);
        }

        mainFrame.setVisible(true);
    }

    private class CardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNum = (String)e.getActionCommand();
            statusLabel.setText("Card " + cardNum + " has been clicked");
        }
    }
}
