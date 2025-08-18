<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 18/08/2025
  Time: 10:43 am
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Pahana Edu - Dashboard</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
  <div class="card">
    <h2>Welcome, User</h2>
    <div class="tile-container">
      <div class="tile" onclick="location.href='customers.html'">Customers</div>
      <div class="tile" onclick="location.href='items.html'">Items</div>
      <div class="tile" onclick="location.href='help.html'">Help</div>
    </div>
    <br>
    <button class="back-btn" onclick="location.href='login.html'">Logout</button>
  </div>
</div>
</body>
</html>

