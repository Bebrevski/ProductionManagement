package com.productionmanagement.service;

import com.productionmanagement.domain.entities.Stock;
import com.productionmanagement.domain.models.stock.StockModel;
import com.productionmanagement.helpers.BusinessException;
import com.productionmanagement.helpers.KeyValuePair;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.helpers.ResultType;
import com.productionmanagement.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockTypeRepository stockTypeRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ProductionRepository productionRepository;
    private final ModelMapper mapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StockTypeRepository stockTypeRepository, MaterialTypeRepository materialTypeRepository, UnitOfMeasureRepository unitOfMeasureRepository, ProductionRepository productionRepository, ModelMapper mapper) {
        this.stockRepository = stockRepository;
        this.stockTypeRepository = stockTypeRepository;
        this.materialTypeRepository = materialTypeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.productionRepository = productionRepository;
        this.mapper = mapper;
    }

    @Override
    public OperationResult<StockModel> submitStock(String productionUuid, StockModel stockModel) {
        try {
            OperationResult<StockModel> result = validateStock(productionUuid, stockModel);

            if (result.Type != ResultType.Success) {
                return result;
            } else {
                Stock entity = this.stockRepository.findByUuidAndActiveIsTrue(stockModel.getUuid()).orElse(null);

                if (entity == null) {
                    //create
                    entity = this.mapper.map(stockModel, Stock.class);
                    entity.setStockType(this.stockTypeRepository.findById(stockModel.getStockTypeId()));
                    entity.setProduction(this.productionRepository.getByUuid(productionUuid).orElse(null));
                    entity.setLastModified(LocalDate.now());
                    entity.setActive(true);

                    entity = this.stockRepository.saveAndFlush(entity);

                    return OperationResult.Success(this.mapper.map(entity, StockModel.class), "Успешен запис.", null);
                } else {
                    //update
                    entity = this.mapper.map(stockModel, Stock.class);
                    entity.setStockType(this.stockTypeRepository.findById(stockModel.getStockTypeId()));
                    entity.setLastModified(LocalDate.now());

                    entity = this.stockRepository.save(entity);

                    return OperationResult.Success(this.mapper.map(entity, StockModel.class), "Успешена корекция.", null);
                }
            }
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<StockModel> deleteStock(String productionUuid, StockModel stockToBeDeleted) {
        try{
            OperationResult<StockModel> result;

            Stock entity = this.stockRepository.findById(stockToBeDeleted.getId()).orElse(null);

            result = approveDeletion(entity, stockToBeDeleted.getId());

            if (result.Type == ResultType.Success){

                this.stockRepository.delete(entity);

                return OperationResult.Success("Успешно изтрит склад.");
            }else {
                return result;
            }
        }catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<StockModel>> getStocks(String productionUuid) {

        List<StockModel> result = this.stockRepository.findAllByProduction_UuidAndActiveIsTrue(productionUuid).stream()
                .map(x -> mapper.map(x, StockModel.class))
                .collect(Collectors.toList());

        return OperationResult.Success(result, null, null);
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getStockTypes() {
        try {
            List<KeyValuePair<Integer, String>> result = this.stockTypeRepository.findAllByActive(true).stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getMaterialTypes() {
        try {
            List<KeyValuePair<Integer, String>> result = this.materialTypeRepository.findAllByActive(true).stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getUnitsOfMeasure() {
        try {
            List<KeyValuePair<Integer, String>> result = this.unitOfMeasureRepository.findAllByActive(true).stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    private OperationResult<StockModel> approveDeletion(Stock entity, int stockId) throws BusinessException {
        List<String> errorMessages = new ArrayList<>();

        if (entity == null) throw new BusinessException("несъществуващ склад!");
        if(!entity.getMaterials().isEmpty()) errorMessages.add("Склада съдържа материали! Изтрийте съдържанието и пробвайте отново.");

        if (errorMessages.isEmpty()) {
            return OperationResult.Success();
        }
        return OperationResult.Error("Не може да бъде изтрито!", errorMessages);
    }

    private OperationResult<StockModel> validateStock(String productionUuid, StockModel stockModel) throws BusinessException {
        List<String> errorMessages = new ArrayList<>();

        /*
        data integrity check
         */
        if (productionUuid.isEmpty()) throw new BusinessException("Невалиден уникален идентификатор за база");
        if (this.productionRepository.getByUuid(productionUuid).isEmpty())
            throw new BusinessException("Несъществуваща база");
        if (stockModel.getUuid().isEmpty()) throw new BusinessException("Невалиден уникален идентификатор на склад");

        /*
        validation
         */
        if (stockModel.getStockTypeId() == 0) errorMessages.add("Изберете склад");

        if (errorMessages.isEmpty()) return OperationResult.Success();
        return OperationResult.Error("Невалидни данни!", errorMessages);
    }
}
