package com.attijari.service.dto;

import java.util.Arrays;

public class SubscriberInfoOutputDTO {

    private boolean status;
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private String email;
        private String first_name;
        private String last_name;
        private String birthday;
        private String cin;
        private String phone;
        private byte[] cin_recto;
        private byte[] cin_verso;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCin() {
            return cin;
        }

        public void setCin(String cin) {
            this.cin = cin;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public byte[] getCin_recto() {
            return cin_recto;
        }

        public void setCin_recto(byte[] cin_recto) {
            this.cin_recto = cin_recto;
        }

        public byte[] getCin_verso() {
            return cin_verso;
        }

        public void setCin_verso(byte[] cin_verso) {
            this.cin_verso = cin_verso;
        }

        @Override
        public String toString() {
            return "Data{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cin='" + cin + '\'' +
                ", phone='" + phone + '\'' +
                ", cin_recto=" + Arrays.toString(cin_recto) +
                ", cin_verso=" + Arrays.toString(cin_verso) +
                '}';
        }
    }

    @Override
    public String toString() {
        return "SubscriberInfoOutputDTO{" +
            "status=" + status +
            ", data=" + data +
            '}';
    }
}
