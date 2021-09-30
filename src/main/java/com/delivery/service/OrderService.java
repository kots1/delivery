package com.delivery.service;

import com.delivery.EmailSend;
import com.delivery.db.DirectionDAO;
import com.delivery.db.OrderDao;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.*;

import javax.servlet.http.HttpServletRequest;

public class OrderService {

    public static boolean updateStatus(String status,int orderId,User user){
        if (!OrderDao.getInstance().updateStatus(orderId,status)){
            return false;
        }
        if (status.equals("unpaid")){
            Order order = OrderDao.getInstance().getOrderById(orderId);
            EmailSend.sendEmail(user.getEmail(),order);
        }
        if (status.equals("in the way")){
            OrderDao.getInstance().updatedDate(orderId,"dispatch");
        }
        if (status.equals("delivered")){
            OrderDao.getInstance().updatedDate(orderId,"receiving");
        }
       return true;
    }
   public static Order getOrderFromRequestParam(HttpServletRequest req){
       int directionId = Integer.parseInt(req.getParameter("direction"));
       int tariffId = Integer.parseInt(req.getParameter("tariff"));
       int typeId = Integer.parseInt(req.getParameter("type"));

       Tariff tariff = TariffDAO.getInstance().getTariffById(tariffId);
       Direction direction = DirectionDAO.getInstance().getDirectionById(directionId);
       TypeBaggage typeBaggage = TypeBaggageDAO.getInstance().getTypeById(typeId);

       User user = (User) req.getSession().getAttribute("user");
       return new Order.Builder()
               .weight(Double.parseDouble(req.getParameter("weight")))
               .user(user)
               .apartment(Integer.parseInt(req.getParameter("apartment")))
               .house(Integer.parseInt(req.getParameter("house")))
               .street(req.getParameter("street"))
               .description(req.getParameter("desc"))
               .tariff(tariff)
               .typeBaggage(typeBaggage)
               .direction(direction)
               .price(Double.parseDouble(req.getParameter("price")))
               .volume(Double.parseDouble(req.getParameter("volume")))
               .build();
   }

}
