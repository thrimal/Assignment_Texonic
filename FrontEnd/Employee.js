const baseURL = 'http://localhost:8080/api/v1/employee';
let employeeId;

clearFields();

function submitForm() {
    let myForm = document.getElementById('employeeForm');
    let formData = new FormData(myForm);

    console.log("Full Name: " + formData.get('name'));
    console.log("Join Date: " + formData.get('date'));
    console.log("Is Manager: " + formData.get('myCheckbox'));

    let data = {
        fullName: formData.get('name'),
        dateOfJoining: formData.get('date'),
        isManager: formData.get('myCheckbox') === 'on' // Convert checkbox value to boolean
    };

    fetch(baseURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    })
        .then(res => {
            clearFields();
            getAllEmployees();
            console.log(res);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function getAllEmployees() {
    fetch(baseURL, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(res => {
            console.log(res.data);
            populateTable(res.data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function populateTable(dataArray) {
    const tableBody = document.getElementById('tblEmployee');

    tableBody.innerHTML = '';

    dataArray.forEach(item => {
        let row = document.createElement('tr');

        row.setAttribute('data-employee-id', item.employeeId);
        row.setAttribute('data-name', item.fullName);
        row.setAttribute('data-join-date', item.dateOfJoinig);
        row.setAttribute('data-is-manager', item.isManager);

        row.innerHTML = `
            <td>${item.employeeId}</td>
            <td>${item.fullName}</td>
            <td>${item.dateOfJoining}</td>
            <td>${item.isManager ? 'Yes' : 'No'}</td>
            <td>
               <button class="btn btn-danger" type="button" onclick="deleteRow(this)">Delete</button>
               <button class="btn btn-warning" type="button" onclick="editRow(this)">Edit</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
}

function editRow(button) {
    let row = button.closest('tr');

    employeeId = row.getAttribute('data-employee-id');
    let name = row.getAttribute('data-name');
    let joinDate = row.getAttribute('data-join-date');
    let isManager = row.getAttribute('data-is-manager') === 'true';

    document.getElementById('name').value = name;
    document.getElementById('date').value = joinDate;
    document.getElementById('myCheckbox').checked = isManager;
}

function updateEmployee() {
    let myForm = document.getElementById('employeeForm');
    let formData = new FormData(myForm);

    console.log("id: " + employeeId);
    console.log("Join Date: " + formData.get('date'));

    let data = {
        employeeId: employeeId,
        fullName: formData.get('name'),
        dateOfJoining: formData.get('date'),
        isManager: formData.get('myCheckbox') === 'on'
    };

    fetch(baseURL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    })
        .then(res => {
            console.log(res);
            getAllEmployees();
            clearFields();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function deleteRow(button) {
    let row = button.closest('tr');

    let employeeId = row.getAttribute('data-employee-id');

    fetch(`${baseURL}/?id=${employeeId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(() => {
            row.remove();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function clearFields() {
    document.getElementById('name').value = '';
    document.getElementById('date').value = '';
    document.getElementById('myCheckbox').checked = false;
}

getAllEmployees();
