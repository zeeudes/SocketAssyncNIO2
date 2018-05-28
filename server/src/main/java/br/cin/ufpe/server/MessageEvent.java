package br.cin.ufpe.server;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    private Message message;

    public MessageEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }

    public Message getMessage(){
        return message;
    }
}
