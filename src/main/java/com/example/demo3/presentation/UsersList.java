package com.example.demo3.presentation;

import com.example.demo3.data.CrudOperations;
import com.example.demo3.data.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usersList")
public class UsersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = "SELECT * FROM users";
        List<User> userList = CrudOperations.read(query);
        int count = (userList.size() + 9) / 10; // Розрахувати кількість сторінок

        if (req.getParameter("page") != null) {
            int page = Integer.parseInt(req.getParameter("page"));

            int pageSize = 10;
            int startIdx = (page - 1) * pageSize;

            int endIdx = Math.min(startIdx + pageSize, userList.size());
            if (startIdx > userList.size()) {
                req.getRequestDispatcher("errorLogin.jsp").forward(req, resp);
            }

            List<User> usersToDisplay = userList.subList(startIdx, endIdx);
            req.setAttribute("currentPage", page);

            req.setAttribute("pageCount", count);
            System.out.println(count);
            req.setAttribute("userList", usersToDisplay);
        } else {
            List<User> usersToDisplay = userList.subList(0, 9);
            req.setAttribute("userList", usersToDisplay);

            req.setAttribute("pageNum", 5);

            req.setAttribute("currentPage", 1); // Встановимо початкову сторінку як 1
            req.setAttribute("pageCount", count);
        }

        req.getRequestDispatcher("usersList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sortColumn = req.getParameter("sortColumn");
        String sortOrder = req.getParameter("sortOrder");
        String query = "SELECT * FROM users ORDER BY " + sortColumn + " " + sortOrder;


        boolean sortOrderAscending = sortColumn != null && sortOrder != null && sortOrder.equals("ASC");

        // Зберігаємо оновлений стан сортування в сесії
        HttpSession session = req.getSession();
        session.setAttribute("sortOrderAscending", sortOrderAscending);


        List<User> userList = CrudOperations.read(query);
        req.setAttribute("userList", userList);

        req.getRequestDispatcher("usersList.jsp").forward(req, resp);
    }
}
