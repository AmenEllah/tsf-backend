package com.attijari.config.optConfig;

public class CreditParticulierBct {
    private String codeErreur;
    private String messageErreur = null;
    private String datePremEch;
    private String dateDernEch;
    private String dateEffet;
    private String dateNaiss;
    private String libelle;
    private String mntCmr;
    private String mntEncours;
    private String mntImpaye;
    private String numCin;
    private String nom;
    private String prenom;


    // Getter Methods

    public String getCodeErreur() {
        return codeErreur;
    }

    public String getMessageErreur() {
        return messageErreur;
    }

    public String getDatePremEch() {
        return datePremEch;
    }

    public String getDateDernEch() {
        return dateDernEch;
    }

    public String getDateEffet() {
        return dateEffet;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getMntCmr() {
        return mntCmr;
    }

    public String getMntEncours() {
        return mntEncours;
    }

    public String getMntImpaye() {
        return mntImpaye;
    }

    public String getNumCin() {
        return numCin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    // Setter Methods

    public void setCodeErreur(String codeErreur) {
        this.codeErreur = codeErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public void setDatePremEch(String datePremEch) {
        this.datePremEch = datePremEch;
    }

    public void setDateDernEch(String dateDernEch) {
        this.dateDernEch = dateDernEch;
    }

    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMntCmr(String mntCmr) {
        this.mntCmr = mntCmr;
    }

    public void setMntEncours(String mntEncours) {
        this.mntEncours = mntEncours;
    }

    public void setMntImpaye(String mntImpaye) {
        this.mntImpaye = mntImpaye;
    }

    public void setNumCin(String numCin) {
        this.numCin = numCin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Override
    public String toString() {
        return "CreditParticulierBct{" +
            "codeErreur='" + codeErreur + '\'' +
            ", messageErreur='" + messageErreur + '\'' +
            ", datePremEch='" + datePremEch + '\'' +
            ", dateDernEch='" + dateDernEch + '\'' +
            ", dateEffet='" + dateEffet + '\'' +
            ", dateNaiss='" + dateNaiss + '\'' +
            ", libelle='" + libelle + '\'' +
            ", mntCmr='" + mntCmr + '\'' +
            ", mntEncours='" + mntEncours + '\'' +
            ", mntImpaye='" + mntImpaye + '\'' +
            ", numCin='" + numCin + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
