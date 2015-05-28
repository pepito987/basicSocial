package model;

import java.time.LocalDateTime;

/**
 * Created by peppe on 28/05/15.
 */
public class Message {

    private String text;
    private LocalDateTime time;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
