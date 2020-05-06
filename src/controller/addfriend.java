package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class addfriend extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person person = getPersonService().getPerson(((Person) request.getSession().getAttribute("user")).getUserId());
        System.out.println(person.getFirstName());
        System.out.println(request.getParameter("name"));

        Person pp = getPersonService().getPerson(request.getParameter("name"));
        System.out.println(pp.getFirstName());
        System.out.println(pp.getUserId());

        person.addFriend(pp);
        getPersonService().updatePersons(person);
        System.out.println("friends: " + toJson(person.getFriends()));
        request.getSession().setAttribute("user",person);
        response.setContentType("text/json");
        response.getWriter().write(String.valueOf(this.toJson(person.getFriends())));


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
