package Model.concretePattern;

import java.util.ArrayList;
import java.util.List;


import Model.observerPattern.Observer;
import Model.observerPattern.Subject;
import Model.observerPattern.Subject;

public class ConcreteSubject implements Subject {
    

    private List<Observer> observers = new ArrayList<Observer>();
    private String event;
	 
	 

    @Override
    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
	public void removeObserver(Observer observer) {
		
		observers.remove(observer);		
	}

	@Override
	public void notifyObserver(String event) {
		
		 this.event = event;
	        for (Observer observer : observers) {
	            observer.update(event);
	    }	
	}

	public String getEvent() {
        return event;
    }

}
