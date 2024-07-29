import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DatabaseUtils;

@WebServlet("/updateItem")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Limit to 5MB
public class UpdateItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String features = request.getParameter("features");
        String description = request.getParameter("description");
        String condition = request.getParameter("condition");
        Part photoPart = request.getPart("photo");

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql;
            if (photoPart != null && photoPart.getSize() > 0) {
                try (InputStream photoInputStream = photoPart.getInputStream()) {
                    sql = "UPDATE items SET category=?, name=?, features=?, description=?, condittion=?, photo=? WHERE id=?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, category);
                        statement.setString(2, name);
                        statement.setString(3, features);
                        statement.setString(4, description);
                        statement.setString(5, condition);
                        statement.setBinaryStream(6, photoInputStream, (int) photoPart.getSize());
                        statement.setInt(7, id);
                        statement.executeUpdate();
                    }
                }
            } else {
                sql = "UPDATE items SET category=?, name=?, features=?, description=?, condittion=? WHERE id=?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, category);
                    statement.setString(2, name);
                    statement.setString(3, features);
                    statement.setString(4, description);
                    statement.setString(5, condition);
                    statement.setInt(6, id);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error updating item", e);
        }

        response.sendRedirect("listItems");
    }
}
