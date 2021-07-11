package com.attijari.config.optConfig;

import com.attijari.service.dto.RequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface MiddleWareService {

    ResponseEntity<Response> sendEmail(String to, String cc, String cci, String subject, String content);

    List<ClientDelta> getInfoClientByIden (String CIN);

    FicheCreBct  getFicheCre(String numCin, String dateNaiss);

    ChequeImpayeBct getChequeImpaye(String numCin, String dateNaiss);

    List<ActifClasseBct> getActifClasse(String numCin, String dateNaiss);

    infoClient  getInfoClientAndComptebyCLI (String numCli);

    UserAD authenticationAD(String login, String password);

    public List<CompteRealTimeDelta> getRTAccount(String numCli);

    DocumentHeader LeverOpposCompte(RequestDTO requestDTO, String accountNumber);

}

