<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Item</title>
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
            width: 50%;
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

        form {
            display: flex;
            flex-direction: column;
        }

        label, input, button {
            margin-bottom: 10px;
        }

        input, button {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            text-align: center;
            margin-bottom: 20px;
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
        <h2>Add Item</h2>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p class="error"><%= error %></p>
        <%
            }
        %>
        <form action="addItem" method="post" enctype="multipart/form-data">
            <label for="category">Category:</label>
            <input type="text" id="category" name="category" required>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="features">Features:</label>
            <input type="text" id="features" name="features" required>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required>

            <label for="condition">Condition:</label>
            <input type="text" id="condition" name="condition" required>

            <label for="photo">Photo (Max 5MB):</label>
            <input type="file" id="photo" name="photo" required>

            <button type="submit">Add Item</button>
        </form>
    </div>
</body>
</html>
