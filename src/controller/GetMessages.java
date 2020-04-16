package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetMessages extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person user = (Person) request.getSession().getAttribute("user");

        JsonObject object = getMessageService().getMessages(user);


        response.getWriter().write(object.toString());
    }
}
