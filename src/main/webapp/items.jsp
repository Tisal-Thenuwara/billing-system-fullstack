<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Item Management</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.jsp'">← Back</button>
<div class="container">
<div class="card">
  <h2>Item Management</h2>

  <!-- Add Item Form -->
  <form>
    <input type="text" id="itemCode" placeholder="Item Code" disabled>
    <input type="text" id="itemName" placeholder="Item Name" required>
    <input type="number" id="price" placeholder="Price" required>
    <br>
    <button type="button" id="saveBtn" onclick="add()">Add Item</button>
    <button type="button" id="updateBtn" style="display: none; justify-content: center" onclick="update()">Update
    </button>
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
    </tr>
    </tbody>
  </table>
</div>
</div>
<script>
async function add(){
  const payload = {itemName: itemName.value, pricePerUnit: price.value};
  const res = await fetch('<%= request.getContextPath() %>/api/items', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
  alert(await res.text()); loadList();
}

async function update(){
  const payload = {itemId: itemCode.value, itemName: itemName.value, pricePerUnit: price.value};
  const res = await fetch('<%= request.getContextPath() %>/api/items/' + itemCode.value, {
    method: 'PUT',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(payload)
  });
  alert(await res.text());

  document.getElementById('saveBtn').style.display = 'inline-block'; // Show Save button
  document.getElementById('updateBtn').style.display = 'none'; // Hide Update button

  document.getElementById('itemCode').value = '';
  document.getElementById('itemName').value = '';
  document.getElementById('price').value = '';


  loadList();
}

async function editItem(id) {
  const res = await fetch('<%= request.getContextPath() %>/api/items/' + id, {
    method: 'GET',
    headers: {'Content-Type': 'application/json'}
  });
  const item = await res.json();

  console.log("getting customer:" + item)
  // Populate form for editing
  document.getElementById('itemCode').value = item.itemId;
  document.getElementById('itemName').value = item.itemName;
  document.getElementById('price').value = item.pricePerUnit;

  document.getElementById('saveBtn').style.display = 'none';
  document.getElementById('updateBtn').style.display = 'inline-block';

  alert('Edit the customer details and submit to save changes.');
}

async function del(id) {
  const res = await fetch('<%= request.getContextPath() %>/api/items/' + id, {method: 'DELETE'});
  alert(await res.text()); loadList();
}
async function loadList(){

  try {
    const res = await fetch('<%= request.getContextPath() %>/api/items');
    if (!res.ok) {
      throw new Error(`Failed to fetch data. Status: ${res.status}`);
    }
    const data = await res.json();

    const tbody = document.querySelector('table tbody');
    if (!tbody) {
      return;
    }

    tbody.innerHTML = ''; // Clear existing rows (if any)

    if (!Array.isArray(data) || data.length === 0) {
      tbody.innerHTML = '<tr><td colspan="8">No Items available</td></tr>';
      return;
    }

    data.forEach((item) => {

      const tr = document.createElement('tr');
      tr.innerHTML = `
  <td>\${item.itemId ?? ''}</td>
  <td>\${item.itemName ?? ''}</td>
  <td>\${item.pricePerUnit ?? ''}</td>
    <td>
      <button onclick="editItem(\${item.itemId})">Edit</button>
      <button onclick="del(\${item.itemId})">Delete</button>
    </td>
  `;
      tbody.appendChild(tr);
    });
  } catch (error) {
    alert('An error occurred while loading Items. Check the console for details.');
  }

  const res = await fetch('<%= request.getContextPath() %>/api/items');
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