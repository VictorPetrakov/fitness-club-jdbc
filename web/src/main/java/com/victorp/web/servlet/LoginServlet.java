package com.victorp.web.servlet;

import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;
import com.victorp.services.AuthorizationService;
import com.victorp.services.impl.AuthorizationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.victorp.web.filter.AuthenticationFilter.*;

@WebServlet(name = "LoginServlet" , urlPatterns = "/auth")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String ID_ROLE = "role";

    private final AuthorizationService authorizationService = AuthorizationServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter(LOGIN_PARAM);
        final String password = req.getParameter(PASSWORD_PARAM);
        final int role = Integer.valueOf(req.getParameter(ID_ROLE));

        final Client client;
        final Trainer trainer;
        final Admin admin;

        switch (role) {
            case 1:
                try {
                    admin = authorizationService.authorizeAdmin(login, password);
                    final String contextPath = req.getContextPath();

                    if (admin == null) {
                        resp.sendRedirect(contextPath + "/login.html");
                    } else {
                        final HttpSession session = req.getSession();
                        session.setAttribute(USER_ID_PARAM, admin.getId());
                        session.setAttribute(STATUS, admin.getIdRole());
                        req.setAttribute("username", admin.getFirstName() + " " + admin.getLastName());
                        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                        final Cookie myOwnCookie = new Cookie("MyOwnCookie", "PC");
                        resp.addCookie(myOwnCookie);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    trainer = authorizationService.authorizeTrainer(login, password);
                    final String contextPath = req.getContextPath();

                    if (trainer == null) {
                        resp.sendRedirect(contextPath + "/login.html");
                    } else {
                        final HttpSession session = req.getSession();
                        session.setAttribute(USER_ID_PARAM, trainer.getId());
                        req.setAttribute("username", trainer.getFirstName() + " " + trainer.getLastName());
                        req.setAttribute("status", trainer.getIdRole() );
                        final Cookie myOwnCookie = new Cookie("MyOwnCookie", "PC");
                        resp.addCookie(myOwnCookie);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    client = authorizationService.authorizeClient(login, password);
                    final String contextPath = req.getContextPath();

                    if (client == null) {
                        resp.sendRedirect(contextPath + "/login.html");
                    } else {
                        final HttpSession session = req.getSession();
                        session.setAttribute(USER_ID_PARAM, client.getId());
                        req.setAttribute("username", client.getFirstName() + " " + client.getLastName());
                        req.setAttribute("status", client.getIdRole() );
                        final Cookie myOwnCookie = new Cookie("MyOwnCookie", "PC");
                        resp.addCookie(myOwnCookie);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
            resp.setContentType("text/html;charset=UTF-8");
            final RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.include(req, resp);
        }
    }

