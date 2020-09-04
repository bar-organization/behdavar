package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.dto.InputExcelDebtorDto;
import com.bar.behdavarbackend.dto.InputExcelGuarantorDto;
import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavarbackend.dto.UploadExcelDto;
import com.bar.behdavardatabase.entity.InputExcelDebtorEntity;
import com.bar.behdavardatabase.entity.InputExcelEntity;
import com.bar.behdavardatabase.entity.InputExcelGuarantorEntity;
import com.bar.behdavardatabase.entity.InputExcelLendingEntity;
import com.bar.behdavardatabase.repository.InputExcelDebtorRepository;
import com.bar.behdavardatabase.repository.InputExcelGuarantorRepository;
import com.bar.behdavardatabase.repository.InputExcelLendingRepository;
import com.bar.behdavardatabase.repository.InputExcelRepository;
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

    @Override
    @Transactional
    public void readAndSave(UploadExcelDto dto) {
        byte[] bytes = Base64Utils.decodeFromString(dto.getContent());
        InputExcelEntity inputExcelEntity = new InputExcelEntity();
        inputExcelEntity.setContent(bytes);
        inputExcelEntity.setFileName(dto.getFileName());
        inputExcelRepository.save(inputExcelEntity);


        List<InputExcelLendingDto> inputExcelLendingDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelLendingDto.class);
        if (!CollectionUtils.isEmpty(inputExcelLendingDtos)) {
            List<InputExcelLendingEntity> inputExcelLendingEntities = new ArrayList<>();
            inputExcelLendingDtos.forEach(inputExcelLendingDto -> inputExcelLendingEntities.add(InputExcelLendingTransformer.DTO_TO_ENTITY(inputExcelLendingDto, new InputExcelLendingEntity())));
            inputExcelLendingRepository.saveAll(inputExcelLendingEntities);
        }

        List<InputExcelGuarantorDto> inputExcelGuarantorDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelGuarantorDto.class);
        if (!CollectionUtils.isEmpty(inputExcelGuarantorDtos)) {
            List<InputExcelGuarantorEntity> inputExcelLendingEntities = new ArrayList<>();
            inputExcelGuarantorDtos.forEach(excelGuarantorDto -> inputExcelLendingEntities.add((InputExcelGuarantorEntity) InputExcelPersonTransformer.DTO_TO_ENTITY(excelGuarantorDto, new InputExcelGuarantorEntity())));
            inputExcelGuarantorRepository.saveAll(inputExcelLendingEntities);
        }

        List<InputExcelDebtorDto> inputExcelDebtorDtos = Poiji.fromExcel(new ByteArrayInputStream(bytes), PoijiExcelType.XLSX, InputExcelDebtorDto.class);
        if (!CollectionUtils.isEmpty(inputExcelDebtorDtos)) {
            List<InputExcelDebtorEntity> inputExcelDebtorEntities = new ArrayList<>();
            inputExcelDebtorDtos.forEach(inputExcelDebtorDto -> inputExcelDebtorEntities.add((InputExcelDebtorEntity) InputExcelPersonTransformer.DTO_TO_ENTITY(inputExcelDebtorDto, new InputExcelDebtorEntity())));
            inputExcelDebtorRepository.saveAll(inputExcelDebtorEntities);
        }


    }
}
