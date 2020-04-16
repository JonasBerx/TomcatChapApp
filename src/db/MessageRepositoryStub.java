package db;

import domain.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryStub implements MessageRepository {
    private List<Message> messageList = new ArrayList<>();

    public MessageRepositoryStub() {
        messageList = new ArrayList<>();
    }

    @Override
    public void addMessage(Message message){
        this.messageList.add(message);
    }

    @Override
    public List<Message> messagesFromChat(String userId) {
        System.out.println("in messagesfromchat" + userId);

        List<Message> result = new ArrayList();
        for(Message message:getAllMessages()){
            if(message.getRecipientId().equalsIgnoreCase(userId) || message.getSenderId().equalsIgnoreCase(userId)){
                System.out.println(message.getMessage());
                System.out.println(message.getRecipientId());
                System.out.println(message.getSenderId());
                result.add(message);
            }
        }
        return result;
    }

    private List<Message> getAllMessages(){
        return this.messageList;
    }
}