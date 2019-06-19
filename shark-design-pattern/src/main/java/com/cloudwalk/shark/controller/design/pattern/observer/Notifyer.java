package com.cloudwalk.shark.controller.design.pattern.observer;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Notifyer implements INotifyer{
    private List<Observer> list = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        list.add(observer);
    }

    public void notifyObserver() {
        if(!CollectionUtils.isEmpty(list)){
            for(Observer observer:list){
                observer.updateObserver();
            }
        }
    }
}
