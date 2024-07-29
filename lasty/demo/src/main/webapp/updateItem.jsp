<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Item" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.DatabaseUtils" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Item</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #ece9e6, #ffffff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="file"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Item</h2>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            Item item = null;

            try (Connection connection = DatabaseUtils.getConnection()) {
                String sql = "SELECT * FROM items WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            item = new Item();
                            item.setId(resultSet.getInt("id"));
                            item.setCategory(resultSet.getString("category"));
                            item.setName(resultSet.getString("name"));
                            item.setFeatures(resultSet.getString("features"));
                            item.setDescription(resultSet.getString("description"));
                            item.setCondition(resultSet.getString("condittion"));
                        }
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Database error retrieving item", e);
            }
        %>
        <form action="updateItem" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= item.getId() %>">
            <label for="category">Category:</label>
            <input type="text" id="category" name="category" value="<%= item.getCategory() %>" required>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= item.getName() %>" required>
            <label for="features">Features:</label>
            <input type="text" id="features" name="features" value="<%= item.getFeatures() %>" required>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="<%= item.getDescription() %>" required>
            <label for="condition">Condition:</label>
            <input type="text" id="condition" name="condition" value="<%= item.getCondition() %>" required>
            <label for="photo">Photo:</label>
            <input type="file" id="photo" name="photo">
            <button type="submit">Update Item</button>
        </form>
    </div>
</body>
</html>
