package com.attijari.service.dto;

import java.time.LocalDate;
import java.util.List;

public class AllDataOfDocDto {
    private String numCarteIdentite;
    private LocalDate dateDelivranceCarteIdentite;
    private LocalDate deliveryDate;
    private LocalDate experationDate;
    private Boolean illimitedExperationDate;
    private List<String> justifResidenceList;
    private List<String> justifRevenuList;
    private String numDocumentJustif;
    private String rectoCin;
    private String versoCin;
    private String fatca;
    private String path;
    private String residencyRecto;
    private String residencyVerso;
    public String getNumCarteIdentite() {
        return numCarteIdentite;
    }

    public void setNumCarteIdentite(String numCarteIdentite) {
        this.numCarteIdentite = numCarteIdentite;
    }

    public LocalDate getDateDelivranceCarteIdentite() {
        return dateDelivranceCarteIdentite;
    }

    public void setDateDelivranceCarteIdentite(LocalDate dateDelivranceCarteIdentite) {
        this.dateDelivranceCarteIdentite = dateDelivranceCarteIdentite;
    }

    public List<String> getJustifResidenceList() {
        return justifResidenceList;
    }

    public void setJustifResidenceList(List<String> justifResidenceList) {
        this.justifResidenceList = justifResidenceList;
    }

    public List<String> getJustifRevenuList() {
        return justifRevenuList;
    }

    public void setJustifRevenuList(List<String> justifRevenuList) {
        this.justifRevenuList = justifRevenuList;
    }

    public String getNumDocumentJustif() {
        return numDocumentJustif;
    }

    public void setNumDocumentJustif(String numDocumentJustif) {
        this.numDocumentJustif = numDocumentJustif;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getExperationDate() {
        return experationDate;
    }

    public void setExperationDate(LocalDate experationDate) {
        this.experationDate = experationDate;
    }

    public Boolean getIllimitedExperationDate() {
        return illimitedExperationDate;
    }

    public void setIllimitedExperationDate(Boolean illimitedExperationDate) {
        this.illimitedExperationDate = illimitedExperationDate;
    }

    public String getRectoCin() {
        return rectoCin;
    }

    public void setRectoCin(String rectoCin) {
        this.rectoCin = rectoCin;
    }

    public String getVersoCin() {
        return versoCin;
    }

    public void setVersoCin(String versoCin) {
        this.versoCin = versoCin;
    }

    public String getFatca() {
        return fatca;
    }

    public void setFatca(String fatca) {
        this.fatca = fatca;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResidencyRecto() {
        return residencyRecto;
    }

    public void setResidencyRecto(String residencyRecto) {
        this.residencyRecto = residencyRecto;
    }

    public String getResidencyVerso() {
        return residencyVerso;
    }

    public void setResidencyVerso(String residencyVerso) {
        this.residencyVerso = residencyVerso;
    }

    public AllDataOfDocDto() {

    }

}
