package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.BaseballKeyListener;
import Model.BaseballGame;
import Model.concretePattern.ConcreteObserver;
import Model.concretePattern.ConcreteSubject;


public class BaseballGamePanel {

    private ConcreteSubject subject = new ConcreteSubject();
    public enum GameState {
        READY, PLAYING, GAMEOVER
    }
    
    private JFrame window;
    private BaseballCanvas canvas; 
    private JTextField gameKeyField = new JTextField();
    private JTextField guessField = new JTextField(); 
    private JButton[] digitButtons;
    private JButton playButton = new JButton("Play Ball");
    private JButton exitButton = new JButton ("Exit");
    private GameState gamestate = GameState.READY; 

    private BaseballGame baseball; 

    public BaseballGamePanel(JFrame window) {
        this.window = window;
    }

    public void init() {
        Container cp = window.getContentPane();

        canvas = new BaseballCanvas(this);
        cp.add(BorderLayout.CENTER, canvas);
    
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2)); 
        northPanel.add(new JLabel("Secret Game Key: "));
        northPanel.add(gameKeyField);
        northPanel.add(new JLabel("Your Guess: "));
        northPanel.add(guessField);
        gameKeyField.setEditable(false);
        guessField.setEditable(false);
    
        cp.add(BorderLayout.NORTH, northPanel);

        canvas = new BaseballCanvas(this);
        cp.add(BorderLayout.CENTER, canvas);


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(4, 3));

        BaseballKeyListener keyListener = new BaseballKeyListener(this); 

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton("" + i);
            southPanel.add(digitButtons[i]);
            digitButtons[i].addActionListener(keyListener);
        }

        // 3rd click from guessField notifies Observer implementation

        guessField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText();
                if (guess.length() == 3) {
                    subject.notifyObserver(guess);
                }
            }
           

            
        });

        playButton.addActionListener(keyListener);
        exitButton.addActionListener(keyListener);
        southPanel.add(playButton);
        southPanel.add(exitButton);
        cp.add(BorderLayout.SOUTH, southPanel); 

        for (var b: digitButtons) {
            b.setEnabled(false);
        }

    }

    public BaseballGame getBaseballGame() {
        return baseball; 
    }

    public JFrame getWindow() {
        return window; 
    }

    public BaseballCanvas getCanvas() {
        return canvas; 
    }

    public JTextField getGameKeyField() {
        return gameKeyField;
    }

    public JTextField getGuessField() {
        return guessField; 
    }

    public JButton[] getDigButtons() {
        return digitButtons; 
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getExitButton() {
        return exitButton; 
    }

    public GameState getGameState() {
        return gamestate;
    }

    public void setGameState(GameState state) {
        this.gamestate = state; 
    }

    public BaseballGame getBaseball() {
        return baseball;
    }

    public void setBaseball(BaseballGame baseball) {
        this.baseball = baseball; 
    }

    public void addObserver(ConcreteObserver observer) {
        subject.addObserver(observer);
    }
}
