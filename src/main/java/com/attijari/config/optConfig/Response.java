package com.attijari.config.optConfig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Response {
    @XmlElement
    private DocumentHeader documentHeader;

    List<CreditParticulierBct> listCreditParticulierBct;

    List<ClientDelta> listClientsDelta;

    List<FicheCreBct> listFicheCreBct;

    List<ChequeImpayeBct> listChequeImpayeBct;

    List<ActifClasseBct> listActifClasseBct;

    List<infoClient> infoClient;

    public List<CompteRealTimeDelta> getListComptesRealTimeAboDelta() {
        return listComptesRealTimeAboDelta;
    }

    public void setListComptesRealTimeAboDelta(List<CompteRealTimeDelta> listComptesRealTimeAboDelta) {
        this.listComptesRealTimeAboDelta = listComptesRealTimeAboDelta;
    }

    List<InfoCompte> listInfoCompte;

     List<CompteRealTimeDelta> listComptesRealTimeAboDelta;


    public Response(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    public Response() {
    }

    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    public List<CreditParticulierBct> getListCreditParticulierBct() {
        return listCreditParticulierBct;
    }

    public void setListCreditParticulierBct(List<CreditParticulierBct> listCreditParticulierBct) {
        this.listCreditParticulierBct = listCreditParticulierBct;
    }

    public List<ClientDelta> getListClientsDelta() {
        return listClientsDelta;
    }

    public void setListClientsDelta(List<ClientDelta> listClientsDelta) {
        this.listClientsDelta = listClientsDelta;
    }

    public List<FicheCreBct> getListFicheCreBct() {
        return listFicheCreBct;
    }

    public void setListFicheCreBct(List<FicheCreBct> listFicheCreBct) {
        this.listFicheCreBct = listFicheCreBct;
    }

    public List<ActifClasseBct> getListActifClasseBct() {
        return listActifClasseBct;
    }

    public void setListActifClasseBct(List<ActifClasseBct> listActifClasseBct) {
        this.listActifClasseBct = listActifClasseBct;
    }

    public List<ChequeImpayeBct> getListChequeImpayeBct() {
        return listChequeImpayeBct;
    }

    public void setListChequeImpayeBct(List<ChequeImpayeBct> listChequeImpayeBct) {
        this.listChequeImpayeBct = listChequeImpayeBct;
    }

    public List<infoClient> getInfoClient() {
        return infoClient;
    }

    public void setInfoClient(List<infoClient> infoClient) {
        this.infoClient = infoClient;
    }

    public List<InfoCompte> getListInfoCompte() {
        return listInfoCompte;
    }

    public void setListInfoCompte(List<InfoCompte> listInfoCompte) {
        this.listInfoCompte = listInfoCompte;
    }
}
