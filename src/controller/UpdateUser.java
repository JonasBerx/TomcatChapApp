package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class UpdateUser extends RequestHandler {


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {



        getPersonService().getPerson(String.valueOf(request.getParameter("id"))).setStatus(request.getReader().readLine());

//        String pp = request.getParameter("user");
//        System.out.println(pp);
//        p.setStatus(request.getParameter("user"));

    }
}
