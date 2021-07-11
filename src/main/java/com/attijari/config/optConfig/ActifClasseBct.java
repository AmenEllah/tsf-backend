package com.attijari.config.optConfig;

public class ActifClasseBct {
    private String codeErreur;
    private String messageErreur = null;
    private String classe;
    private String dateEffet;
    private String libelle;
    private String mntEngagemen;
    private String nbrDeclarants;
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

    public String getClasse() {
        return classe;
    }

    public String getDateEffet() {
        return dateEffet;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getMntEngagemen() {
        return mntEngagemen;
    }

    public String getNbrDeclarants() {
        return nbrDeclarants;
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

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMntEngagemen(String mntEngagemen) {
        this.mntEngagemen = mntEngagemen;
    }

    public void setNbrDeclarants(String nbrDeclarants) {
        this.nbrDeclarants = nbrDeclarants;
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
        return "ActifClasseBct{" +
            "codeErreur='" + codeErreur + '\'' +
            ", messageErreur='" + messageErreur + '\'' +
            ", classe='" + classe + '\'' +
            ", dateEffet='" + dateEffet + '\'' +
            ", libelle='" + libelle + '\'' +
            ", mntEngagemen='" + mntEngagemen + '\'' +
            ", nbrDeclarants='" + nbrDeclarants + '\'' +
            ", numCin='" + numCin + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
