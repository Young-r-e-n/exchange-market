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

import com.DatabaseUtils;

@WebServlet("/confirmExchange")
public class ConfirmExchangeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int currentUserId = (Integer) session.getAttribute("userId");
        int itemId1 = Integer.parseInt(request.getParameter("itemId1"));
        int itemId2 = Integer.parseInt(request.getParameter("itemId2"));

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO exchanges (item_id1, item_id2, user1_id, user2_id, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId1);
                statement.setInt(2, itemId2);
                statement.setInt(3, currentUserId);
                statement.setInt(4, getItemOwnerId(itemId2, connection));
                statement.setString(5, "Pending");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Database error inserting exchange", e);
        }

        response.sendRedirect("listItems");
    }

    private int getItemOwnerId(int itemId, Connection connection) throws SQLException {
        String sql = "SELECT user_id FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_id");
                } else {
                    throw new SQLException("Item owner not found");
                }
            }
        }
    }
}
