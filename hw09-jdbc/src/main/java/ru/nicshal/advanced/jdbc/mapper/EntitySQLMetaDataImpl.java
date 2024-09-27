package ru.nicshal.advanced.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public final class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private static final String TEMPLATE = "###";
    private final String insertSql;
    private final String updateSql;
    private final String selectAllSql;
    private final String selectByIdSql;

    private EntitySQLMetaDataImpl(String tableName, String IdFieldName, List<String> fieldList) {
        this.insertSql = String.format("insert into %s (%s) values (%s)",
                tableName, String.join(", ", fieldList), generateStatementFromTemplate(fieldList, "?")
        );
        this.updateSql = String.format("update %s set %s where %s = ?",
                tableName, generateStatementFromTemplate(fieldList, TEMPLATE + " = ?"), IdFieldName
        );
        this.selectAllSql = String.format("select * from %s", tableName);
        this.selectByIdSql = String.format("select * from %s where %s = ?", tableName, IdFieldName);
    }

    public static EntitySQLMetaData of(EntityClassMetaData<?> metaData) {
        return new EntitySQLMetaDataImpl(
                metaData.getName(),
                metaData.getIdField().getName(),
                metaData.getFieldsWithoutId()
                        .stream()
                        .map(Field::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String getSelectAllSql() {
        return this.selectAllSql;
    }

    @Override
    public String getSelectByIdSql() {
        return this.selectByIdSql;
    }

    @Override
    public String getInsertSql() {
        return this.insertSql;
    }

    @Override
    public String getUpdateSql() {
        return this.updateSql;
    }

    private String generateStatementFromTemplate(List<String> fieldList, String template) {
        return fieldList.stream()
                .map(field -> template.replace(TEMPLATE, field))
                .collect(Collectors.joining(", "));
    }

}
