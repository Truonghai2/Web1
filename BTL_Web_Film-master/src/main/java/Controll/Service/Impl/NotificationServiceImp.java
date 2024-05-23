package Controll.Service.Impl;

import java.util.List;

import Controll.Dao.NotificationDao;
import Controll.Dao.Impl.NotificationsDaoImp;
import Controll.Entity.Notifications;
import Controll.Entity.User;
import Controll.Service.NotificationService;

public class NotificationServiceImp implements NotificationService {

    private NotificationDao notificationsDao;
    


    public NotificationServiceImp(){
        notificationsDao = new NotificationsDaoImp();
    }
    
    
    
    @Override
    public List<Notifications> perPage(int page, int size){
    	return notificationsDao.perPage(page, size);
    }
    @Override
    public List<Notifications> getAllNotifications(){

        return notificationsDao.findAll();

    }

    
    
    @Override
    public List<Notifications> getALLNotificationByUserId(int id){
    	return notificationsDao.getALLNotificationByUserId(id);
    }
    @Override
    public Notifications getNotificationsByUserId(int userId){
        return notificationsDao.getNotificationsByUserId(userId);
    }

    @Override
    public Notifications createNotification(User user, String content, int type){
        Notifications notifications = new Notifications();
        notifications.setUser(user);
        notifications.setType(type);
        notifications.setContent(content);
        notifications.setIsActive(1);
        notifications.setSeen(0);
        return notificationsDao.create(notifications);
    }
    @Override
    public Notifications deleteNotification(Integer notificationId){
        Notifications notifications = new Notifications();
        notifications.setId(notificationId);
        return notificationsDao.delete(notifications);
    }

    @Override
    public Notifications updateSeen(int id){
        Integer seen = 1;
        Notifications notifications = new Notifications();
        notifications.setId(id);
        notifications.setSeen(seen);
        
        return notificationsDao.update(notifications);
    }

    @Override
    public Notifications createNotificationVideo(String content, String href) {
    	Notifications notifications = new Notifications();
    	notifications.setUser(null);
    	notifications.setContent(content);
    	notifications.setType(0);
    	notifications.setHref(href);
    	notifications.setIsActive(1);
        notifications.setSeen(0);
    	return notificationsDao.create(notifications);
    }
    @Override
    public Notifications createNotificationNoUser(String content, int type){
        Notifications notifications = new Notifications();
        notifications.setType(type);
        notifications.setContent(content);
        notifications.setIsActive(1);
        notifications.setSeen(0);
        notifications.setUser(null);
        notifications.getAddDate();
        return notificationsDao.create(notifications);
    }
    
    @Override
    
    public long cntNoti() {
    	return notificationsDao.countNotifications();
    }
    
    
}
