package com.cloudwalk.shark.mybatis.design.pattern.observer;

public interface INotifyer {
    public void addObserver(Observer observer);
    public void notifyObserver();
}
