package com.attijari.config.optConfig;

public class SubsciberSatusOut {
    private String status;
    private String hasCertificate;

    public SubsciberSatusOut() {
    }

    public SubsciberSatusOut(String status, String hasCertificate) {
        this.status = status;
        this.hasCertificate = hasCertificate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(String hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    @Override
    public String toString() {
        return "SubsciberSatusOut{" +
            "status='" + status + '\'' +
            ", hasCertificate='" + hasCertificate + '\'' +
            '}';
    }
}
