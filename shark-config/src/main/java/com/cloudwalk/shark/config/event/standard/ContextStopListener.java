package com.cloudwalk.shark.config.event.standard;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

public class ContextStopListener implements ApplicationListener<ContextStoppedEvent> {

	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		System.out.println("Stop Listener: I am stop");
	}
}
