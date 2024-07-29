<!DOCTYPE html>
<html>
<head>
    <title>Product Classification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #ece9e6, #ffffff);
            margin: 0;
            padding: 0;
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
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
            max-width: 500px;
            flex: 1;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        textarea {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
            display: block;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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
        <!-- <h1>Product Classification</h1>
        <form action="classify" method="post">
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required><br><br>

            <label for="features">Product Features:</label><br>
            <textarea id="features" name="features" rows="4" required></textarea><br><br>

            <input type="submit" value="Classify">
        </form> -->

        <h1>Classify Item</h1>
    <form action="classifyItem" method="post">
        <label for="item_name">Item Name:</label>
        <input type="text" id="item_name" name="item_name" required><br><br>

        <label for="brand">Brand:</label>
        <input type="text" id="brand" name="brand" required><br><br>

        <label for="ram">RAM:</label>
        <input type="text" id="ram" name="ram"><br><br>

        <label for="model">Model:</label>
        <input type="text" id="model" name="model"><br><br>

        <label for="colour">Colour:</label>
        <input type="text" id="colour" name="colour" required><br><br>

        <label for="size">Size:</label>
        <input type="text" id="size" name="size"><br><br>

        <label for="type">Type:</label>
        <input type="text" id="type" name="type"><br><br>

        <button type="submit">Classify</button>
    </form>
    </div>
</body>
</html>
