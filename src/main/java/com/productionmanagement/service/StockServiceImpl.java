package com.productionmanagement.service;

import com.productionmanagement.helpers.KeyValuePair;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.repository.MaterialTypeRepository;
import com.productionmanagement.repository.StockRepository;
import com.productionmanagement.repository.StockTypeRepository;
import com.productionmanagement.repository.UnitOfMeasureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;
    private final StockTypeRepository stockTypeRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ModelMapper mapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StockTypeRepository stockTypeRepository, MaterialTypeRepository materialTypeRepository, UnitOfMeasureRepository unitOfMeasureRepository, ModelMapper mapper) {
        this.stockRepository = stockRepository;
        this.stockTypeRepository = stockTypeRepository;
        this.materialTypeRepository = materialTypeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.mapper = mapper;
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getStockTypes() {
        try{
            List<KeyValuePair<Integer, String>> result = this.stockTypeRepository.findAll().stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        }catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getMaterialTypes() {
        try{
            List<KeyValuePair<Integer, String>> result = this.materialTypeRepository.findAll().stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        }catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<KeyValuePair<Integer, String>>> getUnitsOfMeasure() {
        try{
            List<KeyValuePair<Integer, String>> result = this.unitOfMeasureRepository.findAll().stream()
                    .map(x -> new KeyValuePair<>(x.getId(), x.getName()))
                    .collect(Collectors.toList());

            return OperationResult.Success(result, null, null);
        }catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }
}
