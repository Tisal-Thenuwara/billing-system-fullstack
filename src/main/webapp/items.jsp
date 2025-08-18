<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Item Management</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.html'">← Back</button>
<div class="card">
  <h2>Item Management</h2>

  <!-- Add Item Form -->
  <form>
    <input type="text" placeholder="Item Code" required>
    <input type="text" placeholder="Item Name" required>
    <input type="number" placeholder="Price" required>
    <button type="submit">Add Item</button>
  </form>

  <hr>

  <!-- Item Table -->
  <table>
    <thead>
    <tr>
      <th>Code</th>
      <th>Name</th>
      <th>Price</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>I001</td>
      <td>Notebook</td>
      <td>150.00</td>
      <td>
        <button>Edit</button>
        <button>Delete</button>
      </td>
    </tr>
    </tbody>
  </table>
</div>

<script>
async function add(){
  const payload = {itemName:name.value, pricePerUnit:+price.value};
  const res = await fetch('api/items', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
  alert(await res.text()); loadList();
}
async function update(){
  const payload = {itemName:name.value, pricePerUnit:+price.value};
  const res = await fetch('api/items/'+id.value, {method:'PUT', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
  alert(await res.text()); loadList();
}
async function del(){
  const res = await fetch('api/items/'+id.value, {method:'DELETE'});
  alert(await res.text()); loadList();
}
async function loadList(){
  const res = await fetch('api/items');
  const data = await res.json();
  const tbody = document.querySelector('#tbl tbody');
  tbody.innerHTML = '';
  data.forEach(it => {
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${it.itemId}</td><td>${it.itemName}</td><td>${it.pricePerUnit}</td>`;
    tbody.appendChild(tr);
  });
}
loadList();
</script>
</body>
</html>
