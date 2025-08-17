<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customers - Pahana Edu</title>
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
<h2>Customer Management</h2>

<div class="row">
  <input id="acc" type="number" placeholder="Account No"/>
  <input id="name" type="text" placeholder="Name"/>
  <input id="addr" type="text" placeholder="Address"/>
  <input id="phone" type="text" placeholder="Phone"/>
  <input id="units" type="number" placeholder="Units"/>
  <button onclick="add()">Add</button>
  <button onclick="update()">Update</button>
</div>

<button onclick="loadList()">Refresh List</button>

<table id="tbl">
  <thead><tr><th>Account</th><th>Name</th><th>Address</th><th>Phone</th><th>Units</th><th>Bill</th></tr></thead>
  <tbody></tbody>
</table>

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
