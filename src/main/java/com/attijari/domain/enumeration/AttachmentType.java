package com.attijari.domain.enumeration;

/**
 * The AttachmentType enumeration.
 */
public enum AttachmentType {
    CIN_RECTO("Carte d'identité Nationale Recto"),
    CIN_VERSO("Carte d'identité Nationale Verso"),
    SECOND_NATIONALITY("Deuxiéme Nationalité"),
    RESIDENCY_RECTO("Certificat de résidence Recto"),
    RESIDENCY_VERSO("Certificat de résidence Verso"),
    INCOME("Justificatif de revenu"),
    FATCA("Document FATCA"),
    CONTRACT("Contrat"),
    SIGNATURE("signature");
    public final String label;

    private AttachmentType(String label) {
        this.label = label;
    }
}
