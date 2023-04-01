import javax.swing.JFrame;

import View.BaseballGamePanel;
import View.BaseballCanvas;

import Model.concretePattern.ConcreteObserver;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        BaseballGamePanel panel = new BaseballGamePanel(window);
        panel.init();
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        ConcreteObserver observer = new ConcreteObserver("Observer 1");
        panel.addObserver(observer);

        BaseballCanvas canvas = panel.getCanvas();
        observer.addCanvas(canvas);
    }
}


