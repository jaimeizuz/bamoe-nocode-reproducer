package org.jaime.bamoe.dto;

import java.io.Serializable;

public class NotificationInfo implements Serializable {

    private String type;
    private String percentageWarn;
    private String finalNotificationDurationExpression;

    public NotificationInfo() {
    }
    public NotificationInfo(String type, String percentageWarn, String durationExpression) {
        this.type = type;
        this.percentageWarn = percentageWarn;
        this.finalNotificationDurationExpression = durationExpression;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFinalNotificationDurationExpression() {
        return finalNotificationDurationExpression;
    }
    public void setFinalNotificationDurationExpression(String finalNotificationDurationExpression) {
        this.finalNotificationDurationExpression = finalNotificationDurationExpression;
    }
    public String getPercentageWarn() {
        return percentageWarn;
    }
    public void setPercentageWarn(String percentageWarn) {
        this.percentageWarn = percentageWarn;
    }
    @Override
    public String toString() {
        return "NotificationInfo [type=" + type + ", percentageWarn=" + percentageWarn + ", finalNotificationDurationExpression="
                + finalNotificationDurationExpression + "]";
    }

    
}
