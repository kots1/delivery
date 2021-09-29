package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.entity.Tariff;

import java.sql.*;
import java.util.List;

public class TariffDAO {

    private static TariffDAO tariffDAO;
    private final DBManager dbManager;

    private TariffDAO() {
        dbManager = DBManager.getInstance();
    }

    public synchronized static TariffDAO getInstance() {
        if (tariffDAO==null){
            tariffDAO=new TariffDAO();
        }
        return tariffDAO;
    }

    public List<Tariff> getAllTariffWithLimit(int start, int count) {
        return dbManager.getAllElementsWithLimitUsingLocale(new TariffMapper(),TariffSQLQuery.SELECT_TARIFF,start,count);
    }
    public List<Tariff> getAllAliveTariff() {
        return dbManager.getAllElementsUsingLocale(new TariffMapper(),TariffSQLQuery.SELECT_ALIVE_TARIFF);
    }
    public List<Tariff> getAllAliveTariffWithLimit(int start, int count) {
        return dbManager.getAllElementsWithLimitUsingLocale(new TariffMapper(),TariffSQLQuery.SELECT_ALIVE_TARIFF_LIMIT,start,count);
    }
    public  void insertTariff(Connection connection, Tariff tariff) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(TariffSQLQuery.INSERT_TARIFF, Statement.RETURN_GENERATED_KEYS);
            int index=1;
            statement.setDouble(index++, tariff.getPricePerKm());
            statement.setDouble(index++, tariff.getPricePerKg());
            statement.setDouble(index++, tariff.getPricePerM3());
            statement.setDouble(index++, tariff.getMaxWeight());
            statement.setDouble(index++, tariff.getMaxVolume());
            statement.setDouble(index, tariff.getTimePer100km());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tariff.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Cannot insert tariff",e);
        } finally {
            dbManager.closeObject( statement, resultSet);
        }
    }

    public void insertLocaleTariffName(Connection connection, String locale, String name, Tariff tariff) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(TariffSQLQuery.INSERT_LOCALE_NAME);
            int index =1;
            statement.setString(index++, locale);
            statement.setInt(index++, tariff.getId());
            statement.setString(index, name);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Cannot locale name "+locale+"in tariff",e);
        } finally {
            dbManager.closeObject( statement);
        }
    }

    public Tariff getTariffById(int id) {
        Tariff tariff=null;
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TariffSQLQuery.SELECT_TARIFF_BY_ID);
            int index=1;
            statement.setString(index++, CurrentLocale.getLocale());
            statement.setInt(index++, id);
            statement.setInt(index, id);
            set= statement.executeQuery();
            if (set.next()){
                tariff=  new TariffMapper().mapRow(set);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            dbManager.closeObject(connection,statement,set);
        }
        return tariff;
    }

    public void delete(int id, Connection connection) throws SQLException {
        dbManager.deleteElement(id,TariffSQLQuery.DELETE_TARIFF_NAME,connection);
        dbManager.deleteElement(id,TariffSQLQuery.DELETE_TARIFF,connection);
    }

    public void updateIsAlive(boolean status, int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TariffSQLQuery.UPDATE_ALIVE);
            int index = 1;
            statement.setBoolean(index++, status);
            statement.setInt(index, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("cannot update tariff status "+status,e);
        } finally {
            dbManager.closeObject(connection, statement);
        }
    }

    public int getCount() {
        return dbManager.getCount(TariffSQLQuery.GET_COUNT);
    }


    private static class TariffMapper implements EntityMapper<Tariff>{

        @Override
        public Tariff mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(TariffSQLQuery.FIELD_ID);
            String name =rs.getString(TariffSQLQuery.FIELD_NAME);
            double priceKg =rs.getDouble(TariffSQLQuery.FIELD_PRICE_KG);
            double priceM3 =rs.getDouble(TariffSQLQuery.FIELD_PRICE_M3);
            double priceKm =rs.getDouble(TariffSQLQuery.FIELD_PRICE_KM);
            double maxKg =rs.getDouble(TariffSQLQuery.FIELD_KG);
            double maxM3 =rs.getDouble(TariffSQLQuery.FIELD_M3);
            double time =rs.getDouble(TariffSQLQuery.FIELD_TIME);
            boolean isAlive =rs.getBoolean(TariffSQLQuery.FIELD_ALIVE);
            return new Tariff.Builder()
                    .id(id)
                    .maxVolume(maxM3)
                    .pricePerKm(priceKm)
                    .maxWeight(maxKg)
                    .timePer100km(time)
                    .pricePerKg(priceKg)
                    .pricePerM3(priceM3)
                    .name(name)
                    .isAlive(isAlive)
                    .build();
        }
    }
}
