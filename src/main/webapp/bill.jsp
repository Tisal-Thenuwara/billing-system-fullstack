<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bills</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<button class="back-btn" onclick="location.href='dashboard.jsp'">← Back</button>
<div class="container">
    <div class="card">
        <h2>Bill Generation</h2>
        <form>
            <div>
                <label for="customerSelect">Select Customer:</label>
                <select id="customerSelect" required>
                    <option value="" disabled selected>Select a customer</option>
                    <!-- Options will be dynamically populated -->
                </select>

                <label for="itemSelect">Select Item:</label>
                <select id="itemSelect" required>
                    <option value="" disabled selected>Select an item</option>
                    <!-- Options will be dynamically populated -->
                </select>
            </div>
            <div>
                <input type="text" id="amount" placeholder="Amount" required>
                <input type="text" id="price" placeholder="Price" disabled>
                <input type="text" id="total" placeholder="Total" disabled>
            </div>
            <button type="button" onclick="generateBill()">Generate Bill</button>
        </form>
        <hr>
        <div id="bill-summary">
            <!-- Bill summary will be dynamically generated here -->
        </div>
        <br>
        <button type="button" id="save" style="display: none; justify-content: center" onclick="saveBill()">Save Bill
        </button>
        <button class="back-btn" onclick="location.href='dashboard.jsp'">Back</button>
    </div>
</div>

<script>
    // Function to fetch customers and populate the dropdown
    async function populateCustomers() {
        try {
            const response = await fetch('<%= request.getContextPath() %>/api/customers');
            if (!response.ok) throw new Error('Failed to fetch customers');
            const customers = await response.json();

            const customerSelect = document.getElementById('customerSelect');
            customers.forEach(customer => {
                const option = document.createElement('option');
                option.value = customer.accountNo; // Assuming customers have an 'id' property
                option.textContent = customer.name; // Assuming customers have a 'name' property
                customerSelect.appendChild(option);
            });
        } catch (error) {
            console.error('Error fetching customers:', error);
            alert('Unable to load customers.');
        }
    }

    let itemDetails = []; // This will store the item data (id, price, and name)

    async function populateItems() {
        try {
            const response = await fetch('<%= request.getContextPath() %>/api/items');
            if (!response.ok) throw new Error('Failed to fetch items');
            const items = await response.json();

            const itemSelect = document.getElementById('itemSelect');
            items.forEach(item => {
                const option = document.createElement('option');
                option.value = item.itemId;
                option.textContent = item.itemName;
                itemSelect.appendChild(option);

                itemDetails[item.itemId] = item;
            });
        } catch (error) {
            console.error('Error fetching items:', error);
            alert('Unable to load items.');
        }
    }

    document.getElementById('itemSelect').addEventListener('change', (event) => {
        const selectedItem = itemDetails[event.target.value];
        if (selectedItem) {
            const priceField = document.getElementById('price');
            const totalField = document.getElementById('total');
            const amountField = document.getElementById('amount');

            priceField.value = selectedItem.pricePerUnit;
            amountField.value = 0;
            totalField.value = 0;
        }
    });

    document.getElementById('amount').addEventListener('input', (event) => {
        const amount = parseInt(event.target.value, 10);
        const price = parseFloat(document.getElementById('price').value);

        if (!isNaN(amount) && !isNaN(price)) {
            const totalField = document.getElementById('total');
            totalField.value = (amount * price).toFixed(2);
        }
    });

    document.addEventListener('DOMContentLoaded', () => {
        populateCustomers();
        populateItems();
    });

    function generateBill() {
        const customerSelect = document.getElementById('customerSelect');
        const itemSelect = document.getElementById('itemSelect');
        const amount = document.getElementById('amount').value;
        const price = document.getElementById('price').value;
        const total = document.getElementById('total').value;

        if (!customerSelect.value) {
            alert('Please select a customer.');
            return;
        }
        if (!itemSelect.value) {
            alert('Please select an item.');
            return;
        }
        if (!amount || isNaN(amount) || parseFloat(amount) <= 0) {
            alert('Please enter a valid amount greater than 0.');
            return;
        }

        const selectedCustomer = customerSelect.options[customerSelect.selectedIndex].text;
        const selectedCustomerId = customerSelect.value;
        const selectedItem = itemSelect.options[itemSelect.selectedIndex].text;
        const selectedItemId = itemSelect.value;

        const summaryDiv = document.getElementById('bill-summary');
        summaryDiv.innerHTML = `
    <h3>Bill Details</h3>
    <p><strong>Customer Name:</strong> \${selectedCustomer}</p>
    <p><strong>Customer Account No:</strong> \${selectedCustomerId}</p>
    <p><strong>Item:</strong> \${selectedItem}</p>
    <p><strong>Item ID:</strong> \${selectedItemId}</p>
    <p><strong>Amount:</strong> \${amount}</p>
    <p><strong>Price per Unit:</strong> \${price}</p>
    <p><strong>Total Amount:</strong> \${total}</p>
  `;
        document.getElementById('save').style.display = 'inline-block';
    }

    function saveBill() {
        const customerSelect = document.getElementById('customerSelect');
        const itemSelect = document.getElementById('itemSelect');
        const amount = document.getElementById('amount').value;
        const price = document.getElementById('price').value;
        const total = document.getElementById('total').value;

        const selectedCustomerId = customerSelect.value;
        const selectedItemId = itemSelect.value;

        if (!selectedCustomerId || !selectedItemId || !amount || isNaN(amount) || parseFloat(amount) <= 0) {
            alert('Please ensure all fields are filled correctly.');
            return;
        }

        const bill = {
            accountNo: selectedCustomerId,
            itemId: selectedItemId,
            units: parseInt(amount),
            total: parseFloat(total),
            pricePerUnit: parseFloat(price)
        };

        fetch('<%= request.getContextPath() %>/api/bills', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(bill),
        })
            .then(response => {
                if (response.ok) {
                    return response.json(); // Parse JSON response if the request succeeded
                } else {
                    return response.text().then(text => {
                        throw new Error(text); // Throw the error response as a message
                    });
                }
            })
            .then(data => {
                alert('Bill created successfully!');
                console.log('Created Bill:', data);
            })
            .catch(error => {
                console.error('Error creating bill:', error);
                alert('Failed to create bill.');
            });
    }
</script>
</body>
</html>