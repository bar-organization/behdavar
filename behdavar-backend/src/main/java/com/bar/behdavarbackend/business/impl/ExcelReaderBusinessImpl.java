package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.business.transformer.InputExcelLendingTransformer;
import com.bar.behdavarbackend.business.transformer.InputExcelPersonTransformer;
import com.bar.behdavarbackend.dto.InputExcelDebtorDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import com.bar.behdavarbackend.dto.InputExcelGuarantorDto;
import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavarbackend.exception.BusinessException;
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
}
