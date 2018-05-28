package br.cin.ufpe.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousServerSocketChannel;

@Component
public class ServerRequestHandler {

    private final int portNumber;
    private final AsynchronousServerSocketChannel asyncListener;
    private int sentMessageSize;
    private int reciveMessage;
    private final String IP;
    private ApplicationEventPublisher publisher;

    @Autowired
    public ServerRequestHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel,
                                ApplicationEventPublisher publisher){
        this.asyncListener = asynchronousServerSocketChannel;
        this.portNumber = 5555;
        this.IP = "localhost";
        this.publisher = publisher;
    }

    public void init(){
        //create an asynchronous server socket channel bound to the default group
        try {
            if (asyncListener.isOpen()) {
                //set some options
                asyncListener.setOption(StandardSocketOptions.SO_RCVBUF,4 * 1024);
                asyncListener.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                //bind the server socket channel to local address
                asyncListener.bind(new InetSocketAddress(IP, this.portNumber));
                //display a waiting message while ... waiting clients
                System.out.println("Waiting for connections ...");
                asyncListener.accept(null, new ProcessIOHandler(this.asyncListener, this.publisher));
                // Wait
                System.in.read();
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public byte[] receive(){
        return null;
    }

    public void send(byte[] msg){

    }

}
