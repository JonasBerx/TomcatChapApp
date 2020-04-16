package domain;

import com.google.gson.JsonObject;
import db.MessageRepository;
import db.MessageRepositoryStub;

import java.util.List;

public class MessageService {

    private MessageRepository messageRepository = new MessageRepositoryStub();

    public MessageService() {
    }

    public void addMessage(Person sender, Person recipient, String message){
        System.out.println(sender);
        System.out.println(recipient);
        System.out.println(message);

        Message message1 = new Message(message,recipient,sender);
//        System.out.println("addmessage:" + sender.getFirstName() + recipient.getFirstName() + message);
        this.messageRepository.addMessage(message1);
    }

    private List<Message> getMessagesForUser(String userId){
        System.out.println("in getmessagesforuser" + userId);
        return this.messageRepository.messagesFromChat(userId);
    }

    public JsonObject getMessages(Person user){
        System.out.println("getmessages in messageservice" + user.getUserId());
        JsonObject messages = getMessagesAsJson(getMessagesForUser(user.getUserId()),user);
        System.out.println("sout van getmessage in messageservice" + messages);
        return messages;
    }

    private JsonObject getMessagesAsJson(List<Message> messages, Person user){
        JsonObject object = new JsonObject();
        System.out.println("in getmessagesasjson van messageservice");
        int teller = 0;
        for(Message message: messages){

            JsonObject messageObject = new JsonObject();
            messageObject.addProperty("message", message.getMessage());
            messageObject.addProperty("recipientId", message.getRecipientId());
            messageObject.addProperty("sender", message.getSender().getFirstName());
            messageObject.addProperty("senderId",message.getSenderId());
            object.add(String.valueOf(teller),messageObject);
            teller+=1;
        }
        teller=0;
        return object;
    }
}
