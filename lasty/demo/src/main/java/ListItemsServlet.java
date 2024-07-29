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

@WebServlet("/listItems")
public class ListItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Integer currentUserId = (Integer) session.getAttribute("userId");
            request.setAttribute("userId", currentUserId);
        }

        String selectedCategory = request.getParameter("category");
        List<Item> items = new ArrayList<>();

        String sql = "SELECT id, user_id, category, name, features, description, condittion, photo FROM items";
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            sql += " WHERE category = ?";
        }

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                statement.setString(1, selectedCategory);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setUserId(resultSet.getInt("user_id"));
                    item.setCategory(resultSet.getString("category"));
                    item.setName(resultSet.getString("name"));
                    item.setFeatures(resultSet.getString("features"));
                    item.setDescription(resultSet.getString("description"));
                    item.setCondition(resultSet.getString("condittion"));
                    item.setPhoto(resultSet.getBytes("photo"));

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving items", e);
        }

        request.setAttribute("items", items);
        request.getRequestDispatcher("listItems.jsp").forward(request, response);
    }
}
