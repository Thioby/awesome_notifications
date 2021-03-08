package me.carda.awesome_notifications.notifications.models;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import me.carda.awesome_notifications.Definitions;
import me.carda.awesome_notifications.notifications.exceptions.PushNotificationException;
import me.carda.awesome_notifications.notifications.models.Model;
import me.carda.awesome_notifications.notifications.models.NotificationPersonModel;

public class NotificationMessageModel extends Model {

    public String text;
    public String date;
    public NotificationPersonModel person;

    @Override
    public NotificationMessageModel fromMap(Map<String, Object> arguments) {
        this.text = getValueOrDefault(arguments, Definitions.NOTIFICATION_MESSAGE_TEXT, String.class);
        this.date = getValueOrDefault(arguments, Definitions.NOTIFICATION_MESSAGE_DATE, String.class);
        this.person = new NotificationPersonModel().fromMap(getValueOrDefault(arguments, Definitions.NOTIFICATION_MESSAGE_PERSON, Map.class));
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Definitions.NOTIFICATION_MESSAGE_TEXT, this.text);
        map.put(Definitions.NOTIFICATION_MESSAGE_DATE, this.date);
        map.put(Definitions.NOTIFICATION_MESSAGE_PERSON, this.person.toMap());
        return map;
    }

    @Override
    public String toJson() {
        return templateToJson();
    }

    @Override
    public NotificationButtonModel fromJson(String json){
        return (NotificationButtonModel) super.templateFromJson(json);
    }

    @Override
    public void validate(Context context) throws PushNotificationException {

    }
}
