package Model.concretePattern;

import Model.observerPattern.Observer;

import View.BaseballCanvas;

public class ConcreteObserver implements Observer {
    

    private String name;

    
    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {}

    public void addCanvas(BaseballCanvas canvas) {}
}
