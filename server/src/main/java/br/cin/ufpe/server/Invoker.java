package br.cin.ufpe.server;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Invoker implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        String line = new String(ArrayUtils.toPrimitive(event.getMessage().getMsg()));
        // Debug
        System.out.println("Message: " + line);
    }
}
