<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Pahana Edu Billing - Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <style>
    body { font-family: Arial, sans-serif; margin: 2rem; }
    .card { max-width: 420px; margin: auto; padding: 1.5rem; border: 1px solid #ddd; border-radius: 12px; }
    input, button { width: 100%; padding: .7rem; margin-top: .6rem; }
    .nav { margin: 1rem auto; max-width: 800px; display: flex; gap: .5rem; flex-wrap: wrap; }
    .nav a { padding: .6rem 1rem; border: 1px solid #ddd; border-radius: 8px; text-decoration: none; }
    .muted { color: #666; font-size: .9rem; }
  </style>
</head>
<body>
<div class="card">
  <h2>Login</h2>
  <form id="loginForm">
    <input type="text" id="username" placeholder="Username" required/>
    <input type="password" id="password" placeholder="Password" required/>
    <button type="submit">Sign In</button>
    <div id="msg" class="muted"></div>
  </form>
</div>

<div class="nav">
  <a href="customers.jsp">Customers</a>
  <a href="items.jsp">Items</a>
  <a href="help.jsp">Help</a>
</div>

<script>
document.getElementById('loginForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const res = await fetch('api/auth/login', {
    method: 'POST',
    headers: {'Content-Type':'application/json'},
    body: JSON.stringify({username: username.value, password: password.value})
  });
  const text = await res.text();
  document.getElementById('msg').textContent = text;
});
</script>
</body>
</html>
