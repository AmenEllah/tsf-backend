package com.attijari.domain;

public class QrCodeUtils {
    private String url;

    public QrCodeUtils(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
