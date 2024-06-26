package com.yennth.assignment.controller.user;

import com.yennth.assignment.entity.Favorite;
import com.yennth.assignment.entity.User;
import com.yennth.assignment.entity.Video;
import com.yennth.assignment.service.UserService;
import com.yennth.assignment.service.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/detail", "/addFavorite", "/deleteFavorite"})
public class DetailServlet extends HttpServlet {
    VideoService service = new VideoService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/addFavorite")) {
            addFavorite(req, resp);
        }
        if (uri.contains("/deleteFavorite")) {
            deleteFavorite(req, resp);
        } else {
            detail(req, resp);
        }

    }

    void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String username = (String) req.getSession().getAttribute("username");

        User user = userService.getById(username);
        Video video = service.getById(id);

        if (user != null && video != null) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setVideo(video);

            try {
                service.addFavorite(favorite);
                System.out.println("Favorite added successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error adding favorite: " + e.getMessage());
            }
        } else {
            System.out.println("User or Video does not exist.");
        }
        detail(req, resp);
    }


    void deleteFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String username = (String) req.getSession().getAttribute("username");

        service.deleteFavorite(id, username);
        System.out.println("Favorite added successfully!");
        detail(req, resp);
    }


    void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = service.getById(id);
        String username = (String) req.getSession().getAttribute("username");

        if (userService.CheckStatus(username, id)) {
            req.setAttribute("status", true);
        } else {
            req.setAttribute("status", false);
        }

        req.setAttribute("video", video);
        req.getRequestDispatcher("/view/detail.jsp").forward(req, resp);
    }
}
