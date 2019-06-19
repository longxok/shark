package com.cloudwalk.shark.api.design.pattern.observer;

public interface INotifyer {
    public void addObserver(Observer observer);
    public void notifyObserver();
}
