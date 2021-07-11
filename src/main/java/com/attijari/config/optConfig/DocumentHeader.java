package com.attijari.config.optConfig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentHeader {
    @XmlElement
    String receiveTime;
    @XmlElement
    String sendTime;
    @XmlElement
    String resultCode;
    @XmlElement
    String resultMessage;
    @XmlElement
    String encryptedConnexion;
    @XmlElement
    String transmissionID;

    public DocumentHeader(String receiveTime, String sendTime, String resultCode, String resultMessage, String encryptedConnexion, String transmissionID) {
        this.receiveTime = receiveTime;
        this.sendTime = sendTime;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.encryptedConnexion = encryptedConnexion;
        this.transmissionID = transmissionID;
    }

    public DocumentHeader() {
    }


    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getEncryptedConnexion() {
        return encryptedConnexion;
    }

    public void setEncryptedConnexion(String encryptedConnexion) {
        this.encryptedConnexion = encryptedConnexion;
    }

    public String getTransmissionID() {
        return transmissionID;
    }

    public void setTransmissionID(String transmissionID) {
        this.transmissionID = transmissionID;
    }

}
