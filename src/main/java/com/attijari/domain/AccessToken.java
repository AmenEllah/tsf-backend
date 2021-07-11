package com.attijari.domain;


import javax.persistence.*;
import java.util.Date;

@Table(name = "Access_Tokens")
@Entity
public class AccessToken {

    @Id
    @Column(name="accessToken")
    private String accessToken;

    @Column(name="creation_date")
    private Date creationDate;

    @Column(name="number_of_use")
    private int numberOfUse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfUse() {
        return numberOfUse;
    }

    public void setNumberOfUse(int numberOfUse) {
        this.numberOfUse = numberOfUse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
            "accessToken='" + accessToken + '\'' +
            ", creationDate=" + creationDate +
            ", numberOfUse=" + numberOfUse +
            ", user=" + user +
            '}';
    }
}
