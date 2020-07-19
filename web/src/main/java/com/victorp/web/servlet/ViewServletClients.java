package com.victorp.web.servlet;

import com.victorp.model.Client;
import com.victorp.services.ViewService;
import com.victorp.services.impl.ViewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewServletClients", urlPatterns = "/viewClients")

public class ViewServletClients extends HttpServlet {

    private final ViewService viewService = ViewServiceImpl.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            final List<Client> clientList = viewService.viewClients();
            req.setAttribute("clients", clientList);
            req.getServletContext().getRequestDispatcher("/clientsView.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
