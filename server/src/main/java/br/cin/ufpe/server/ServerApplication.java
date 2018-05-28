package br.cin.ufpe.server;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@EnableAsync
@SpringBootApplication
public class ServerApplication {

	private final ServerRequestHandler serverRequestHandler;
	private final ApplicationEventPublisher publisher;

	@Autowired
	public ServerApplication(ServerRequestHandler serverRequestHandler,
							 ApplicationEventPublisher publisher){
		this.serverRequestHandler = serverRequestHandler;
		this.publisher = publisher;
	}

	@PostConstruct
	public void init(){
		publisher.publishEvent(new MessageEvent(this, new Message(ArrayUtils.toObject("gato preto".getBytes()))));
		serverRequestHandler.init();
	}

	public static void main(String[] args){
		SpringApplication.run(ServerApplication.class, args);
	}
}

