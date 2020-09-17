package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.dto.InputExcelDebtorDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import com.bar.behdavarbackend.dto.InputExcelGuarantorDto;
import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarcommon.enumeration.PhoneType;
import com.bar.behdavardatabase.entity.*;
import com.bar.behdavardatabase.repository.*;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service(ExcelReaderBusinessImpl.BEAN_NAME)
public class ExcelReaderBusinessImpl implements ExcelReaderBusiness {
    public static final String BEAN_NAME = "ExcelReaderBusinessImpl";

    @Autowired
    InputExcelLendingRepository inputExcelLendingRepository;

    @Autowired
    InputExcelGuarantorRepository inputExcelGuarantorRepository;

    @Autowired
    InputExcelDebtorRepository inputExcelDebtorRepository;

    @Autowired
    InputExcelRepository inputExcelRepository;

    @Autowired
    PersonBusinessImpl personBusiness;


    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContractRepository contractRepository;

    @Override
    @Transactional
    public void readAndSave(InputExcelDto dto) {
        byte[] bytes = Base64Utils.decodeFromString(dto.getContent());
        InputExcelEntity inputExcelEntity = new InputExcelEntity();
        inputExcelEntity.setContent(bytes);
        inputExcelEntity.setFileName(dto.getFileName());
        inputExcelEntity = inputExcelRepository.save(inputExcelEntity);
        InputExcelEntity finalInputExcelEntity = inputExcelEntity;


        List<InputExcelLendingDto> inputExcelLendingDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelLendingDto.class);
        if (!CollectionUtils.isEmpty(inputExcelLendingDtos)) {
            List<InputExcelLendingEntity> inputExcelLendingEntities = new ArrayList<>();
            inputExcelLendingDtos.forEach(inputExcelLendingDto -> {
                InputExcelLendingEntity entity = InputExcelLendingTransformer.dtoToEntity(inputExcelLendingDto, new InputExcelLendingEntity());
                entity.setInputExcel(finalInputExcelEntity);
                inputExcelLendingEntities.add(entity);
            });
            inputExcelLendingRepository.saveAll(inputExcelLendingEntities);
        } else {
            throw new BusinessException("invalid.input.excel.file");
        }

