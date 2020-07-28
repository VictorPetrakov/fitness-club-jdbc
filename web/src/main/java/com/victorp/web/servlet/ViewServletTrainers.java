package com.victorp.web.servlet;

import com.victorp.model.Trainer;
import com.victorp.services.ViewService;
import com.victorp.services.impl.ViewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ViewServletTrainers", urlPatterns = "/viewTrainers")

public class ViewServletTrainers extends HttpServlet {

    private final ViewService viewService = ViewServiceImpl.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            final List<Trainer> trainerList = viewService.viewTrainers();
            req.setAttribute("trainers", trainerList);
            req.getServletContext().getRequestDispatcher("/trainerView.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
