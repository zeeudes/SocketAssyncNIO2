package br.cin.ufpe.server;

import java.io.Serializable;

public class Message implements Serializable {

    private Byte[] msg;

    public Message(Byte[] msg) {
        this.msg = msg;
    }

    public Byte[] getMsg() {
        return msg;
    }
}
