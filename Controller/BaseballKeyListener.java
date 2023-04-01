package Controller;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Model.BaseballGame;
import Model.concretePattern.ConcreteSubject;
import View.BaseballGamePanel;

import java.awt.event.ActionEvent;

public class BaseballKeyListener implements ActionListener {

    private BaseballGamePanel panel;
    private int clicks = 0;
    private ConcreteSubject subject; // add subject field
    
    public BaseballKeyListener(BaseballGamePanel panel) {
        this.panel = panel;
        this.subject = new ConcreteSubject(); // initialize the subject
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        System.out.println(button.getText());
        if (button == panel.getPlayButton()) {
            var baseball = new BaseballGame();
            panel.setBaseball(baseball);
            panel.setGameState(BaseballGamePanel.GameState.PLAYING);
            int[] keys = baseball.getKey();
            String keyString = "" + keys[0] + keys[1] + keys[2];
            panel.getGameKeyField().setText(keyString);
            panel.getGuessField().setText("");
            for (var b: panel.getDigButtons()) {
                b.setEnabled(true);
            }
            panel.getCanvas().setBallStrikeCount(0, 0);
            panel.getCanvas().repaint();
        } else if (button == panel.getExitButton()) {
            JFrame window = panel.getWindow();
            window.getContentPane().removeAll();
            BaseballGamePanel panel = new BaseballGamePanel(window);
            panel.init();
            window.pack();
            window.revalidate();
        } else {
            button.setEnabled(false);

            JTextField guessField = panel.getGuessField();
            if (clicks == 0 ) guessField.setText("");

            BaseballGame baseball = panel.getBaseball();
            String n = button.getText();
            guessField.setText(guessField.getText() + n);
            baseball.setGuess(clicks, n.charAt(0) - '0');
            clicks++;

            if (clicks == 3) {
                baseball.computeBallsStrikes();
                int balls = baseball.getBallCount();
                int strikes = baseball.getStrikeCount();
                panel.getCanvas().setBallStrikeCount(balls, strikes);

                if (strikes == 3) {
                    panel.setGameState(BaseballGamePanel.GameState.GAMEOVER);
                    for (var b: panel.getDigButtons()) {
                        b.setEnabled(false);
                    }
                } else {
                    // enable all digit buttons
                    for (var b: panel.getDigButtons()) {
                        b.setEnabled(true);
                    }
                }

                String guessStr = guessField.getText(); // get the 3-digit guess
                subject.notifyObserver(guessStr); // notify the observer
                System.out.println("------------The observer received it---------");
                System.out.println("3 digits entered: " + guessStr); // print message to console
                System.out.println("Balls: " + balls + ", Strikes: " + strikes);

                
                clicks = 0;
                panel.getCanvas().repaint();
            }
        }
    }

    public int getClicks() {
        return clicks;
    }
}
