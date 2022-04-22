package com.tourofhanoi.game.swing.bubbleBrust;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BubbleBurstEx {

    // main frame, panel and layout
    private JFrame mainFrame;
    private JPanel controlPanel;
    private CardLayout cardLayout;

    // constructor
    public BubbleBurstEx() {
        initFrameAndLayoutGUI();
        createStartGUI();
    }

    // init all properties for the frame and layout
    private void initFrameAndLayoutGUI() {
        // config main panel and layouts
        cardLayout = new CardLayout();
        controlPanel = new JPanel();
        controlPanel.setLayout(cardLayout);

        mainFrame = new JFrame("Bubble Burst Game");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    // game start screen
    public void createStartGUI() {
        // start panel
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.setBackground(Color.WHITE);
        startPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 200));

        // start components
        JLabel gameName = new JLabel("Bubble Burst Game");
        gameName.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel intoMsg = new JLabel("Set Difficulty");


        // difficulty config
        JSlider difficultySlider = new JSlider(3, 5);
        JLabel difficultyMsg = new JLabel("Difficulty: Easy");
        difficultySlider.setValue(3);
        difficultySlider.addChangeListener(e -> {
            if (difficultySlider.getValue() == 3) {
                difficultyMsg.setText("Difficulty: Easy");
            }
            if (difficultySlider.getValue() == 4) {
                difficultyMsg.setText("Difficulty: Medium");
            }
            if (difficultySlider.getValue() == 5) {
                difficultyMsg.setText("Difficulty: Hard");
            }
        });

        // start button & action
        JButton startBtn = new JButton("START GAME");
        startBtn.addActionListener(e -> {
            startTheGame(difficultySlider.getValue());
        });

        // add all components
        startPanel.add(gameName);
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(intoMsg);
        startPanel.add(difficultySlider);
        startPanel.add(difficultyMsg);
        startPanel.add(startBtn);

        // add to main panel
        controlPanel.add("START", startPanel);
        cardLayout.show(controlPanel, "START");
        mainFrame.setVisible(true);
    }

    // load game screen and start the game
    private void startTheGame(int difficulty) {
        // Main Game panel
        JPanel gamePanel = new GamePanel(difficulty);

        // add to main panel and show
        controlPanel.add("GAME", gamePanel);
        cardLayout.show(controlPanel, "GAME");
        mainFrame.setVisible(true);
    }

    // main method to start the game
    public static void main(String[] args) {
        EventQueue.invokeLater(BubbleBurstEx::new);
    }
}