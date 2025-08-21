<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Pahana Edu - Login</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
<div class="card">
    <h2>Pahana Edu Login</h2>
    <form id="loginForm">
      <input type="text" placeholder="Username" name="username" id="username" required>
      <input type="password" placeholder="Password" name="password" id="password" required>
        <br>
      <button type="button" id="loginButton">Login</button>
    </form>
</div>

<script>
document.getElementById("loginButton").addEventListener("click", async function () {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("<%= request.getContextPath() %>/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    });

    if (response.ok) {
        const data = await response.json();
        alert(data.message);
        
        // Redirect to the dashboard
        window.location.href = "<%= request.getContextPath() %>/dashboard.jsp";
    } else {
        const error = await response.json();
        alert(error.error);
    }
});
</script>
</div>
</body>
</html>