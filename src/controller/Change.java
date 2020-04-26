package controller;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Change extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String colorHex = request.getParameter("colorHex");
        System.out.println(colorHex);

        response.setContentType("json");
        response.getWriter().write(String.valueOf(this.toJson(colorHex)));
    }

    private Object toJson(String colorHex){
        JsonObject color = new JsonObject();
        color.addProperty("color",colorHex);
        return color.toString();
    }
}