        List<InputExcelGuarantorDto> inputExcelGuarantorDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelGuarantorDto.class);
        if (!CollectionUtils.isEmpty(inputExcelGuarantorDtos)) {
            List<InputExcelGuarantorEntity> inputExcelGuarantorEntities = new ArrayList<>();
            inputExcelGuarantorDtos.forEach(excelGuarantorDto -> {
                InputExcelGuarantorEntity entity = (InputExcelGuarantorEntity) InputExcelPersonTransformer.dtoToEntity(excelGuarantorDto, new InputExcelGuarantorEntity());
                entity.setInputExcel(finalInputExcelEntity);
                inputExcelGuarantorEntities.add(entity);
            });
            inputExcelGuarantorRepository.saveAll(inputExcelGuarantorEntities);
        } else {
            throw new BusinessException("invalid.input.excel.file");
        }

        List<InputExcelDebtorDto> inputExcelDebtorDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelDebtorDto.class);
        if (!CollectionUtils.isEmpty(inputExcelDebtorDtos)) {
            List<InputExcelDebtorEntity> inputExcelDebtorEntities = new ArrayList<>();
            inputExcelDebtorDtos.forEach(inputExcelDebtorDto -> {
                InputExcelDebtorEntity entity = (InputExcelDebtorEntity) InputExcelPersonTransformer.dtoToEntity(inputExcelDebtorDto, new InputExcelDebtorEntity());
                entity.setInputExcel(finalInputExcelEntity);
                inputExcelDebtorEntities.add(entity);
            });
            inputExcelDebtorRepository.saveAll(inputExcelDebtorEntities);
        } else {
            throw new BusinessException("invalid.input.excel.file");
        }


    }

    @Transactional
    public void convert(Long inputExcelId) {

        List<InputExcelLendingEntity> inputExcelLendingEntities = inputExcelLendingRepository.findByInputExcelId(inputExcelId);
        if (!inputExcelLendingEntities.isEmpty()) {
            inputExcelLendingEntities.forEach(excelLendingEntity -> {
                ContractEntity contractEntity = contractRepository.findByContractNumber(excelLendingEntity.getContractNumber());

                if (contractEntity == null) {
                    LendingEntity lendingEntity = new LendingEntity();
                    //ُ

                }

                // grantors
                List<InputExcelGuarantorEntity> byInputExcelIdAndContractNumber = inputExcelGuarantorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (!CollectionUtils.isEmpty(byInputExcelIdAndContractNumber)) {
                    byInputExcelIdAndContractNumber.forEach(inputExcelGuarantorEntity -> {
                        PersonEntity personEntity = new PersonEntity();
                        personEntity.setFirstName(inputExcelGuarantorEntity.getName());
                        personEntity.setLastName(inputExcelGuarantorEntity.getLastName());
                        personEntity.setFullName(inputExcelGuarantorEntity.getName() + " " + inputExcelGuarantorEntity.getLastName());
                        personEntity.setFatherName(inputExcelGuarantorEntity.getFatherName());
                        personEntity.setNationalCode(inputExcelGuarantorEntity.getNationalCode());
                        personEntity.setId(personBusiness.save(personEntity));

                        if (inputExcelGuarantorEntity.getMobile1() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getMobile1());
                            contactEntity.setPhoneType(PhoneType.MOBILE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getMobile2() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getMobile2());
                            contactEntity.setPhoneType(PhoneType.MOBILE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getTel1() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getTel1());
                            contactEntity.setPhoneType(PhoneType.PHONE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getTel2() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getTel2());
                            contactEntity.setPhoneType(PhoneType.PHONE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getAddress() != null) {
                            AddressEntity addressEntity = new AddressEntity();
                            addressEntity.setPerson(personEntity);
                            addressEntity.setDescription(inputExcelGuarantorEntity.getAddress());
                        }

                    });
                }
                // end of grantor
                //debtors
                List<InputExcelDebtorEntity> debtorEntities = inputExcelDebtorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (!CollectionUtils.isEmpty(debtorEntities)) {
                    debtorEntities.forEach(inputExcelGuarantorEntity -> {
                        PersonEntity personEntity = new PersonEntity();
                        personEntity.setFirstName(inputExcelGuarantorEntity.getName());
                        personEntity.setLastName(inputExcelGuarantorEntity.getLastName());
                        personEntity.setFullName(inputExcelGuarantorEntity.getName() + " " + inputExcelGuarantorEntity.getLastName());
                        personEntity.setFatherName(inputExcelGuarantorEntity.getFatherName());
                        personEntity.setNationalCode(inputExcelGuarantorEntity.getNationalCode());
                        personEntity.setId(personBusiness.save(personEntity));

                        if (inputExcelGuarantorEntity.getMobile1() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getMobile1());
                            contactEntity.setPhoneType(PhoneType.MOBILE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getMobile2() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getMobile2());
                            contactEntity.setPhoneType(PhoneType.MOBILE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getTel1() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getTel1());
                            contactEntity.setPhoneType(PhoneType.PHONE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getTel2() != null) {
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.setNumber(inputExcelGuarantorEntity.getTel2());
                            contactEntity.setPhoneType(PhoneType.PHONE);
                            contactEntity.setPerson(personEntity);
                        }

                        if (inputExcelGuarantorEntity.getAddress() != null) {
                            AddressEntity addressEntity = new AddressEntity();
                            addressEntity.setPerson(personEntity);
                            addressEntity.setDescription(inputExcelGuarantorEntity.getAddress());
                        }

                    });
                }
                //end of debtor
            });


        }


    }
}
