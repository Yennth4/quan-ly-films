package com.yennth.assignment.controller.admin;

import com.yennth.assignment.entity.User;
import com.yennth.assignment.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/user","/add-user","/update-user","/delete-user" })
public class UserServlet extends HttpServlet {
    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri= req.getRequestURI();
        if(uri.contains("/update-user")){
            viewUpdate(req,resp);
        }
        else if (uri.contains("/delete-user")) {
            deleteUser(req, resp);
        }

        else if (uri.contains("/add-user")) {
            viewAdd(req, resp);
        }
        else {
            listUser(req, resp);
        }
    }

    void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list" ,service.getAll());
        req.getRequestDispatcher("user/user.jsp").forward(req,resp);
    }

    void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("user",service.getById(id));
        req.getRequestDispatcher("user/_update.jsp").forward(req,resp);
    }
    void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/_form.jsp").forward(req,resp);
    }

    void addUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String id = req.getParameter("id");
        User u = service.getById(id);
        if(u!=null){
            req.setAttribute("message","ID đã tồn tại");
            req.getRequestDispatcher("user/_form.jsp").forward(req,resp);
            return;
        }
        User user= new User(req.getParameter("id"),req.getParameter("password"),req.getParameter("fullname"),req.getParameter("email"),req.getParameter("admin")!=null);
        service.create(user);
        resp.sendRedirect("user");
    }
    void deleteUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String id = req.getParameter("id");
        if( service.checkDeleteUser(id)){
            req.setAttribute("error","user đang nối với bảng favorite");
            listUser(req,resp);
        }
        else {
            service.deleteById(id);
            System.out.printf("ok");
            resp.sendRedirect("user");
        }


    }

    void updateUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String id = req.getParameter("id");
        User user = service.getById(id);
        if(user != null){
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            user.setFullname(req.getParameter("fullname"));
            user.setAdmin(req.getParameter("admin")!=null);
            service.update(user);
        }
        resp.sendRedirect("user");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri= req.getRequestURI();
        if(uri.contains("/update-user")){
            updateUser(req,resp);
        }
        else if(uri.contains("/delete-user")){
            deleteUser(req,resp);
        }
        else {
            addUser(req, resp);
        }
    }
}


