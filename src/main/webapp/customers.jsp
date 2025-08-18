<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer Management</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.html'">← Back</button>
<div class="card">
  <h2>Customer Management</h2>

  <!-- Add Customer Form -->
  <form>
    <input type="text" placeholder="Account Number" required>
    <input type="text" placeholder="Name" required>
    <input type="text" placeholder="Address" required>
    <input type="text" placeholder="Telephone" required>
    <button type="submit">Add Customer</button>
  </form>

  <hr>

  <!-- Customer Table -->
  <table>
    <thead>
    <tr>
      <th>Account No</th>
      <th>Name</th>
      <th>Address</th>
      <th>Telephone</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>C001</td>
      <td>John Doe</td>
      <td>Colombo</td>
      <td>0771234567</td>
      <td>
        <button>Edit</button>
        <button>Delete</button>
      </td>
    </tr>
    </tbody>
  </table>
</div>

<%--<button onclick="add()">Add</button>--%>
<script>
async function add(){
  const payload = {accountNo:+acc.value, name:name.value, address:addr.value, phone:phone.value, unitsConsumed:+units.value};
  const res = await fetch('api/customers', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
  alert(await res.text());
  loadList();
}
async function update(){
  const payload = {name:name.value, address:addr.value, phone:phone.value, unitsConsumed:+units.value};
  const res = await fetch('api/customers/'+acc.value, {method:'PUT', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
  alert(await res.text());
  loadList();
}
async function bill(accountNo){
  const res = await fetch('api/billing/'+accountNo);
  alert(await res.text());
}
async function loadList(){
  const res = await fetch('api/customers');
  const data = await res.json();
  const tbody = document.querySelector('#tbl tbody');
  tbody.innerHTML = '';
  data.forEach(c => {
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${c.accountNo}</td><td>${c.name}</td><td>${c.address}</td><td>${c.phone}</td><td>${c.unitsConsumed}</td>
                    <td><button onclick="bill(${c.accountNo})">Generate</button></td>`;
    tbody.appendChild(tr);
  });
}
loadList();
</script>
</body>
</html>
