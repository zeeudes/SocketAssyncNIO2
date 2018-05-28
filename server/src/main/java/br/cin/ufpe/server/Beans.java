package br.cin.ufpe.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.support.TaskUtils;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;

@Configuration
public class Beans {

    @Bean
    public AsynchronousServerSocketChannel asynchronousServerSocketChannel() {
        try {
            return AsynchronousServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        eventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
        return eventMulticaster;
    }

}
