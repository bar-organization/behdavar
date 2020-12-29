package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.InputExcelDebtorDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import com.bar.behdavarbackend.dto.InputExcelGuarantorDto;
import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
import com.bar.behdavarcommon.enumeration.ContractWeight;
import com.bar.behdavarcommon.enumeration.PhoneType;
import com.bar.behdavardatabase.entity.*;
import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.*;
import com.bar.behdavardatabase.repository.security.UserRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
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

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    GuarantorRepository guarantorRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CartableRepository cartableRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAmountRepository userAmountRepository;

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
        convert(inputExcelEntity.getId());
    }

    @Transactional
    public void convert(Long inputExcelId) {

        List<InputExcelLendingEntity> inputExcelLendingEntities = inputExcelLendingRepository.findByInputExcelId(inputExcelId);
        if (!inputExcelLendingEntities.isEmpty()) {
            inputExcelLendingEntities.forEach(excelLendingEntity -> {
                ContractEntity contractEntity = contractRepository.findByContractNumber(excelLendingEntity.getContractNumber());
                UserEntity userExpertEntity = userRepository.findByCode(excelLendingEntity.getExpertCode());
                if (contractEntity != null) {
                    if (contractEntity.getLending() != null) {
                        LendingEntity lendingEntity = lendingRepository.findById(contractEntity.getLending().getId()).orElse(null);
                        if (lendingEntity != null ){
                            lendingEntity.setMasterAmount(excelLendingEntity.getDebtAmount());
                            lendingEntity.setDefferedAmount(excelLendingEntity.getInstallmentAmount());
                            lendingEntity.setDefferedCount(excelLendingEntity.getInstallmentCount());
                            lendingEntity.setRemainDebtAmount(excelLendingEntity.getRemainDebtAmount());
                            lendingEntity.setDifferedInstallmentCount(excelLendingEntity.getDifferedInstallmentCount());
                            lendingRepository.save(lendingEntity);
                        }
                    }
                    CartableEntity cartableEntity = cartableRepository.findByContractIdAndActive(contractEntity.getId(), true).orElse(null);
                    if (cartableEntity != null) {
                        if (!cartableEntity.getReceiver().getCode().equals(excelLendingEntity.getExpertCode())) {
                            cartableEntity.setActive(false);
                            UserAmountEntity oldUserAmount = userAmountRepository.findByUserId(cartableEntity.getReceiver().getId()).orElse(null);
                            oldUserAmount.setTotalAmount(oldUserAmount.getTotalAmount().min(excelLendingEntity.getRemainDebtAmount()));
                            userAmountRepository.save(oldUserAmount);
                            UserEntity newReceiver = userRepository.findByCode(excelLendingEntity.getExpertCode());
                            if (newReceiver == null) {
                                throw new BusinessException("user.with.code.not.found", excelLendingEntity.getExpertCode());
                            }
                            UserAmountEntity userAmountEntity = userAmountRepository.findByUserId(newReceiver.getId()).orElse(null);
                            if (userAmountEntity == null) {
                                userAmountEntity = new UserAmountEntity();
                            }
                            userAmountEntity.setTotalAmount(userAmountEntity.getTotalAmount().add(excelLendingEntity.getRemainDebtAmount()));
                            contractEntity.setContractStatus(ContractStatus.RAW);
                            contractRepository.save(contractEntity);
                            cartableRepository.save(cartableEntity);
                            CartableEntity newCartableEntity = new CartableEntity();
                            newCartableEntity.setActive(true);
                            newCartableEntity.setContract(contractEntity);
                            newCartableEntity.setReceiver(userExpertEntity);
                            UserEntity sender = UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId());
                            newCartableEntity.setSender(sender);
                            cartableRepository.save(newCartableEntity);
                        }
                    } else {
                        cartableEntity = new CartableEntity();
                        cartableEntity.setActive(true);
                        cartableEntity.setContract(contractEntity);
                        cartableEntity.setReceiver(userExpertEntity);
                        UserEntity sender = UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId());
                        cartableEntity.setSender(sender);
                        cartableRepository.save(cartableEntity);
                    }
                    return;
                }

                // grantors
                contractEntity = new ContractEntity();
                contractEntity.setContractNumber(excelLendingEntity.getContractNumber());
                contractEntity.setContractStatus(ContractStatus.RAW);
                if (excelLendingEntity.getMachine() != null) {
                    contractEntity.setContractType(ContractType.CARS);
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductName(excelLendingEntity.getMachine());
                    productEntity.setProductPlate(excelLendingEntity.getPlaqueNumber());
                    productEntity.setProductShasiNumber(excelLendingEntity.getShasiNumber());
                    productRepository.save(productEntity);
                    contractEntity.setProduct(productEntity);
                } else {
                    contractEntity.setContractType(ContractType.BANKS);
                }

                LendingEntity lendingEntity = new LendingEntity();
                lendingEntity.setMasterAmount(excelLendingEntity.getDebtAmount());
                lendingEntity.setDefferedAmount(excelLendingEntity.getInstallmentAmount());
                lendingEntity.setRemainDebtAmount(excelLendingEntity.getRemainDebtAmount());
                lendingEntity.setDefferedCount(excelLendingEntity.getInstallmentCount());
                lendingEntity.setDifferedInstallmentCount(excelLendingEntity.getDifferedInstallmentCount());
                lendingRepository.save(lendingEntity);
                contractEntity.setLending(lendingEntity);
                contractEntity.setContractWeight(ContractWeight.LEVEL1);
                //condition for contractWeight
            /*    if (excelLendingEntity.getAmount() != null) {

                }*/
                InputExcelGuarantorEntity byInputExcelIdAndContractNumber = inputExcelGuarantorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (byInputExcelIdAndContractNumber != null) {
                    PersonEntity personEntity = new PersonEntity();
                    personEntity.setFullName(byInputExcelIdAndContractNumber.getLastName());
                    personEntity.setFatherName(byInputExcelIdAndContractNumber.getFatherName());
                    personEntity.setNationalCode(byInputExcelIdAndContractNumber.getNationalCode());
                    Long personId = personBusiness.save(personEntity);
                    personEntity.setId(personId);

                    if (byInputExcelIdAndContractNumber.getMobile1() != null && !(("-").equals(byInputExcelIdAndContractNumber.getMobile1()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getMobile1());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getMobile2() != null && !(("-").equals(byInputExcelIdAndContractNumber.getMobile2()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getMobile2());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getTel1() != null && !(("-").equals(byInputExcelIdAndContractNumber.getTel1()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getTel1());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getTel2() != null && !(("-").equals(byInputExcelIdAndContractNumber.getTel2()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(byInputExcelIdAndContractNumber.getTel2());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (byInputExcelIdAndContractNumber.getAddress() != null && !(("-").equals(byInputExcelIdAndContractNumber.getAddress()))) {
                        AddressEntity addressEntity = new AddressEntity();
                        addressEntity.setPerson(personEntity);
                        addressEntity.setDescription(byInputExcelIdAndContractNumber.getAddress());
                        addressRepository.save(addressEntity);
                    }
                    GuarantorEntity guarantorEntity = new GuarantorEntity();
                    guarantorEntity.setContract(contractEntity);
                    guarantorEntity.setPerson(personEntity);
                    contractRepository.save(contractEntity);
                    guarantorRepository.save(guarantorEntity);

                    if (excelLendingEntity.getExpertCode() != null && !(("-").equals(excelLendingEntity.getExpert()))) {
                        if (userExpertEntity != null) {
                            CartableEntity cartableEntity = new CartableEntity();
                            cartableEntity.setActive(true);
                            cartableEntity.setContract(contractEntity);
                            cartableEntity.setReceiver(userExpertEntity);
                            UserEntity sender = UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId());
                            cartableEntity.setSender(sender);
                            cartableRepository.save(cartableEntity);
                            UserAmountEntity userAmountEntity = userAmountRepository.findByUserId(userExpertEntity.getId()).orElse(null);
                            if (userAmountEntity == null) {
                                userAmountEntity = new UserAmountEntity();
                                userAmountEntity.setTotalAmount(new BigDecimal("0"));
                                userAmountEntity.setReceiveAmount(new BigDecimal("0"));
                                userAmountEntity.setUser(userExpertEntity);
                            }
                            userAmountEntity.setTotalAmount(userAmountEntity.getTotalAmount().add(excelLendingEntity.getRemainDebtAmount()));
                            userAmountRepository.save(userAmountEntity);
                        } else {
                            throw new BusinessException("user.with.code.not.found", excelLendingEntity.getExpertCode());
                        }
                    } else {
                        throw new BusinessException("contract.with.contractNumber.not.have.expertCode", excelLendingEntity.getContractNumber());
                    }


                }
                // end of grantor
                //debtors
                InputExcelDebtorEntity debtorEntities = inputExcelDebtorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());
                if (debtorEntities != null) {
                    PersonEntity personEntity = new PersonEntity();
                    personEntity.setFullName(debtorEntities.getLastName());
                    personEntity.setFatherName(debtorEntities.getFatherName());
                    personEntity.setNationalCode(debtorEntities.getNationalCode());
                    personEntity.setId(personBusiness.save(personEntity));
                    personBusiness.save(personEntity);

                    if (debtorEntities.getMobile1() != null && !(("-").equals(debtorEntities.getMobile1()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getMobile1());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (debtorEntities.getMobile2() != null && !(("-").equals(debtorEntities.getMobile2()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getMobile2());
                        contactEntity.setPhoneType(PhoneType.MOBILE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (debtorEntities.getTel1() != null && !(("-").equals(debtorEntities.getTel1()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getTel1());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (debtorEntities.getTel2() != null && !(("-").equals(debtorEntities.getTel2()))) {
                        ContactEntity contactEntity = new ContactEntity();
                        contactEntity.setNumber(debtorEntities.getTel2());
                        contactEntity.setPhoneType(PhoneType.PHONE);
                        contactEntity.setPerson(personEntity);
                        contactRepository.save(contactEntity);
                    }

                    if (debtorEntities.getAddress() != null && !(("-").equals(debtorEntities.getAddress()))) {
                        AddressEntity addressEntity = new AddressEntity();
                        addressEntity.setPerson(personEntity);
                        addressEntity.setDescription(debtorEntities.getAddress());
                        addressRepository.save(addressEntity);
                    }
                    CustomerEntity customerEntity = new CustomerEntity();
                    customerEntity.setContract(contractEntity);
                    customerEntity.setPerson(personEntity);
                    customerRepository.save(customerEntity);
                    //end of debtor
                }
            });
        }
    }
}