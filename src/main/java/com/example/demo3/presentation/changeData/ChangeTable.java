package com.example.demo3.presentation.changeData;

import com.example.demo3.data.CrudOperations;
import com.example.demo3.data.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/change")
public class ChangeTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("userRegister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("update".equals(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String query = "SELECT * FROM users WHERE id =" + userId;
            List<User> userList = CrudOperations.read(query);
            req.setAttribute("userList", userList);

            req.getRequestDispatcher("userUpdate.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String query = "DELETE FROM users WHERE id = " + userId;
            CrudOperations.delete(query);

            resp.sendRedirect("/demo3_war_exploded/usersList");
        } else if ("search".equals(action)) {
            String searchText = req.getParameter("searchText");
            String query = "SELECT * FROM users WHERE first_name LIKE '%" + searchText + "%' OR last_name LIKE '%" + searchText + "%' OR login LIKE '%" + searchText + "%'";
            List<User> userList = CrudOperations.read(query);
            if (userList.isEmpty()){
                req.setAttribute("hint", "User not found. Try write something else");
            }
            req.setAttribute("userList", userList);

            req.getRequestDispatcher("usersList.jsp").forward(req, resp);
        }
    }
}
