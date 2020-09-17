package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.dto.InputExcelDebtorDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import com.bar.behdavarbackend.dto.InputExcelGuarantorDto;
import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
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

    @Autowired
    LendingRepository lendingRepository;

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

                if (contractEntity != null) {
                    LendingEntity lendingEntity = lendingRepository.findByContractId(contractEntity.getId());
                    lendingEntity.setMasterAmount(excelLendingEntity.getDebtAmount());
                    lendingEntity.setDefferedAmount(excelLendingEntity.getInstallmentAmount());
                    lendingEntity.setDefferedCount(excelLendingEntity.getInstallmentCount());
                    lendingEntity.setDifferedInstallmentCount(excelLendingEntity.getDifferedInstallmentCount());
                    lendingEntity.setDifferedInstallmentCount(excelLendingEntity.getDifferedInstallmentCount());
                    return;
                }

                // grantors
                contractEntity = new ContractEntity();
                contractEntity.setContractNumber(excelLendingEntity.getContractNumber());
                contractEntity.setContractStatus(ContractStatus.AVAILABLE);
                if (excelLendingEntity.getMachine() != null){
                    contractEntity.setContractType(ContractType.CARS);
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductName(excelLendingEntity.getMachine());
                    productEntity.setProductPlate(excelLendingEntity.getPlaqueNumber());
                    productEntity.setProductShasiNumber(excelLendingEntity.getShasiNumber());
                } else {
                    contractEntity.setContractType(ContractType.BANKS);
                }
                //condition for contractWeight
            /*    if (excelLendingEntity.getAmount() != null) {

                }*/
                InputExcelGuarantorEntity byInputExcelIdAndContractNumber = inputExcelGuarantorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (byInputExcelIdAndContractNumber != null) {
                    PersonEntity personEntity = new PersonEntity();
                    personEntity.setFirstName(byInputExcelIdAndContractNumber.getName());
                    personEntity.setLastName(byInputExcelIdAndContractNumber.getLastName());
                    personEntity.setFullName(byInputExcelIdAndContractNumber.getName() + " " + byInputExcelIdAndContractNumber.getLastName());
                    personEntity.setFatherName(byInputExcelIdAndContractNumber.getFatherName());
                    personEntity.setNationalCode(byInputExcelIdAndContractNumber.getNationalCode());
                    personEntity.setId(personBusiness.save(personEntity));

                    if (byInputExcelIdAndContractNumber.getMobile1() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getMobile1());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getMobile2() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getMobile2());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getTel1() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getTel1());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getTel2() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getTel2());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getAddress() != null) {
                        AddressEntity addressEntity = new AddressEntity();
                        addressEntity.setPerson(personEntity);
                        addressEntity.setDescription(byInputExcelIdAndContractNumber.getAddress());
                    }
                    GuarantorEntity guarantorEntity = new GuarantorEntity();
                    guarantorEntity.setContract(contractEntity);
                    guarantorEntity.setPerson(personEntity);
                }
                // end of grantor
                //debtors
                InputExcelDebtorEntity debtorEntities = inputExcelDebtorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (debtorEntities != null) {
                    PersonEntity personEntity = new PersonEntity();
                    personEntity.setFirstName(debtorEntities.getName());
                    personEntity.setLastName(debtorEntities.getLastName());
                    personEntity.setFullName(debtorEntities.getName() + " " + debtorEntities.getLastName());
                    personEntity.setFatherName(debtorEntities.getFatherName());
                    personEntity.setNationalCode(debtorEntities.getNationalCode());
                    personEntity.setId(personBusiness.save(personEntity));

                    if (debtorEntities.getMobile1() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getMobile1());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (debtorEntities.getMobile2() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getMobile2());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (debtorEntities.getTel1() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getTel1());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (debtorEntities.getTel2() != null) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getTel2());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                    }

                    if (debtorEntities.getAddress() != null) {
                        AddressEntity addressEntity = new AddressEntity();
                        addressEntity.setPerson(personEntity);
                        addressEntity.setDescription(debtorEntities.getAddress());
                    }
                    CustomerEntity customerEntity = new CustomerEntity();
                    customerEntity.setContract(contractEntity);
                    customerEntity.setPerson(personEntity);
                    //end of debtor
                }
            });
        }
    }
}