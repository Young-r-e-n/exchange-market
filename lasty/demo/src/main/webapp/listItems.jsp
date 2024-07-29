<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.Item, java.util.Base64, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #ece9e6, #ffffff);
            margin: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .nav-bar {
            background-color: #333;
            color: white;
            padding: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .nav-bar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
        }

        .nav-bar a:hover {
            text-decoration: underline;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .logout {
            display: inline-block;
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            text-align: center;
            margin-top: 20px;
            float: right;
        }

        .logout:hover {
            background-color: #d32f2f;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            text-align: center;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #45a049;
        }

        .button.update {
            background-color: #2196F3;
        }

        .button.update:hover {
            background-color: #1e88e5;
        }

        img {
            max-width: 100px;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="nav-bar">
        <a href="home.jsp">Home</a>
        <a href="addItem.jsp">Add Item</a>
        <a href="listItems">List Items</a>
        <a href="prediction.jsp">Predict Item Category</a>
        <a href="confirmExchanges.jsp">confirm Exchange</a>
        <a href="logout">Logout</a>
    </div>
    <div class="container">
        <h2>Items Available for Exchange</h2>
        <a class="logout" href="logout">Logout</a>
        
        <!-- Category Filter Form -->
        <form action="listItems" method="get">
            <label for="category">Select Category:</label>
            <select id="category" name="category">
                <option value="">All</option>
                <option value="Electronics">Electronics</option>
                <option value="Furniture">Furniture</option>
                <option value="Clothing">Clothing</option>
                <!-- Add more categories as needed -->
            </select>
            <button type="submit">Filter</button>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Features</th>
                    <th>Description</th>
                    <th>Condition</th>
                    <th>Photo</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Item> items = (List<Item>) request.getAttribute("items");
                    Integer currentUserId = (Integer) request.getAttribute("userId");
                    if (items != null) {
                        for (Item item : items) {
                %>
                <tr>
                    <td><%= item.getCategory() %></td>
                    <td><%= item.getName() %></td>
                    <td><%= item.getFeatures() %></td>
                    <td><%= item.getDescription() %></td>
                    <td><%= item.getCondition() %></td>
                    <td>
                        <%
                            byte[] photo = item.getPhoto();
                            if (photo != null && photo.length > 0) {
                        %>
                        <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(photo) %>" alt="Item Photo">
                        <%
                            } else {
                        %>
                        <span>No photo available</span>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <%
                            if (currentUserId != null && currentUserId.equals(item.getUserId())) {
                        %>
                        <a class="button update" href="updateItem.jsp?id=<%= item.getId() %>">Update</a>
                        <%
                            } else {
                        %>
                        <a class="button" href="expressInterest?id=<%= item.getId() %>">Exchange</a>
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
