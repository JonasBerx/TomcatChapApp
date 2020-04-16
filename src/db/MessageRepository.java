package db;

import domain.Message;
import java.util.List;

public interface MessageRepository {

    void addMessage(Message message);

    List<Message> messagesFromChat(String userId);
}
