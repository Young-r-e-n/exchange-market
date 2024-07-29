import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DatabaseUtils;
import com.Item;

@WebServlet("/expressInterest")
public class ExpressInterestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("index.jsp?error=You must be logged in to express interest.");
            return;
        }

        int targetItemId = Integer.parseInt(request.getParameter("id"));
        List<Item> userItems = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "SELECT id, category, name, features, description, condittion, photo FROM items WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Item item = new Item();
                        item.setId(resultSet.getInt("id"));
                        item.setCategory(resultSet.getString("category"));
                        item.setName(resultSet.getString("name"));
                        item.setFeatures(resultSet.getString("features"));
                        item.setDescription(resultSet.getString("description"));
                        item.setCondition(resultSet.getString("condittion"));
                        item.setPhoto(resultSet.getBytes("photo"));
                        userItems.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving user items", e);
        }

        request.setAttribute("userItems", userItems);
        request.setAttribute("targetItemId", targetItemId);
        request.getRequestDispatcher("expressInterest.jsp").forward(request, response);
    }
}
