package com.delivery.db;

import com.delivery.CurrentLocale;
import com.delivery.FilterBuilder.OrderFilterBuilder;
import com.delivery.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {

    private static OrderDao orderDao;
    private final DBManager dbManager;

    private OrderDao() {
        dbManager = DBManager.getInstance();
    }

    public synchronized static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public List<Order> getAllOrderWithLimit(int start, int count) {
        return dbManager.getAllElementsWithLimit(new OrderMapper(),OrderSQLQuery.SELECT_ORDER_LIMIT,start,count);
    }

    public List<Order> getAllOrderWithFilter(String orderDate,int[] directionId) {
        return dbManager.getAllElements(new OrderMapper(),OrderFilterBuilder.filterQuery(orderDate,directionId));

    }
    public List<Order> getAllOrderWithFilterWithLimit(String orderDate,int[] directionId,int start, int count) {
        return dbManager.getAllElementsWithLimit(new OrderMapper(),OrderFilterBuilder.filterLimitsQuery(orderDate,directionId),start,count);

    }
    public List<Order> getAllOrder() {
        return dbManager.getAllElements(new OrderMapper(),OrderSQLQuery.SELECT_ORDER);


    }

    public boolean insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.INSERT_ORDER,Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            statement.setInt(index++, order.getTariff().getId());
            statement.setInt(index++, order.getUser().getId());
            statement.setInt(index++, order.getDirection().getId());
            statement.setDouble(index++, order.getPrice());
            statement.setString(index++, order.getDescription());
            statement.setDouble(index++, order.getVolume());
            statement.setDouble(index++, order.getWeight());
            statement.setString(index++, order.getStreet());
            statement.setInt(index++, order.getHouse());
            statement.setInt(index++, order.getApartment());
            statement.setInt(index, order.getTypeBaggage().getId());

            statement.execute();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement, resultSet);
        }
        return false;
    }

    public boolean updateStatus(int id, String status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            System.out.println(id);
            System.out.println(status);
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.UPDATE_STATUS);
            int index = 1;
            statement.setString(index++, status);
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

    public boolean deleteOrder(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.DELETE_ORDER);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
        return false;
    }

    public void updatedDate(int id, String dateName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.getConnection();
            if (dateName.equals("dispatch")){
                statement = connection.prepareStatement(OrderSQLQuery.UPDATE_DISPATCH);
            }else {
                statement = connection.prepareStatement(OrderSQLQuery.UPDATE_RECEIVING);
            }
            int index = 1;
            statement.setTimestamp(index++, new java.sql.Timestamp(System.currentTimeMillis()));
            statement.setInt(index, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement);
        }
    }

    public List<Order> getOrderByUserId(int id,int start,int count) {
        List<Order> result = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.SELECT_ORDER_BY_USER_ID);
            int index= 1;
            statement.setInt(index++,id);
            statement.setInt(index++,start-1);
            statement.setInt(index,count);
            set= statement.executeQuery();
            EntityMapper<Order> mapper = new OrderMapper();
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

    public Order getOrderById(int id) {
        Order result = null;
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet set=null;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.SELECT_ORDER_BY_ID);
            statement.setInt(1,id);
            set= statement.executeQuery();
            EntityMapper<Order> mapper = new OrderMapper();
            if (set.next()){
                result= mapper.mapRow(set) ;
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
        return dbManager.getCount(OrderSQLQuery.GET_COUNT);
    }

    public int getOrderCountById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set=null;
        int res = 0;
        try {
            connection = dbManager.getConnection();
            statement = connection.prepareStatement(OrderSQLQuery.GET_COUNT_BY_USER_ID);
            statement.setInt(1,id);
            set= statement.executeQuery();
            if (set.next()){
                res = set.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbManager.closeObject(connection, statement,set);
        }
        return res;
    }


    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt(OrderSQLQuery.FIELD_ID);
            int tariffId = rs.getInt(OrderSQLQuery.FIELD_TARIFF);
            int userId = rs.getInt(OrderSQLQuery.FIELD_USER);
            int directionId = rs.getInt(OrderSQLQuery.FIELD_DIRECTION);
            double price = rs.getDouble(OrderSQLQuery.FIELD_PRICE);
            String description = rs.getString(OrderSQLQuery.FIELD_DESC);
            double volume = rs.getDouble(OrderSQLQuery.FIELD_VOLUME);
            double weight = rs.getDouble(OrderSQLQuery.FIELD_WEIGHT);
            Date orderDate = rs.getDate(OrderSQLQuery.FIELD_ORDER_DATE);
            Date dispatchDate = rs.getDate(OrderSQLQuery.FIELD_DISPATCH);
            Date receivingDate = rs.getDate(OrderSQLQuery.FIELD_RECEIVING);
            String status = rs.getString(OrderSQLQuery.FIELD_STATUS);
            String street = rs.getString(OrderSQLQuery.FIELD_STREET);
            int house = rs.getInt(OrderSQLQuery.FIELD_HOUSE);
            int apartment = rs.getInt(OrderSQLQuery.FIELD_APARTMENT);
            int typeId = rs.getInt(OrderSQLQuery.FIELD_TYPE);

            Tariff tariff = TariffDAO.getInstance().getTariffById(tariffId);
            User user = UserDAO.getInstance().getUserById(userId);
            Direction direction = DirectionDAO.getInstance().getDirectionById(directionId);
            TypeBaggage typeBaggage = TypeBaggageDAO.getInstance().getTypeById(typeId);

            return new Order.Builder()
                    .orderId(id)
                    .tariff(tariff)
                    .orderDate(orderDate)
                    .apartment(apartment)
                    .description(description)
                    .weight(weight)
                    .dispatchDate(dispatchDate)
                    .orderStatus(status)
                    .house(house)
                    .price(price)
                    .receivingDate(receivingDate)
                    .street(street)
                    .direction(direction)
                    .user(user)
                    .volume(volume)
                    .typeBaggage(typeBaggage)
                    .build();
        }
    }
}
