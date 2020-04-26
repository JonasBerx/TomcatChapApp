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
        Person recipient = getPersonService().getPerson(recipientId);
        getMessageService().addMessage(user, recipient, message);
        JsonObject object = getMessageService().getMessages(user);
        response.getWriter().write(object.toString());

    }
}
