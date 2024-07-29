import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DatabaseUtils;

@WebServlet("/finalizeExchange")
public class FinalizeExchangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId1 = Integer.parseInt(request.getParameter("itemId1"));
        int itemId2 = Integer.parseInt(request.getParameter("itemId2"));

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "UPDATE exchanges SET status = 'Confirmed' WHERE item_id1 = ? AND item_id2 = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId1);
                statement.setInt(2, itemId2);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Database error finalizing exchange", e);
        }

        response.sendRedirect("listItems");
    }
}
