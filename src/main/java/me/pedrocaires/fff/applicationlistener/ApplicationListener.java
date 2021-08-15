package me.pedrocaires.fff.applicationlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener {

	private final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	@EventListener
	public void onStartup(ApplicationReadyEvent event) {
		logger.info("Swagger listening at: http://localhost:8080/swagger-ui/index.html");
	}

}
