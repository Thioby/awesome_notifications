package me.carda.awesome_notifications.notifications.models;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import me.carda.awesome_notifications.Definitions;
import me.carda.awesome_notifications.notifications.exceptions.PushNotificationException;
import me.carda.awesome_notifications.utils.BitmapUtils;
import me.carda.awesome_notifications.utils.StringUtils;

public class NotificationPersonModel extends Model {

    public Boolean bot;
    public String icon;
    public Boolean important;
    public String key;
    public String name;
    public String uri;

    @Override
    public NotificationPersonModel fromMap(Map<String, Object> arguments) {
        this.bot = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_BOT, Boolean.class);
        this.icon = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_ICON, String.class);
        this.important = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_IMPORTANT, Boolean.class);
        this.key = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_KEY, String.class);
        this.name = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_NAME, String.class);
        this.uri = getValueOrDefault(arguments, Definitions.NOTIFICATION_PERSON_URI, String.class);
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Definitions.NOTIFICATION_PERSON_BOT, this.bot);
        map.put(Definitions.NOTIFICATION_PERSON_ICON, this.icon);
        map.put(Definitions.NOTIFICATION_PERSON_IMPORTANT, this.important);
        map.put(Definitions.NOTIFICATION_PERSON_KEY, this.key);
        map.put(Definitions.NOTIFICATION_PERSON_NAME, this.name);
        map.put(Definitions.NOTIFICATION_PERSON_URI, this.uri);
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
        if(StringUtils.isNullOrEmpty(name))
            throw new PushNotificationException("Person name cannot be null or empty");
    }

    public Person toPerson(Context context) {
        Person.Builder builder = new Person.Builder();
        if (this.bot != null) {
            builder.setBot(this.bot);
        }
        if (this.icon != null) {
            Bitmap bitmap = BitmapUtils.getBitmapFromSource(context, this.icon);
            if (bitmap != null) {
                builder.setIcon(IconCompat.createWithBitmap(bitmap));
            }
        }
        if (this.important != null) {
            builder.setImportant(this.important);
        }
        if (this.key != null) {
            builder.setKey(this.key);
        }
        if (this.name != null) {
            builder.setName(this.name);
        }
        if (this.uri != null) {
            builder.setUri(this.uri);
        }

        return null;
    }
}
