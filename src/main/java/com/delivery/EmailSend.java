package com.delivery;


import com.delivery.entity.Order;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend  {

    public static void sendEmail(String to, Order order) {

         String user="volodymyr.kotsko.knm.2019@lpnu.ua";//change accordingly
         String pass="06.10.2001";

//1st step) Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,pass);
                    }
                });
//2nd step)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Order № "+order.getOrderId());
            message.setText("Your Order(№" + order.getOrderId() + "): " +
                    "\nCustomer : " + order.getUser().getName() + " " + order.getUser().getSecondName() +
                    "\nTariff : " + order.getTariff().getName() +
                    "\nDirection : " + order.getDirection().getStartCity() +
                    " - " + order.getDirection().getFinalCity() + " (" + order.getDirection().getDistance() + " km )\n" +
                    "Description: " + order.getDescription() +
                    "\nType of baggage :" + order.getTypeBaggage().getType() +
                    "\nAddress : st. " + order.getStreet() + ", " + order.getHouse() + " (" + order.getApartment() + " ap)" +
                    "\nVolume :  " + order.getVolume() + " m3" +
                    "\nWeight : " + order.getWeight() + " kg" +
                    "\nOrder date: "+order.getOrderDate()+
                    "\n------------------------------------------"+
                    "\nApproximate receiving date : "+Calculator.calculateDate(order.getDirection().getId(),order.getTariff().getId())+
                    "\nTotal price: "+order.getPrice()+"" +
                    "\n\nThank you for order! \ndelivery.com");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
} 