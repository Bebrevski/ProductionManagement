package com.productionmanagement.service;

import com.productionmanagement.domain.models.nomenclature.NomenclatureMetadataModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.repository.NomenclatureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NomenclatureServiceImpl implements NomenclatureService{
    private final NomenclatureRepository nomenclatureRepository;
    private final ModelMapper mapper;

    @Autowired
    public NomenclatureServiceImpl(NomenclatureRepository nomenclatureRepository, ModelMapper mapper) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.mapper = mapper;
    }

    @Override
    public OperationResult<List<NomenclatureMetadataModel>> getNomenclatureHeaders() {
        List<NomenclatureMetadataModel> nomenclatureHeaders =  this.nomenclatureRepository.findAll()
                .stream()
                .map(n -> mapper.map(n, NomenclatureMetadataModel.class))
                .collect(Collectors.toList());

        return OperationResult.Success(nomenclatureHeaders, "Успешно заредени данни", null);
    }
}
