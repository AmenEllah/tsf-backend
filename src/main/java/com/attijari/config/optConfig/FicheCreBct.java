package com.attijari.config.optConfig;

public class FicheCreBct {
    private String codeErreur;
    private String messageErreur = null;
    private String etatCivile;
    private String lieuNaiss;
    private String nationalite;
    private String nom;
    private String prenom;
    private String qualite;
    private String sexe;


    // Getter Methods

    public String getCodeErreur() {
        return codeErreur;
    }

    public String getMessageErreur() {
        return messageErreur;
    }

    public String getEtatCivile() {
        return etatCivile;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getQualite() {
        return qualite;
    }

    public String getSexe() {
        return sexe;
    }

    // Setter Methods

    public void setCodeErreur(String codeErreur) {
        this.codeErreur = codeErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public void setEtatCivile(String etatCivile) {
        this.etatCivile = etatCivile;
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "FicheCreBct{" +
            "codeErreur='" + codeErreur + '\'' +
            ", messageErreur='" + messageErreur + '\'' +
            ", etatCivile='" + etatCivile + '\'' +
            ", lieuNaiss='" + lieuNaiss + '\'' +
            ", nationalite='" + nationalite + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", qualite='" + qualite + '\'' +
            ", sexe='" + sexe + '\'' +
            '}';
    }
}
