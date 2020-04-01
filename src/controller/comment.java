package controller;

import com.google.gson.JsonObject;
import domain.Comment;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class comment extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));

        Comment c = new Comment(name,comment,rating);
        System.out.println(toJson(c));
        response.setContentType("text/json");
        response.getWriter().write(String.valueOf(this.toJson(c)));
    }

    private Object toJson(Comment c){
        JsonObject json = new JsonObject();

        JsonObject comment = new JsonObject();
        comment.addProperty("name", c.getName());
        comment.addProperty("comment", c.getComment());
        comment.addProperty("rating", c.getRating());

        json.add(c.getName(), comment);
        return json.toString();
    }
}
