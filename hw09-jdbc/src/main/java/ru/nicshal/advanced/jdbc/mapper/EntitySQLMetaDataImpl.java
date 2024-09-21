package ru.nicshal.advanced.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private static final String TEMPLATE = "###";
    private final String tableName;
    private final String IdFieldName;
    private final List<String> fieldList;

    private EntitySQLMetaDataImpl(String tableName, String IdFieldName, List<String> fieldList) {
        this.tableName = tableName;
        this.IdFieldName = IdFieldName;
        this.fieldList = new LinkedList<>(fieldList);

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
        return String.format("select * from %s", tableName);
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("select * from %s where %s = ?", tableName, IdFieldName);
    }

    @Override
    public String getInsertSql() {
        return String.format(
                "insert into %s (%s) values (%s)",
                tableName, String.join(", ", fieldList), generateStatementFromTemplate(fieldList, "?")
        );
    }

    @Override
    public String getUpdateSql() {
        return String.format(
                "update %s set %s where %s = ?",
                tableName, generateStatementFromTemplate(fieldList, TEMPLATE + " = ?"), IdFieldName
        );
    }

    private String generateStatementFromTemplate(List<String> fieldList, String template) {
        return fieldList.stream()
                .map(field -> template.replace(TEMPLATE, field))
                .collect(Collectors.joining(", "));
    }

}
