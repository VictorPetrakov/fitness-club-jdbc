package com.victorp.web.servlet;


import com.victorp.model.Trainer;
import com.victorp.services.RegistrationService;
import com.victorp.services.impl.RegistrationServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServletTrainers", urlPatterns = "/registTrainers")

public class RegistrationServletTrainers extends HttpServlet {

    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String FIRSTNAME_PARAM = "firstname";
    public static final String LASTNAME_PARAM = "lastname";
    public static final String BIRTHDATE_PARAM = "birthdate";
    public static final String EMAIL_PARAM = "email";
    public static final String GROUP_PARAM = "group";
    public static final int ROLE_PARAM = 2;

    private final RegistrationService registrationService = RegistrationServiceImpl.getInstance();

    final Trainer trainer = new Trainer();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter(LOGIN_PARAM);
        final String password = req.getParameter(PASSWORD_PARAM);
        final String firstname = req.getParameter(FIRSTNAME_PARAM);
        final String lastname = req.getParameter(LASTNAME_PARAM);
        final String birthdate = req.getParameter(BIRTHDATE_PARAM);
        final String email = req.getParameter(EMAIL_PARAM);
        final String group = req.getParameter(GROUP_PARAM);


        trainer.setLogin(login);
        trainer.setPassword(password);
        trainer.setFirstName(firstname);
        trainer.setLastName(lastname);
        trainer.setBirthdate(birthdate);
        trainer.setEmail(email);
        trainer.setGroup(group);


        try {
            registrationService.createTrainer(trainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=UTF-8");
        final RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.include(req, resp);
    }


}

