package com.tourofhanoi.game.swing.bubbleBrust;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class GamePanel extends JPanel {

    // game data properties
    private static final int MAX_LEVEL = 10;
    private int gameRound = 1;
    private Color bubbleColor = Color.red;
    private static boolean isWin = false;
    private Timer timer;
    private long startTime = -1;
    private long duration = 5000;
    private int neiDistance = 10;

    // UI properties
    private MouseHandler mouseHandler;
    private final int difficulty;
    private Label gameRoundLbl;
    private Ellipse2D[] bubbles;
    private ArrayList<Integer> brustedBubbles;

    // defaults constructor
    public GamePanel(int difficulty) {
        this.difficulty = difficulty;

        mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);

        bubbles = new Ellipse2D[difficulty];
        brustedBubbles = new ArrayList<>();
        gameRoundLbl = new Label("");
        add(gameRoundLbl);

        // start new round
        newRound();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        int randX = GUtils.getRandomNum(370, 430);
        int randY = GUtils.getRandomNum(270, 330);

        // reposition local & global, collision
//        System.out.println("\nRound: "+gameRound);
        for (int i = 0; i < difficulty; i++) {
            if (bubbles[i] == null) {

                bubbles[i] = new Ellipse2D.Double(randX, randY, 50, 50);
//                g2d.setColor(new Color(0, 0, 0, (float) 0.0));
//                g2d.fill(bubbles[i]);

                // local neighbor find algorithm and set for next one
                Point point = getRandomNeighbor(randX, randY);
                while (point.x > 700 || point.y > 500) {
                    point = getRandomNeighbor(randX, randY);
                }
                randX = point.x;
                randY = point.y;
            }
            // process color
            if (isSelected(i)) {
                g2d.setColor(new Color(0, 0, 0, (float) 0.0));
                g2d.fill(bubbles[i]);
            } else {
                g2d.setColor(Color.RED);
                g2d.fill(bubbles[i]);
            }
        }
    }


    // handle new round
    private void newRound() {
        gameRoundLbl.setText("Game Round: " + (gameRound++));

        // win check
        if (gameRound > MAX_LEVEL) {
            isWin = true;
            timer.stop();
            gameOver();
            return;
        }

        // if time is already running
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = null;
        startTime = -1;

        // start timer for new round
        timer = new Timer(20, e -> {
            if (startTime < 0) {
                startTime = System.currentTimeMillis();
            }
            long now = System.currentTimeMillis();
            long clockTime = now - startTime;
            if (clockTime >= duration) {
                timer.stop();
                if (!isWin) {
                    gameOver();
                }
            }
        });
        timer.start();

        neiDistance += 5;
        duration -= 250;
        repositionBubbles();
    }

    // rest bubble potions to null
    private void repositionBubbles() {
        // set new color for new round
        bubbleColor = GUtils.getRandomColor();
        brustedBubbles.clear();
        Arrays.fill(bubbles, null);
        repaint();
    }


    private Point getRandomNeighbor(int x, int y) {
        int adder = 30 + neiDistance;
        Point[] points = new Point[4];
        points[0] = new Point(x - adder, y - adder);
        points[1] = new Point(x + adder, y + adder);
        points[2] = new Point(x - adder, y + adder);
        points[3] = new Point(x + adder, y - adder);
        return points[GUtils.getRandomNum(0, 3)];
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + "}";
        }
    }

    // check is the given circle index was already selected or not
    private boolean isSelected(int i) {
        for (int x : brustedBubbles) {
            if (x == i) return true;
        }
        return false;
    }

    // outside click game over notice
    private void gameOver() {

        JPanel panel = new JPanel();
        JButton exitBtn = new JButton("Exit");
        JLabel label = new JLabel("Game Over!");
        JLabel restartLabel = new JLabel("Press 'ok' for restart the game.");

        // restart the game
        exitBtn.addActionListener(e -> {
            System.exit(0);
        });

        if (isWin) {
            label.setText("Congratulation, you win the game.");
        }

        panel.add(label);
        panel.add(restartLabel);
        panel.add(exitBtn);

        JOptionPane.showMessageDialog(null, panel, "Success", JOptionPane.INFORMATION_MESSAGE);

        resetGame();
    }

    // reset game properties
    private void resetGame() {
        Arrays.fill(bubbles, null);
        brustedBubbles.clear();
        gameRound = 1;
        isWin = false;
        duration = 5000;
        neiDistance = 10;
        newRound();
    }

    // handle mouse click or events
    private class MouseHandler extends MouseAdapter implements MouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            // find and add selected one that need to remove
            boolean isClickedOutSide = true;
            for (int i = 0; i < difficulty; i++) {
                if (bubbles[i] != null && bubbles[i].contains(e.getX(), e.getY())) {
                    brustedBubbles.add(i);
                    isClickedOutSide = false;
                }
            }

            // reprint all all elements in the surface
            if (isClickedOutSide) {
                gameOver();
            } else {
                repaint();
                if (brustedBubbles.size() == difficulty) {
                    newRound();
                }
            }
        }
    }
}

