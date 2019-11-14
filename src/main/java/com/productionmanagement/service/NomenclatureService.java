package com.productionmanagement.service;

import com.productionmanagement.domain.models.nomenclature.NomenclatureMetadataModel;
import com.productionmanagement.domain.models.nomenclature.NomenclatureModel;
import com.productionmanagement.helpers.OperationResult;

import java.util.List;

public interface NomenclatureService {
    OperationResult<List<NomenclatureMetadataModel>> getNomenclatureHeaders();

    OperationResult<List<NomenclatureModel>> getNomenclatureItems(int nomenclatureId);

    OperationResult<NomenclatureModel> submitNomenclature(NomenclatureModel nomenclatureItem);

    OperationResult<NomenclatureModel> removeNomenclature(NomenclatureModel nomenclatureItem);
}
