package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event extends BaseElement{
    public enum Type {
        ENTER, LOGOUT
    }
    private long userId;
    private Type type;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
