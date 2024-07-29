<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.Item, java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Exchange</title>
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

        .button.cancel {
            background-color: #f44336;
        }

        .button.cancel:hover {
            background-color: #e31e1e;
        }
    </style>
</head>
<body>
    <div class="nav-bar">
        <a href="index.jsp">Home</a>
        <a href="addItem.jsp">Add Item</a>
        <a href="listItems">List Items</a>
        <a href="prediction.jsp">Predict Item Category</a>
        <a href="confirmExchanges.jsp">confirm Exchange</a>
        <a href="logout">Logout</a>
    </div>
    <div class="container">
        <h2>Confirm Exchange</h2>
        <p>Are you sure you want to exchange these items?</p>
        <form action="finalizeExchange" method="post">
            <input type="hidden" name="itemId1" value="<%= request.getParameter("itemId1") %>">
            <input type="hidden" name="itemId2" value="<%= request.getParameter("itemId2") %>">
            <button type="submit" class="button">Confirm Exchange</button>
            <a href="listItems" class="button cancel">Cancel</a>
        </form>
    </div>
</body>
</html>
