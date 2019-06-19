package com.cloudwalk.shark.controller.design.pattern.observer;

public interface INotifyer {
    public void addObserver(Observer observer);
    public void notifyObserver();
}
