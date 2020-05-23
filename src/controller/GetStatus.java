package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetStatus extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person p = getPersonService().getPerson(((Person) request.getSession().getAttribute("user")).getUserId());
        response.getWriter().write(p.getStatus());

    }
}
