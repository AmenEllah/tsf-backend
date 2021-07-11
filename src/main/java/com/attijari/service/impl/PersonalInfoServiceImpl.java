package com.attijari.service.impl;

import com.attijari.domain.PersonalInfo;
import com.attijari.service.AdressInfoService;
import com.attijari.service.FinancialInfoService;
import com.attijari.service.PersonalInfoService;
import com.attijari.service.dto.PersonalInfoDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.repository.PersonalInfoRepository;
import com.attijari.service.mapper.PersonalInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PersonalInfo}.
 */
@Service
@Transactional
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private final Logger log = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);
    private static final String ENTITY_NAME = "personalInfo";
    private final static int mailLastTime = 2 ;
    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper personalInfoMapper;
    private final AdressInfoService adressInfoService;
    private final FinancialInfoService financialInfoService;

    public PersonalInfoServiceImpl(PersonalInfoRepository personalInfoRepository, PersonalInfoMapper personalInfoMapper, AdressInfoService adressInfoService, FinancialInfoService financialInfoService) {
        this.personalInfoRepository = personalInfoRepository;
        this.personalInfoMapper = personalInfoMapper;
        this.adressInfoService = adressInfoService;
        this.financialInfoService = financialInfoService;
    }

    @Override
    public PersonalInfoDTO save(PersonalInfoDTO personalInfoDTO) {
        log.debug("Request to save PersonalInfo : {}", personalInfoDTO);
        if (personalInfoDTO != null && personalInfoDTO.getBirthday() != null && LocalDate.now().minusYears(18).isBefore(personalInfoDTO.getBirthday())) {
            throw new BadRequestAlertException("Allowed age should be greater then or equal to 18 years old", ENTITY_NAME, "invalidage");
        }
        PersonalInfo personalInfo = personalInfoMapper.toEntity(personalInfoDTO);
        personalInfo = personalInfoRepository.save(personalInfo);
        return personalInfoMapper.toDto(personalInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonalInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PersonalInfos");
        return personalInfoRepository.findAll(pageable)
            .map(personalInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfoDTO> findOne(Long id) {
        log.debug("Request to get PersonalInfo : {}", id);
        return personalInfoRepository.findById(id)
            .map(personalInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonalInfo : {}", id);
        Optional<PersonalInfoDTO> existingPersonalInfo = findOne(id);
        if (existingPersonalInfo.isPresent()) {
            if(existingPersonalInfo.get().getFinancialInfo() != null) {
                financialInfoService.delete(existingPersonalInfo.get().getFinancialInfo().getId());
            }
            if(existingPersonalInfo.get().getAdressInfo() != null) {
                adressInfoService.delete(existingPersonalInfo.get().getAdressInfo().getId());
            }
            personalInfoRepository.deleteById(id);
        }
    }
}
