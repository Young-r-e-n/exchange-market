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
import com.Exchange;

@WebServlet("/confirmExchanges")
public class ConfirmExchangesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        List<Exchange> pendingExchanges = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "SELECT e.id, e.item_id1, e.item_id2, e.user1_id, e.status, i1.name AS item1_name, i2.name AS item2_name " +
                         "FROM exchanges e " +
                         "JOIN items i1 ON e.item_id1 = i1.id " +
                         "JOIN items i2 ON e.item_id2 = i2.id " +
                         "WHERE e.user2_id = ? AND e.status = 'Pending'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Exchange exchange = new Exchange();
                        exchange.setId(resultSet.getInt("id"));
                        exchange.setItemId1(resultSet.getInt("item_id1"));
                        exchange.setItemId2(resultSet.getInt("item_id2"));
                        exchange.setUser1Id(resultSet.getInt("user1_id"));
                        exchange.setStatus(resultSet.getString("status"));
                        exchange.setItem1Name(resultSet.getString("item1_name"));
                        exchange.setItem2Name(resultSet.getString("item2_name"));
                        pendingExchanges.add(exchange);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving pending exchanges", e);
        }

        request.setAttribute("pendingExchanges", pendingExchanges);
        request.getRequestDispatcher("confirmExchanges.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int exchangeId = Integer.parseInt(request.getParameter("exchangeId"));

        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "UPDATE exchanges SET status = 'Confirmed' WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, exchangeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Database error updating exchange status", e);
        }

        response.sendRedirect("confirmExchanges");
    }
}
