package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeBaggageDAO {
    private static TypeBaggageDAO typeDAO;
    private final DBManager dbManager;

    private TypeBaggageDAO() {
        dbManager = DBManager.getInstance();
    }

    public synchronized static TypeBaggageDAO getInstance() {
        if (typeDAO==null){
            typeDAO=new TypeBaggageDAO();
        }
        return typeDAO;
    }

    public boolean insertType(TypeBaggage typeBaggage) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                connection = dbManager.getConnection();
                statement = connection.prepareStatement(TypeSQLQuery.INSERT_TYPE, Statement.RETURN_GENERATED_KEYS);
                int index=1;
                statement.setString(index++, typeBaggage.getType());
                statement.setDouble(index, typeBaggage.getCoefficient());
                statement.execute();
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    typeBaggage.setId(resultSet.getInt(1));
                    return true;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                dbManager.closeObject(connection, statement, resultSet);
            }
            return false;
    }

    public List<TypeBaggage> getAllTypes() {
        List<TypeBaggage> result = new ArrayList<>();
        Connection connection=null;
        Statement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            set= statement.executeQuery(TypeSQLQuery.SELECT_TYPES);
            EntityMapper<TypeBaggage> mapper = new TypeMapper();
            while (set.next()){
                result.add( mapper.mapRow(set) );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return result;
    }

    public TypeBaggage getTypeById(int typeId) {
        TypeBaggage type = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TypeSQLQuery.SELECT_TYPE_BY_ID);
            statement.setInt(1,typeId);
            set = statement.executeQuery();
            if (set.next()) {
                type = new TypeMapper().mapRow(set);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, set);
        }
        return type;
    }

    public boolean delete(int id) {
        return dbManager.deleteElement(id,TypeSQLQuery.DELETE_TYPE);
    }

    public List<TypeBaggage> getAllTypesWithLimit(int start, int count) {
        List<TypeBaggage> result = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TypeSQLQuery.SELECT_TYPES_LIMIT);
            int index= 1;
            statement.setInt(index++, start-1);
            statement.setInt(index, count);
            set= statement.executeQuery();
            TypeMapper mapper = new TypeMapper();
            while (set.next()){
                result.add( mapper.mapRow(set) );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return result;
    }

    public int getCount() {
        return dbManager.getCount(TypeSQLQuery.GET_COUNT);
    }

    private static class TypeMapper implements EntityMapper<TypeBaggage> {

        @Override
        public TypeBaggage mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(TypeSQLQuery.FIELD_ID);
            double coefficient = rs.getDouble(TypeSQLQuery.FIELD_COEFFICIENT);
            String type = rs.getString(TypeSQLQuery.FIELD_TYPE);

            return new TypeBaggage(id,type,coefficient);
        }
    }
}
