package ru.itmo.wp.lesson8.form;

public class UserDisableEnableForm {
    private long userId;
    private long userDisableId;
    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserDisableId() {
        return userDisableId;
    }

    public void setUserDisableId(long userDisableId) {
        this.userDisableId = userDisableId;
    }
}
