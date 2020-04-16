package domain;

public class Message {

    private String message;
    private Person recipient;
    private Person sender;

    public Message(String message, Person recipient, Person sender) {
        setMessage(message);
        setRecipient(recipient);
        setSender(sender);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }
    public String getRecipientId(){
        return getRecipient().getUserId();
    }

    public String getSenderId(){
        return getSender().getUserId();
    }
}
