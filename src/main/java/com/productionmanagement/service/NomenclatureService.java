package com.productionmanagement.service;

import com.productionmanagement.domain.models.nomenclature.NomenclatureModel;
import com.productionmanagement.helpers.OperationResult;

import java.util.List;

public interface NomenclatureService {
    OperationResult<List<NomenclatureModel>> getNomenclatureHeaders();
}