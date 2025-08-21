<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.jsp'">← Back</button>
<div class="container">
    <div class="card">
        <h2>Bills Management</h2>

        <table>
            <thead>
            <tr>
                <th>Bill ID</th>
                <th>Customer ID</th>
                <th>Item ID</th>
                <th>Units</th>
                <th>Total Amount</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <hr>
        <!-- Modal for Viewing a Bill -->
        <div id="bill-modal" style="display:none; border: 1px solid #ccc; padding: 10px;">
            <h3>Bill Details</h3>
            <p id="bill-details"></p>
            <button onclick="closeModal()">Close</button>
        </div>
    </div>
</div>
<script>
    async function loadBills() {
        try {
            const res = await fetch('<%= request.getContextPath() %>/api/bills');
            if (!res.ok) {
                throw new Error('Failed to fetch bills.');
            }
            const bills = await res.json();
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = ''; // Clear existing rows

            if (!Array.isArray(bills) || bills.length === 0) {
                tbody.innerHTML = '<tr><td colspan="6">No bills available.</td></tr>';
                return;
            }

            bills.forEach(bill => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
          <td>\${bill.billId ?? ''}</td>
          <td>\${bill.accountNo ?? ''}</td>
          <td>\${bill.itemId ?? ''}</td>
          <td>\${bill.units ?? ''}</td>
          <td>\${bill.total ?? ''}</td>
          <td>
            <button onclick="viewBill(\${bill.billId})">View</button>
            <button onclick="deleteBill(\${bill.billId})">Delete</button>
          </td>
        `;
                tbody.appendChild(tr);
            });
        } catch (error) {
            alert('Error loading bills. Check console for details.');
            console.error(error);
        }
    }

    async function viewBill(billId) {
        try {
            const res = await fetch('<%= request.getContextPath() %>/api/bills/' + billId);
            if (!res.ok) {
                throw new Error('Failed to fetch bill details.');
            }

            const bill = await res.json();
            const billDetails = `
        <p><strong>Bill ID:</strong> \${bill.billId}</p>
        <p><strong>Customer ID:</strong> \${bill.accountNo}</p>
        <p><strong>Item ID:</strong> \${bill.itemId}</p>
        <p><strong>Amount:</strong> \${bill.units}</p>
        <p><strong>Total Amount:</strong> \${bill.total}</p>
      `;

            document.getElementById('bill-details').innerHTML = billDetails;
            document.getElementById('bill-modal').style.display = 'block';
        } catch (error) {
            alert('Error viewing bill. Check console for details.');
            console.error(error);
        }
    }

    async function deleteBill(billId) {
        if (!confirm('Are you sure you want to delete this bill?')) return;

        try {
            const res = await fetch('<%= request.getContextPath() %>/api/bills/' + billId, {method: 'DELETE'});
            if (res.ok) {
                alert('Bill deleted successfully!');
                loadBills(); // Refresh the table
            } else {
                const errorMessage = await res.text();
                alert('Failed to delete bill: ' + errorMessage);
            }
        } catch (error) {
            alert('Error deleting bill. Check console for details.');
            console.error(error);
        }
    }

    function closeModal() {
        document.getElementById('bill-modal').style.display = 'none';
    }

    // Load bills on page load
    document.addEventListener('DOMContentLoaded', loadBills);
</script>
</body>
</html>
