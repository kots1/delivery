package com.delivery.db;

public class TypeSQLQuery {
    public static final String INSERT_TYPE = "insert into type_of_baggage (type,coefficient) values (?,?)";
    public static final String FIELD_ID = "type_id";
    public static final String FIELD_COEFFICIENT = "coefficient";
    public static final String FIELD_TYPE = "type";
    public static final String SELECT_TYPES = "select * from type_of_baggage";
    public static final String SELECT_TYPE_BY_ID = "select * from type_of_baggage where type_id=?";
}
