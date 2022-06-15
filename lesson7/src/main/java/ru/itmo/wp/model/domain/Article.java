package ru.itmo.wp.model.domain;

public class Article extends BasicDomain {
    public enum Status {
        HIDE, SHOW
    }

    private long userId;
    private String title, text;
    private Status hidden = Status.SHOW;
    private String userLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Status getHidden() {
        return hidden;
    }

    public void setHidden(Status hidden) {
        this.hidden = hidden;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
