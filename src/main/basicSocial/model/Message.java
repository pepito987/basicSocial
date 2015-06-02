package basicSocial.model;

import org.joda.time.LocalDateTime;


/**
 * Created by peppe on 28/05/15.
 */
public class Message {
	
	public static final String COLLECTION = "post";
	
	private String sender;
    private String text;
    private LocalDateTime time;

    public Message(String sender,String text) {
        this.text = text;
        this.sender = sender;
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

    public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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
