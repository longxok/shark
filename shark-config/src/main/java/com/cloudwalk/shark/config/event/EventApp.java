package com.cloudwalk.shark.config.event;

import com.cloudwalk.shark.config.event.custom.MyEventService;
import com.cloudwalk.shark.config.event.generics.EntiryWrapperEventService;
import com.cloudwalk.shark.config.event.standard.ContextStartedListener;
import com.cloudwalk.shark.config.event.standard.ContextStopListener;
import com.cloudwalk.shark.config.event.standard.MultiEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScans({@ComponentScan("com.cloudwalk.shark.config.event.custom"),
		@ComponentScan("com.cloudwalk.shark.config.event.generics")})
public class EventApp/* implements ApplicationListener */{

	@Autowired
	private MyEventService myEventService;

	@Bean
	ContextStopListener contextStopListener() {
		return new ContextStopListener();
	}
	
	@Bean
	ContextStartedListener contextStartedListener() {
		return new ContextStartedListener();
	}
	
	@Bean
	MultiEventListener multiEventListener() {
		return new MultiEventListener();
	}
	
	@Bean ExtBean extBean() {
		return new ExtBean();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(EventApp.class);
		System.out.println("Main Thread :" + Thread.currentThread().getId());
		
		//刷新容器，新增注入的EventApp.class。
		context.start();
		
		MyEventService service = context.getBean(MyEventService.class);
		service.publish("First publish!");
		service.publish("Second publish!");

		EntiryWrapperEventService wrapperService = context.getBean(EntiryWrapperEventService.class);
		wrapperService.publishPES();
		wrapperService.publishWOW();
		
		context.stop();
		context.close();
	}

	/*@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		myEventService.publish("First publish!");
		myEventService.publish("Second publish!");
	}*/
}




