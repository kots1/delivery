package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.entity.Direction;
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

    public List<Tariff> getAllTariff() {
        return dbManager.getAllElements(new TariffMapper(),TariffSQLQuery.SELECT_TARIFF);
    }
    public List<Tariff> getAllAliveTariff() {
        return dbManager.getAllElements(new TariffMapper(),TariffSQLQuery.SELECT_ALIVE_TARIFF);
    }

    public  boolean insertTariff(Tariff tariff) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
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
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }

    public boolean insertLocaleTariffName(String locale, String name, Tariff tariff) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TariffSQLQuery.INSERT_LOCALE_NAME);
            int index =1;
            statement.setString(index++, locale);
            statement.setInt(index++, tariff.getId());
            statement.setString(index, name);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
        return false;
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

    public boolean delete(int id) {
        return
        dbManager.deleteElement(id,TariffSQLQuery.DELETE_TARIFF_NAME)
        && dbManager.deleteElement(id,TariffSQLQuery.DELETE_TARIFF);
    }

    public boolean updateIsAlive(boolean status, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(TariffSQLQuery.UPDATE_ALIVE);
            int index = 1;
            statement.setBoolean(index++, status);
            statement.setInt(index, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
        return false;
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
