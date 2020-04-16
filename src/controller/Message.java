package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Message extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person user = (Person) request.getSession().getAttribute("user");
        String message = request.getParameter("message");
        String recipientId = request.getParameter("recipient");

        System.out.println("Sender: " + user.getFirstName());
        System.out.println("Message:" + message);
        System.out.println("Recipient: " + recipientId);
        System.out.println("Personrepo" +getPersonService());
        System.out.println(getPersonService().getPerson(recipientId));
        Person recipient = getPersonService().getPerson(recipientId);


        getMessageService().addMessage(user, recipient, message);
        System.out.println("voorbij addmessage in messagehandler");
        System.out.println(user.getUserId());
        JsonObject object = getMessageService().getMessages(user);
        System.out.println("object in message handler" + object.toString());

        response.getWriter().write(object.toString());

    }
}
