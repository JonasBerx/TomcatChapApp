package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFriends extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person p = getPersonService().getPerson(((Person) request.getSession().getAttribute("user")).getUserId());

        if (p != null) {
            response.setContentType("application/json");

            try {

                getPersonService().updatePersons(p);
                System.out.println("friends: " + toJson(p.getFriends()));
                request.getSession().setAttribute("user",p);
                response.setContentType("text/json");
                response.getWriter().write(String.valueOf(this.toJson(p.getFriends())));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("test");

    }

    private Object toJson(List<Person> list){
        JsonObject json = new JsonObject();
        for (Person u:list){
            JsonObject user = new JsonObject();
            user.addProperty("name",u.getFirstName());
            user.addProperty("statusname",u.getStatus());
            json.add(u.getFirstName(),user);
        }
        return json.toString();
    }
}
