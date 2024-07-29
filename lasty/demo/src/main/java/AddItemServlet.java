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

@WebServlet("/addItem")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Limit to 5MB
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String features = request.getParameter("features");
        String description = request.getParameter("description");
        String condition = request.getParameter("condition");
        Part photoPart = request.getPart("photo");

        if (category == null || category.isEmpty() ||
            name == null || name.isEmpty() ||
            features == null || features.isEmpty() ||
            description == null || description.isEmpty() ||
            condition == null || condition.isEmpty() ||
            photoPart == null || photoPart.getSize() == 0) {

            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
            return;
        }

        if (photoPart.getSize() > 1024 * 1024 * 5) { // 5MB size limit
            request.setAttribute("error", "Photo size must be less than 5MB.");
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
            return;
        }

        try (InputStream photoInputStream = photoPart.getInputStream()) {
            try (Connection connection = DatabaseUtils.getConnection()) {
                String sql = "INSERT INTO items (category, name, features, description, condittion, photo) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, category);
                    statement.setString(2, name);
                    statement.setString(3, features);
                    statement.setString(4, description);
                    statement.setString(5, condition);
                    statement.setBinaryStream(6, photoInputStream, (int) photoPart.getSize());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error saving item", e);
        }

        response.sendRedirect("listItems");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addItem.jsp").forward(request, response);
    }
}
