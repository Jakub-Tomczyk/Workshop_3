package pl.coderslab.workshop3.servlet;

import pl.coderslab.workshop3.db.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet (name = "example", urlPatterns = "/example")
public class ExampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // implementujemy metodę
        PrintWriter responeWriter = response.getWriter();
        try {
            Connection connection =  DbUtil.getConnection(); // przygotowanie połączenia z bazą danych
            PreparedStatement preparedStatement =  connection.prepareStatement("SELECT * FROM users"); // do łączenia się z bazą danych z tabelą users i wyświeltniea
            ResultSet resultSet = preparedStatement.executeQuery(); // do wyświetlenia tych wyników
            while (resultSet.next()) {
                responeWriter.println(resultSet.getString("username")); // do wypisywania username w tabeli users, tak aby wypisało wszystkich takich użytkowników

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
