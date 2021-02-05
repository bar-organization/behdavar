package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.impl.PersonBusinessImpl;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.*;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarcommon.AuthorityConstant;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
@Validated
public class ExcelRest {

    @Autowired
    ExcelReaderBusiness excelReaderBusiness;

    @Autowired
    PersonRepository personRepository;


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
    CartableRepository cartableRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAmountRepository userAmountRepository;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.UPLOAD_EXCEL + "')")
    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<CartableDto> findById(@RequestBody @Valid InputExcelDto dto) {
//        excelReaderBusiness.readAndSave(dto);
//        personRepository.convertArabicLetters();
        byte[] bytes = Base64Utils.decodeFromString(dto.getContent());
        InputExcelEntity inputExcelEntity = new InputExcelEntity();
        inputExcelEntity.setContent(bytes);
        inputExcelEntity.setFileName(dto.getFileName());
        inputExcelEntity = inputExcelRepository.save(inputExcelEntity);
        InputExcelEntity finalInputExcelEntity = inputExcelEntity;


        List<InputExcelLendingDto> inputExcelLendingDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelLendingDto.class);
        if (!CollectionUtils.isEmpty(inputExcelLendingDtos)) {
            System.out.println("inputExcelLendingDtos" + inputExcelLendingDtos);
            List<InputExcelLendingEntity> inputExcelLendingEntities = new ArrayList<>();
            inputExcelLendingDtos.forEach(inputExcelLendingDto -> {
                System.out.println("inputExcelLendingDto" + inputExcelLendingDto);
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
            System.out.println("inputExcelGuarantorDtos" + inputExcelGuarantorDtos);
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
            System.out.println("inputExcelDebtorDtos" + inputExcelDebtorDtos);
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @org.springframework.transaction.annotation.Transactional
    public void convert(Long inputExcelId) {

        List<InputExcelLendingEntity> inputExcelLendingEntities = inputExcelLendingRepository.findByInputExcelId(inputExcelId);
        if (!inputExcelLendingEntities.isEmpty()) {
            inputExcelLendingEntities.forEach(excelLendingEntity -> {
                System.out.println("excelLendingEntity           &&&&&&&&&&     " + excelLendingEntity.getContractNumber());
                try {
                    ContractEntity contractEntity = contractRepository.findByContractNumber(excelLendingEntity.getContractNumber());
                    System.out.println("contractEntity           &&&&&&&&&&     " + contractEntity.getContractNumber());
                    UserEntity userExpertEntity = userRepository.findByCode(excelLendingEntity.getExpertCode());
                    if (contractEntity != null) {
                        contractRepeated(excelLendingEntity, contractEntity, userExpertEntity);
                        return;
                    }

                    // contract
                    contractEntity = new ContractEntity();
                    contractEntity.setContractNumber(excelLendingEntity.getContractNumber());
                    contractEntity.setContractStatus(ContractStatus.RAW);
                    //product
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
                    setAndSaveLending(excelLendingEntity, lendingEntity);

                    contractEntity.setLending(lendingEntity);
                    contractEntity.setContractWeight(ContractWeight.LEVEL1);

                    InputExcelGuarantorEntity byInputExcelIdAndContractNumber = inputExcelGuarantorRepository.findByInputExcelIdAndContractNumber(inputExcelId, excelLendingEntity.getContractNumber());

                    // guarantor
                    if (byInputExcelIdAndContractNumber != null) {
                        System.out.println("byInputExcelIdAndContractNumber" + byInputExcelIdAndContractNumber);
                        PersonEntity personEntity = new PersonEntity();
                        Long personId = setAndSavePerson(byInputExcelIdAndContractNumber, personEntity);
                        personEntity.setId(personId);

                        if (byInputExcelIdAndContractNumber.getMobile1() != null && !(("-").equals(byInputExcelIdAndContractNumber.getMobile1()))) {
                            saveMobileContact(personEntity, byInputExcelIdAndContractNumber.getMobile1(), PhoneType.MOBILE);
                        }

                        if (byInputExcelIdAndContractNumber.getMobile2() != null && !(("-").equals(byInputExcelIdAndContractNumber.getMobile2()))) {
                            saveMobileContact(personEntity, byInputExcelIdAndContractNumber.getMobile2(), PhoneType.MOBILE);
                        }

                        if (byInputExcelIdAndContractNumber.getTel1() != null && !(("-").equals(byInputExcelIdAndContractNumber.getTel1()))) {
                            saveMobileContact(personEntity, byInputExcelIdAndContractNumber.getTel1(), PhoneType.PHONE);
                        }

                        if (byInputExcelIdAndContractNumber.getTel2() != null && !(("-").equals(byInputExcelIdAndContractNumber.getTel2()))) {
                            saveMobileContact(personEntity, byInputExcelIdAndContractNumber.getTel2(), PhoneType.PHONE);
                        }

                        if (byInputExcelIdAndContractNumber.getAddress() != null && !(("-").equals(byInputExcelIdAndContractNumber.getAddress()))) {
                            setAndSaveAddress(personEntity, byInputExcelIdAndContractNumber.getAddress());
                        }
                        GuarantorEntity guarantorEntity = new GuarantorEntity();
                        guarantorEntity.setContract(contractEntity);
                        guarantorEntity.setPerson(personEntity);
                        contractRepository.save(contractEntity);
                        guarantorRepository.save(guarantorEntity);

                        if (excelLendingEntity.getExpertCode() != null && !(("-").equals(excelLendingEntity.getExpert()))) {
                            if (userExpertEntity != null) {
                                setAndSaveCartableWhenForFirst(contractEntity, userExpertEntity);
                                UserAmountEntity userAmountEntity = userAmountRepository.findByUserId(userExpertEntity.getId()).orElse(null);
                                if (userAmountEntity == null) {
                                    userAmountEntity = setFirstUserAmount(userExpertEntity);
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
                    // end of guarantor

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
                            saveMobileContact(personEntity, debtorEntities.getMobile1(), PhoneType.MOBILE);
                        }

                        if (debtorEntities.getMobile2() != null && !(("-").equals(debtorEntities.getMobile2()))) {
                            saveMobileContact(personEntity, debtorEntities.getMobile2(), PhoneType.MOBILE);
                        }

                        if (debtorEntities.getTel1() != null && !(("-").equals(debtorEntities.getTel1()))) {
                            saveMobileContact(personEntity, debtorEntities.getTel1(), PhoneType.PHONE);
                        }

                        if (debtorEntities.getTel2() != null && !(("-").equals(debtorEntities.getTel2()))) {
                            saveMobileContact(personEntity, debtorEntities.getTel2(), PhoneType.PHONE);
                        }

                        if (debtorEntities.getAddress() != null && !(("-").equals(debtorEntities.getAddress()))) {
                            setAndSaveAddress(personEntity, debtorEntities.getAddress());
                        }
                        CustomerEntity customerEntity = new CustomerEntity();
                        customerEntity.setContract(contractEntity);
                        customerEntity.setPerson(personEntity);
                        customerRepository.save(customerEntity);
                        //end of debtor
                    }
                } catch (Exception e) {
                    throw new BusinessException("error.in.excel.with.record : " + excelLendingEntity.getContractNumber() );
                }
            });
        }
    }

    private void setAndSaveAddress(PersonEntity personEntity, String address) {
        System.out.println("setAndSaveAddress   " + personEntity + address);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPerson(personEntity);
        addressEntity.setDescription(address);
        addressRepository.save(addressEntity);
    }

    private UserAmountEntity setFirstUserAmount(UserEntity userExpertEntity) {
        System.out.println("setFirstUserAmount" + userExpertEntity);
        UserAmountEntity userAmountEntity;
        userAmountEntity = new UserAmountEntity();
        userAmountEntity.setTotalAmount(new BigDecimal("0"));
        userAmountEntity.setReceiveAmount(new BigDecimal("0"));
        userAmountEntity.setUser(userExpertEntity);
        return userAmountEntity;
    }

    private Long setAndSavePerson(InputExcelGuarantorEntity byInputExcelIdAndContractNumber, PersonEntity
            personEntity) {
        System.out.println("setAndSavePerson   " + byInputExcelIdAndContractNumber + personEntity);
        personEntity.setFullName(byInputExcelIdAndContractNumber.getLastName());
        personEntity.setFatherName(byInputExcelIdAndContractNumber.getFatherName());
        personEntity.setNationalCode(byInputExcelIdAndContractNumber.getNationalCode());
        return personBusiness.save(personEntity);
    }

    private void saveMobileContact(PersonEntity personEntity, String tel2, PhoneType phone) {
        System.out.println("saveMobileContact   " + personEntity + tel2 + phone);
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setNumber(tel2);
        contactEntity.setPhoneType(phone);
        contactEntity.setPerson(personEntity);
        contactRepository.save(contactEntity);
    }

    private void contractRepeated(InputExcelLendingEntity excelLendingEntity, ContractEntity
            contractEntity, UserEntity userExpertEntity) {
        CartableEntity cartableEntity = cartableRepository.findByContractIdAndActive(contractEntity.getId(), true).orElse(null);
        if (cartableEntity != null) {
            if (!cartableEntity.getReceiver().getCode().equals(excelLendingEntity.getExpertCode())) {
                setAndSaveCartableWhenExpertChange(excelLendingEntity, contractEntity, userExpertEntity, cartableEntity);
            }
        } else {
            setAndSaveCartableWhenForFirst(contractEntity, userExpertEntity);
        }
        if (contractEntity.getLending() != null) {
            LendingEntity lendingEntity = lendingRepository.findById(contractEntity.getLending().getId()).orElse(null);
            if (lendingEntity != null) {
                setAndSaveLending(excelLendingEntity, lendingEntity);
            }
        }
    }

    private void setAndSaveCartableWhenForFirst(ContractEntity contractEntity, UserEntity userExpertEntity) {
        CartableEntity cartableEntity = new CartableEntity();
        cartableEntity.setActive(true);
        cartableEntity.setContract(contractEntity);
        cartableEntity.setReceiver(userExpertEntity);
        UserEntity sender = UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId());
        cartableEntity.setSender(sender);
        cartableRepository.save(cartableEntity);
    }

    private void setAndSaveCartableWhenExpertChange(InputExcelLendingEntity excelLendingEntity, ContractEntity
            contractEntity, UserEntity userExpertEntity, CartableEntity cartableEntity) {
        cartableEntity.setActive(false);
        UserAmountEntity oldUserAmount = userAmountRepository.findByUserId(cartableEntity.getReceiver().getId()).orElse(null);
        oldUserAmount.setTotalAmount(oldUserAmount.getTotalAmount().min(contractEntity.getLending().getRemainDebtAmount()));
        userAmountRepository.save(oldUserAmount);
        UserEntity newReceiver = userRepository.findByCode(excelLendingEntity.getExpertCode());
        if (newReceiver == null) {
            throw new BusinessException("user.with.code.not.found", excelLendingEntity.getExpertCode());
        }
        UserAmountEntity userAmountEntity = userAmountRepository.findByUserId(newReceiver.getId()).orElse(null);
        if (userAmountEntity == null) {
            setFirstUserAmount(userExpertEntity);
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

    private void setAndSaveLending(InputExcelLendingEntity excelLendingEntity, LendingEntity lendingEntity) {
        lendingEntity.setMasterAmount(excelLendingEntity.getDebtAmount());
        lendingEntity.setDefferedAmount(excelLendingEntity.getInstallmentAmount());
        lendingEntity.setDefferedCount(excelLendingEntity.getInstallmentCount());
        lendingEntity.setRemainDebtAmount(excelLendingEntity.getRemainDebtAmount());
        lendingEntity.setLateFees(excelLendingEntity.getLateFees());
        lendingEntity.setDifferedInstallmentCount(excelLendingEntity.getDifferedInstallmentCount());
        lendingRepository.save(lendingEntity);
    }
}
