package com.cloudwalk.shark.config.event.generics;

import org.springframework.context.event.EventListener;

public class EntiryWrapperEventListener {
	
	@EventListener
	public void handlePES(EntityWrapperEvent<PES> evnet) {
		System.out.println("EntiryWrapper PES: " +  evnet);
	}
	
	@EventListener
	public void handleWOW(EntityWrapperEvent<WOW> evnet) {
		System.out.println("EntiryWrapper WOW: " +  evnet);
	}
}
