package com.productionmanagement.service;

import com.productionmanagement.domain.entities.Nomenclature;
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
import java.util.Comparator;
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
                    .sorted(Comparator.comparingInt(Nomenclature::getHeadersOrderNumber))
                    .map(n -> new NomenclatureMetadataModel() {{
                        setId(n.getId());
                        setUuid(n.getUuid());
                        setTitleToBeDisplayed(n.getTitleToBeDisplayed());
                    }})
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
                String tableName = this.nomenclatureRepository.getNomenclatureTableName(nomenclatureItem.getNomenclatureID());

                NomenclatureModel dbEntity = null;
                String query = String.format("SELECT * FROM %s AS t WHERE t.uuid = '%s'", tableName, nomenclatureItem.getUuid());

                PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                dbEntity = toList(resultSet).stream().findFirst().orElse(null);

                if (dbEntity == null) {
                    result = createNomenclature(nomenclatureItem, tableName);

                    return result;
                } else {
                    result = updateNomenclature(dbEntity, nomenclatureItem, tableName);

                    return result;
                }
            }
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    @Override
    public OperationResult<NomenclatureModel> removeNomenclature(NomenclatureModel nomenclatureItem) {
        OperationResult<NomenclatureModel> result;

        try {

            NomenclatureMetadataModel metadata = null;
            NomenclatureModel dbEntity = null;

            String queryMetadata = String.format("SELECT n.table_name, n.connected_table_id_column_name, n.connected_table_name " +
                    "FROM nomenclatures AS n " +
                    "WHERE n.id = %d ", nomenclatureItem.getNomenclatureID());

            PreparedStatement statement = DbConnection.getConnection().prepareStatement(queryMetadata);
            ResultSet resultSet = statement.executeQuery();
            metadata = toListMetadata(resultSet).stream().findFirst().orElse(null);


            assert metadata != null;
            String queryEntity = String.format("SELECT t.* " +
                            "FROM %s AS t " +
                            "WHERE t.uuid = '%s' "
                    , metadata.getTableName()
                    , nomenclatureItem.getUuid());

            statement = DbConnection.getConnection().prepareStatement(queryEntity);
            resultSet = statement.executeQuery();
            dbEntity = toList(resultSet).stream().findFirst().orElse(null);

            result = approveDeletion(dbEntity, metadata);

            if (result.Type != ResultType.Success) {
                return result;
            } else {
                String queryDelete = String.format("DELETE FROM %s WHERE id = %d"
                        , metadata.getTableName()
                        , nomenclatureItem.getId());

                DbConnection.getConnection().prepareStatement(queryDelete).executeUpdate();

                return OperationResult.Success("Успешно изтрита номенклатура");
            }

        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    private OperationResult<NomenclatureModel> createNomenclature(NomenclatureModel nomenclatureItem, String tableName) throws SQLException {
        String query = String.format("INSERT INTO %s (uuid, name, is_active) " +
                "VALUES ('%s', '%s', %b)", tableName, nomenclatureItem.getUuid(), nomenclatureItem.getName(), nomenclatureItem.isActive());

        DbConnection.getConnection().prepareStatement(query).executeUpdate();

        return OperationResult.Success(nomenclatureItem, "Успешен запис.", null);
    }

    private OperationResult<NomenclatureModel> updateNomenclature(NomenclatureModel dbEntity, NomenclatureModel nomenclatureItem, String tableName) throws SQLException {
        String query = String.format("UPDATE %s " +
                "SET name = '%s', is_active = %b " +
                "WHERE uuid = '%s'", tableName, nomenclatureItem.getName(), nomenclatureItem.isActive(), nomenclatureItem.getUuid());

        DbConnection.getConnection().prepareStatement(query).executeUpdate();

        return OperationResult.Success(nomenclatureItem, "Успешена корекция.", null);
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

    private List<NomenclatureMetadataModel> toListMetadata(ResultSet resultSet) throws SQLException {
        List<NomenclatureMetadataModel> result = new ArrayList<>();

        while (resultSet.next()) {
            NomenclatureMetadataModel model = new NomenclatureMetadataModel();
            model.setTableName(resultSet.getString("table_name"));
            model.setConnectedTableIdColumnName(resultSet.getString("connected_table_id_column_name"));
            model.setConnectedTableName(resultSet.getString("connected_table_name"));
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

    private OperationResult<NomenclatureModel> approveDeletion(NomenclatureModel dbEntity, NomenclatureMetadataModel metadata) throws BusinessException, SQLException {
        List<String> errorMessages = new ArrayList<>();

        if (dbEntity == null) throw new BusinessException("Несъществуваща номенклатура");

        String queryConnections = String.format("SELECT * FROM %s AS t WHERE t.%s = %d limit 1"
                , metadata.getConnectedTableName()
                , metadata.getConnectedTableIdColumnName()
                , dbEntity.getId());

        PreparedStatement statement = DbConnection.getConnection().prepareStatement(queryConnections);
        ResultSet resultSet = statement.executeQuery();

        if (!toList(resultSet).isEmpty()) errorMessages.add("Премахнете връзките към номенклатурата и опитайте отново");

        if (!errorMessages.isEmpty()) return OperationResult.Error("Не може да бъде изтрита", errorMessages);

        return OperationResult.Success();
    }
}
