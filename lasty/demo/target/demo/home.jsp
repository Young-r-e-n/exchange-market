<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(45deg, #f3ec78, #af4261);
            background-size: 400% 400%;
            animation: gradientBackground 15s ease infinite;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        @keyframes gradientBackground {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .home-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        a {
            display: block;
            margin: 10px 0;
            color: #4CAF50;
            text-decoration: none;
            font-size: 18px;
        }

        a:hover {
            color: #388E3C;
        }

        .logout {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }

        .logout:hover {
            background-color: #d32f2f;
        }
        .logo-container {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}
.logo {
    max-width: 100px;
}

    </style>
</head>
<body>
    <div class="home-container">
        <h2>Welcome to the Exchange Agency Platform</h2>
        <div class="logo-container">
            <img src="logo.jpg" alt="Logo" class="logo">
        </div>
        
        <div class="nav-bar">
            <a href="index.jsp">Home</a>
            <a href="addItem.jsp">Add Item</a>
            <a href="listItems">List Items</a>
            <a href="prediction.jsp">Predict Item Category</a>
            <a href="confirmExchanges.jsp">confirm Exchange</a>
            <a href="logout">Logout</a>
        </div>
    </div>
</body>
</html>
