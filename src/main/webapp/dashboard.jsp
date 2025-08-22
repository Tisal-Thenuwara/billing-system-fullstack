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
      <div class="tile" onclick="location.href='customers.jsp'">Customers</div>
      <div class="tile" onclick="location.href='items.jsp'">Items</div>
      <div class="tile" onclick="location.href='bill.jsp'">Generate Bills</div>
      <div class="tile" onclick="location.href='transaction.jsp'">Transaction History</div>
      <div class="tile" onclick="location.href='help.jsp'">Help</div>
    </div>
    <br>
    <button class="back-btn" onclick="location.href='index.jsp'">Logout</button>
  </div>
</div>
</body>
</html>

