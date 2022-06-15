package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostForm {
    @NotEmpty
    @NotNull
    @Size(max = 3000)
    private String text;

    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Pattern(regexp = "[0-9]+", message = "Expected numeric userId")
    private long userId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
