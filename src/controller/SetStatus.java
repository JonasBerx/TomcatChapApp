package controller;


import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetStatus extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Person person = getPersonService().getPerson(((Person) request.getSession().getAttribute("user")).getUserId());
        System.out.println(person.getFirstName());

        person.setStatus(request.getParameter("status"));
        getPersonService().updatePersons(person);
        System.out.println("status: " + person.getStatus());
        request.getSession().setAttribute("user",person);
        response.setContentType("text/json");
        response.getWriter().write(this.toJSON(request.getParameter("status")));

    }

    private String toJSON (String status) {
        StringBuffer json = new StringBuffer();
        json.append("{ \"status\" : \"");
        json.append(status);
        json.append("\"}");

        return json.toString();
    }
}