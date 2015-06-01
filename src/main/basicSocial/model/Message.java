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
        this.time = LocalDateTime.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!text.equals(message.text)) return false;
        if (!time.equals(message.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
