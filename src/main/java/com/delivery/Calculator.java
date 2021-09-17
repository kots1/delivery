package com.delivery;

import com.delivery.db.DirectionDAO;
import com.delivery.db.TariffDAO;
import com.delivery.db.TypeBaggageDAO;
import com.delivery.entity.Direction;
import com.delivery.entity.Tariff;
import com.delivery.entity.TypeBaggage;

import java.util.Calendar;
import java.util.Date;

public class Calculator {



    public static double calculatePrice(int directionId, int tariffId,int typeId, double weight, double volume) {
        double price = 0;
        Tariff tariff = TariffDAO.getInstance().getTariffById(tariffId);
        Direction direction = DirectionDAO.getInstance().getDirectionById(directionId);
        TypeBaggage type = TypeBaggageDAO.getInstance().getTypeById(typeId);
        price = tariff.getPricePerKg()*weight;
        price+=tariff.getPricePerM3()*volume;
        price+=tariff.getPricePerKm()*direction.getDistance();
        price*=type.getCoefficient();
        return price;
    }

    public static Date calculateDate(int directionId, int tariffId) {
        Tariff tariff = TariffDAO.getInstance().getTariffById(tariffId);
        Direction direction = DirectionDAO.getInstance().getDirectionById(directionId);
        int minutePer100km= (int) ((tariff.getTimePer100km()-(int)tariff.getTimePer100km())*100);
        int hour =  (direction.getDistance()/100*(int)tariff.getTimePer100km());
        int minute =  (direction.getDistance()/100*minutePer100km);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

}
