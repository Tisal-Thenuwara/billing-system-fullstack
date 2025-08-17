<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Items - Pahana Edu</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <style>
    body { font-family: Arial, sans-serif; margin: 2rem; }
    input, button { padding: .5rem; margin: .3rem; }
    table { border-collapse: collapse; width: 100%; margin-top: 1rem;}
    th, td { border: 1px solid #ddd; padding: .5rem; text-align: left;}
    .row { display:flex; flex-wrap: wrap; gap: .5rem; }
  </style>
</head>
<body>
<h2>Item Management</h2>
<div class="row">
  <input id="id" type="number" placeholder="ID (for update/delete)"/>
  <input id="name" type="text" placeholder="Item Name"/>
  <input id="price" type="number" step="0.01" placeholder="Price per Unit"/>
  <button onclick="add()">Add</button>
  <button onclick="update()">Update</button>
  <button onclick="del()">Delete</button>
</div>

<button onclick="loadList()">Refresh List</button>

<table id="tbl">
  <thead><tr><th>ID</th><th>Name</th><th>Price/Unit</th></tr></thead>
  <tbody></tbody>
</table>

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
