package com.yennth.assignment.controller.admin;

import com.yennth.assignment.entity.Video;
import com.yennth.assignment.service.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/video", "/add-video", "/update-video", "/delete-video"})
public class VideoServlet extends HttpServlet {
    VideoService service = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/update-video")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/delete-video")) {
            deleteVideo(req, resp);
        } else if (uri.contains("/add-video")) {
            viewAdd(req, resp);
        } else {
            listVideo(req, resp);
        }
    }

    void listVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", service.getAll());
        req.getRequestDispatcher("QLVideo/movie.jsp").forward(req, resp);

    }

    void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("video", service.getById(id));
        req.getRequestDispatcher("QLVideo/_update.jsp").forward(req, resp);
    }

    void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("QLVideo/_form.jsp").forward(req, resp);
    }


    void addVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video m = service.getById(id);
        if (m != null) {
            req.setAttribute("message", "ID đã tồn tại");
            req.getRequestDispatcher("QLVideo/_form.jsp").forward(req, resp);
            return;
        }
        Video movie = new Video();

        movie.setId(req.getParameter("id"));
        movie.setTitle(req.getParameter("title"));
        movie.setDescription(req.getParameter("description"));
        movie.setActive(req.getParameter("active") != null);
        movie.setPoster(req.getParameter("poster"));
        service.create(movie);
        resp.sendRedirect("video");
    }

    void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String id = req.getParameter("id");
        if (service.checkDeleteUser(id)) {
            req.setAttribute("error", "video đang nối với bảng favorite");
            listVideo(req, resp);
        } else {
            service.deleteById(id);
            System.out.printf("ok");
            resp.sendRedirect("video");
        }

    }

    void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Video movie = service.getById(id);
        if (movie != null) {
            movie.setId(req.getParameter("id"));
            movie.setTitle(req.getParameter("title"));
            movie.setDescription(req.getParameter("description"));
            movie.setActive(req.getParameter("active") != null);
            movie.setPoster(req.getParameter("poster"));
            service.update(movie);
        }
        resp.sendRedirect("video");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/update-video")) {
            updateVideo(req, resp);
        } else if (uri.contains("/delete-video")) {
            deleteVideo(req, resp);
        } else if (uri.contains("/add-video")) {
            addVideo(req, resp);
        } else {
            addVideo(req, resp);
        }
    }
}


