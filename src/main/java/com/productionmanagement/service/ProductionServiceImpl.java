package com.productionmanagement.service;

import com.productionmanagement.domain.entities.Production;
import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.helpers.ResultType;
import com.productionmanagement.repository.ProductionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProductionServiceImpl(ProductionRepository productionRepository, ModelMapper mapper) {
        this.productionRepository = productionRepository;
        this.mapper = mapper;
    }

    @Override
    public OperationResult<ProductionModel> submitProduction(ProductionModel productionModel) {
        try {
            OperationResult<ProductionModel> result = validateProduction(productionModel);

            if (result.Type != ResultType.Success) {
                return result;
            } else {
                boolean exists = productionExists(productionModel);

                if (exists) {
                    //UPDATE
                    return null;
                } else {
                    //CREATE
                    result = validateUniqueness(productionModel);

                    if (result.Type != ResultType.Success) return result;

                    return createProduction(productionModel);
                }
            }
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    private OperationResult<ProductionModel> createProduction(ProductionModel productionModel) {
        Production entity = mapper.map(productionModel, Production.class);

        entity = productionRepository.saveAndFlush(entity);

        productionModel = backwardMapping(entity, productionModel);

        return OperationResult.Success(productionModel, "Успешен запис", null);
    }

    private OperationResult<ProductionModel> validateProduction(ProductionModel productionModel) {
        List<String> errorMessages = new ArrayList<>();

        if (productionModel.getName().isEmpty()) errorMessages.add("Въведете име");

        if (!errorMessages.isEmpty())
            return OperationResult.Error("Невалидни данни!", errorMessages);
        else
            return OperationResult.Success();
    }

    private boolean productionExists(ProductionModel productionModel) {
        Optional<Production> dbProduction = productionRepository.getByUuid(productionModel.getUuid());

        return dbProduction.isEmpty();
    }

    private OperationResult<ProductionModel> validateUniqueness(ProductionModel productionModel) {
        List<String> errorMessages = new ArrayList<>();

        List<Production> dbProductions = productionRepository.findAll()
                .stream()
                .filter(n -> n.getName().equals(productionModel.getName()))
                .collect(Collectors.toList());

        if (!dbProductions.isEmpty()) errorMessages.add("Името вече съществува");

        dbProductions = productionRepository.findAll()
                .stream()
                .filter(n -> n.getIdentifyingNumber().equals(productionModel.getIdentifyingNumber()))
                .collect(Collectors.toList());

        if (!dbProductions.isEmpty()) errorMessages.add("Идентификационни номер вече съществува");

        if (!errorMessages.isEmpty())
            return OperationResult.Error("Невалидни данни!", errorMessages);
        else
            return OperationResult.Success();
    }

    private ProductionModel backwardMapping(Production entity, ProductionModel model) {
        model = mapper.map(entity, ProductionModel.class);
        return model;
    }
}
