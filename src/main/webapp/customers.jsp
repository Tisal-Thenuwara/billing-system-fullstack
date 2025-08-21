<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer Management</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.jsp'">← Back</button>
<div class="container">
  <div class="card">
    <h2>Customer Management</h2>

    <!-- Add Customer Form -->
    <form>
      <input type="text" id="acc" placeholder="Account Number" disabled>
      <input type="text" id="fName" placeholder="Name" required>
      <input type="text" id="addr1" placeholder="Address Line 1" required>
      <input type="text" id="addr2" placeholder="Address Line 2" required>
      <input type="text" id="phone" placeholder="Telephone" required>
      <input type="text" id="email" placeholder="Email" required>
      <input type="text" id="units" placeholder="Units Consumed">
      <br>
      <button type="button" id="saveBtn" onclick="add()">Save Customer</button>
      <button type="button" id="updateBtn" style="display: none; justify-content: center" onclick="update()">Update
      </button>

    </form>
    <%--  <button onclick="loadList()">Load All Customers</button>--%>
    <hr>

    <!-- Customer Table -->
    <table>
      <thead>
      <tr>
        <th>Account No</th>
        <th>Name</th>
        <th>Address Line 1</th>
        <th>Address Line 2</th>
        <th>Telephone</th>
        <th>Email</th>
        <th>Units</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
<script>

  async function add(){
    const payload = {name:fName.value, address1:addr1.value, address2:addr2.value, phone:phone.value, email:email.value, unitsConsumed:units.value};
    const res = await fetch('<%= request.getContextPath() %>/api/customers/save', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
    alert(await res.text());
    loadList();
  }
  async function update(){
    const payload = {name:fName.value, address1:addr1.value, address2:addr2.value, phone:phone.value, email:email.value, unitsConsumed:units.value};
    const res = await fetch('<%= request.getContextPath() %>/api/customers/'+acc.value, {method:'PUT', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
    alert(await res.text());

    document.getElementById('saveBtn').style.display = 'inline-block'; // Show Save button
    document.getElementById('updateBtn').style.display = 'none'; // Hide Update button

    document.getElementById('acc').value = '';
    document.getElementById('fName').value = '';
    document.getElementById('addr1').value = '';
    document.getElementById('addr2').value = '';
    document.getElementById('phone').value = '';
    document.getElementById('email').value = '';
    document.getElementById('units').value = '';


    loadList();
  }
  async function bill(accountNo){
    const res = await fetch('<%= request.getContextPath() %>/api/billing/'+accountNo);
    alert(await res.text());
  }

  async function loadList() {
    try {
      const res = await fetch('<%= request.getContextPath() %>/api/customers');
      if (!res.ok) {
        throw new Error(`Failed to fetch data. Status: ${res.status}`);
      }
      const data = await res.json();

      const tbody = document.querySelector('table tbody');
      if (!tbody) {
        return;
      }

      if (!tbody) {
        return;
      }

      tbody.innerHTML = ''; // Clear existing rows (if any)

      if (!Array.isArray(data) || data.length === 0) {
        tbody.innerHTML = '<tr><td colspan="8">No customers available</td></tr>';
        return;
      }

      data.forEach((customer) => {

        const tr = document.createElement('tr');
        tr.innerHTML = `
  <td>\${customer.accountNo ?? ''}</td>
  <td>\${customer.name ?? ''}</td>
  <td>\${customer.address1 ?? ''}</td>
  <td>\${customer.address2 ?? ''}</td>
  <td>\${customer.phone ?? ''}</td>
  <td>\${customer.email ?? ''}</td>
  <td>\${customer.unitsConsumed ?? 0}</td>
    <td>
      <button onclick="bill(\${customer.accountNo})">Generate</button>
      <button onclick="editCustomer(\${customer.accountNo})">Edit</button>
      <button onclick="deleteCustomer(\${customer.accountNo})">Delete</button>
    </td>
  `;
        tbody.appendChild(tr);
      });
    } catch (error) {
      alert('An error occurred while loading customers. Check the console for details.');
    }
  }

async function editCustomer(accountNo) {
  const res = await fetch('<%= request.getContextPath() %>/api/customers/'+ accountNo, {method:'GET', headers:{'Content-Type':'application/json'}});
  const customer = await res.json();

  console.log("getting customer:"+customer)
  // Populate form for editing
  document.getElementById('acc').value = customer.accountNo;
  document.getElementById('fName').value = customer.name;
  document.getElementById('addr1').value = customer.address1;
  document.getElementById('addr2').value = customer.address2;
  document.getElementById('phone').value = customer.phone;
  document.getElementById('email').value = customer.email;
  document.getElementById('units').value = customer.unitsConsumed;

  document.getElementById('saveBtn').style.display = 'none';
  document.getElementById('updateBtn').style.display = 'inline-block';

  alert('Edit the customer details and submit to save changes.');
}

async function deleteCustomer(accountNo) {
  if (!confirm('Are you sure you want to delete this customer?')) return;

  const res = await fetch('<%= request.getContextPath() %>/api/customers/' + accountNo, { method: 'DELETE' });

  if (res.ok) {
    alert('Customer deleted successfully!');
    loadList();
  } else {
    alert('Error deleting customer.');
  }
}

loadList();
</script>
</body>
</html>