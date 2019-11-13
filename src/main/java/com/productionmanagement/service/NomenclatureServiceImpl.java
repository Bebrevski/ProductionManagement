package com.productionmanagement.service;

import com.productionmanagement.domain.models.nomenclature.NomenclatureMetadataModel;
import com.productionmanagement.domain.models.nomenclature.NomenclatureModel;
import com.productionmanagement.factories.DbConnection;
import com.productionmanagement.helpers.BusinessException;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.helpers.ResultType;
import com.productionmanagement.repository.NomenclatureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {
    private final NomenclatureRepository nomenclatureRepository;
    private final ModelMapper mapper;

    @Autowired
    public NomenclatureServiceImpl(NomenclatureRepository nomenclatureRepository, ModelMapper mapper) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.mapper = mapper;
    }

    @Override
    public OperationResult<List<NomenclatureMetadataModel>> getNomenclatureHeaders() {
        try {
            List<NomenclatureMetadataModel> nomenclatureHeaders = this.nomenclatureRepository.findAll()
                    .stream()
                    .map(n -> mapper.map(n, NomenclatureMetadataModel.class))
                    .collect(Collectors.toList());

            return OperationResult.Success(nomenclatureHeaders, null, null);
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<List<NomenclatureModel>> getNomenclatureItems(int nomenclatureId) {
        String tableName = this.nomenclatureRepository.getNomenclatureTableName(nomenclatureId);

        String query = String.format("SELECT * FROM %s AS t ORDER BY t.name ASC", tableName);

        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            return OperationResult.Success(toList(resultSet), "Успешно заредени данни.", null);

        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<NomenclatureModel> submitNomenclature(NomenclatureModel nomenclatureItem) {
        OperationResult<NomenclatureModel> result;

        try {
            result = validateNomenclature(nomenclatureItem);

            if (result.Type != ResultType.Success) {
                return result;
            } else {

            }

            return null;
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    private List<NomenclatureModel> toList(ResultSet resultSet) throws SQLException {
        List<NomenclatureModel> result = new ArrayList<>();

        while (resultSet.next()) {
            NomenclatureModel model = new NomenclatureModel();
            model.setId(resultSet.getInt("id"));
            model.setUuid(resultSet.getString("uuid"));
            model.setName(resultSet.getString("name"));
            model.setActive(resultSet.getBoolean("is_active"));
            result.add(model);
        }

        return result;
    }

    private OperationResult<NomenclatureModel> validateNomenclature(NomenclatureModel nomenclatureItem) throws BusinessException {
        List<String> errorMessages = new ArrayList<>();

        if (nomenclatureItem.getUuid().isEmpty()) throw new BusinessException("Невалиден уникален идентификатор");

        if (nomenclatureItem.getName().isEmpty()) errorMessages.add("Невалидно име на номенклатура");

        if (!errorMessages.isEmpty())
            return OperationResult.Error("Невалидни данни", errorMessages);
        else
            return OperationResult.Success();
    }
}
