package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class privateChat extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("nick");
        System.out.println(username);
        String message = request.getParameter("message");
        System.out.println(message);
        System.out.println(toJson(username, message));

        response.setContentType("json");
        response.getWriter().write(String.valueOf(this.toJson(username, message)));

    }

    private Object toJson(String name, String message){
        JsonObject chat = new JsonObject();
            JsonObject user = new JsonObject();
            chat.addProperty("name",name);
            chat.addProperty("message",message);

        return chat.toString();
    }
}
