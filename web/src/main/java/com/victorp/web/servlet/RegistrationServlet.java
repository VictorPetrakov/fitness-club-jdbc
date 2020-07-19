package com.victorp.web.servlet;

import com.victorp.model.Client;
import com.victorp.services.RegistrationService;
import com.victorp.services.impl.RegistrationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RegistrationServlet", urlPatterns = "/regist")

public class RegistrationServlet extends HttpServlet {

    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String FIRSTNAME_PARAM = "firstname";
    public static final String LASTNAME_PARAM = "lastname";
    public static final String BIRTHDATE_PARAM = "birthdate";
    public static final String EMAIL_PARAM = "email";
    public static final int ROLE_PARAM = 3;

    private final RegistrationService registrationService = RegistrationServiceImpl.getInstance();

    final Client client = new Client();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter(LOGIN_PARAM);
        final String password = req.getParameter(PASSWORD_PARAM);
        final String firstname = req.getParameter(FIRSTNAME_PARAM);
        final String lastname = req.getParameter(LASTNAME_PARAM);
        final String birthdate = req.getParameter(BIRTHDATE_PARAM);
        final String email = req.getParameter(EMAIL_PARAM);

        client.setLogin(login);
        client.setPassword(password);
        client.setFirstName(firstname);
        client.setLastName(lastname);
        client.setBirthdate(birthdate);
        client.setEmail(email);

        try {
            registrationService.createClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=UTF-8");
        final RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.include(req, resp);
    }


}
