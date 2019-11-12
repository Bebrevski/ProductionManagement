package com.productionmanagement.service;

import com.productionmanagement.domain.entities.Nomenclature;
import com.productionmanagement.domain.models.nomenclature.NomenclatureModel;
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
    public OperationResult<List<NomenclatureModel>> getNomenclatureHeaders() {
        List<Nomenclature> nomenclatureHeaders =  this.nomenclatureRepository.getNomenclatureHeaders();
        var some = nomenclatureHeaders
                .stream()
                .map(n -> mapper.map(n, NomenclatureModel.class))
                .collect(Collectors.toList());

        return OperationResult.Success(some, "Успешно заредени данни", null);
    }
}
