<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.Exchange" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Exchanges</title>
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
        <a href="home.jsp">Home</a>
        <a href="addItem.jsp">Add Item</a>
        <a href="listItems">List Items</a>
        <a href="prediction.jsp">Predict Item Category</a>
        <a href="confirmExchanges.jsp">confirm Exchange</a>
        <a href="logout">Logout</a>
    </div>
    <div class="container">
        <h2>Confirm Exchanges</h2>
        <table>
            <thead>
                <tr>
                    <th>Requested Item</th>
                    <th>Your Item</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Exchange> pendingExchanges = (List<Exchange>) request.getAttribute("pendingExchanges");
                    if (pendingExchanges != null) {
                        for (Exchange exchange : pendingExchanges) {
                %>
                <tr>
                    <td><%= exchange.getItem1Name() %></td>
                    <td><%= exchange.getItem2Name() %></td>
                    <td><%= exchange.getStatus() %></td>
                    <td>
                        <form action="confirmExchanges" method="post">
                            <input type="hidden" name="exchangeId" value="<%= exchange.getId() %>">
                            <button type="submit" class="button">Confirm</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No pending exchanges found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <a href="listItems" class="button cancel">Back to List</a>
    </div>
</body>
</html>
