package com.maestros.bottlehoz.model;

public class ChatModel {


    String msgReceive;
    String msgSend;

    public String getMsgReceive() {
        return msgReceive;
    }

    public void setMsgReceive(String msgReceive) {
        this.msgReceive = msgReceive;
    }

    public String getMsgSend() {
        return msgSend;
    }

    public void setMsgSend(String msgSend) {
        this.msgSend = msgSend;
    }

    public ChatModel(String msgReceive, String msgSend) {
        this.msgReceive = msgReceive;
        this.msgSend = msgSend;
    }
}
