package org.jaime.bamoe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NotificationList implements Serializable {

    private List<NotificationInfo> notifications;
    

    public NotificationList () {
        this.notifications = new ArrayList<NotificationInfo>();
    }

    public List<NotificationInfo> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationInfo> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(NotificationInfo notificationInfo) {
        if(notificationInfo.getType() == null || notificationInfo.getType().isEmpty()) {
            throw new IllegalArgumentException("Notification Type cannot be null");
        }

        if(notificationInfo.getType().equals("FINAL")) {
            notificationInfo.setFinalNotificationDurationExpression("PT" + notificationInfo.getFinalNotificationDurationExpression() + "S");
            notifications.add(notificationInfo);
        }
        else {
            NotificationInfo calculatedNotification = new NotificationInfo();
            calculatedNotification.setType(notificationInfo.getType());
            calculatedNotification.setFinalNotificationDurationExpression(
                "PT" + 
                String.valueOf(Integer.valueOf(notificationInfo.getFinalNotificationDurationExpression())/ (100 / Integer.valueOf(notificationInfo.getPercentageWarn()))) +
                "S");
            notifications.add(calculatedNotification);
        }
    }
    
}
