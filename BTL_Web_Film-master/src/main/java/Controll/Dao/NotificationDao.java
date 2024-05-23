package Controll.Dao;

import Controll.Entity.Notifications;

import java.util.List;

public interface NotificationDao {

    List<Notifications> findAll();
    List<Notifications> getALLNotificationByUserId(int id);
    
    List<Notifications> perPage(int page, int size);
    Notifications getNotificationsByUserId(int userId);
    Notifications create(Notifications notification);
    Notifications update(Notifications notification);
    Notifications delete(Notifications notification);
    
    long countNotifications();

}
