package com.attijari.domain;


import javax.annotation.Nullable;
import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Offer.
 */
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;
    @Nullable
    @Column(name = "price")
    private Double price;
    @Nullable
    @Column(name = "url")
    private String url;

    @Column(name = "code")
    private String code;

    @Nullable
    @OneToMany(mappedBy = "offer")
    private Set<Request> requests = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "offer_detail_offer",
        joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "detail_offer_id", referencedColumnName = "id"))
    private Set<DetailOffer> detailOffers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Offer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public Offer price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public Offer url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Offer requests(Set<Request> requests) {
        this.requests = requests;
        return this;
    }

    public Offer addRequest(Request request) {
        this.requests.add(request);
        request.setOffer(this);
        return this;
    }

    public Offer removeRequest(Request request) {
        this.requests.remove(request);
        request.setOffer(null);
        return this;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Set<DetailOffer> getDetailOffers() {
        return detailOffers;
    }

    public Offer detailOffers(Set<DetailOffer> detailOffers) {
        this.detailOffers = detailOffers;
        return this;
    }

    public Offer addDetailOffer(DetailOffer detailOffer) {
        this.detailOffers.add(detailOffer);
        detailOffer.getOffers().add(this);
        return this;
    }

    public Offer removeDetailOffer(DetailOffer detailOffer) {
        this.detailOffers.remove(detailOffer);
        detailOffer.getOffers().remove(this);
        return this;
    }

    public void setDetailOffers(Set<DetailOffer> detailOffers) {
        this.detailOffers = detailOffers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        return id != null && id.equals(((Offer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Offer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", url='" + getUrl() + "'" +
            "}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
