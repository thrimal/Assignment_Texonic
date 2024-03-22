const baseURL = 'http://localhost:8080/api/v1/designation';
let designationId;

clearFields();

function submitForm() {
    let myForm = document.getElementById('designationForm');
    let formData = new FormData(myForm);

    console.log("Name: " + formData.get('name'));
    console.log("Remark: " + formData.get('remark'));

    let data = {
        name: formData.get('name'),
        remark: formData.get('remark')
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
            console.log(res);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function getAllDesignations() {
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
            // Assuming data is an array of objects
            console.log(res.data);
            populateTable(res.data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function populateTable(dataArray) {
    const tableBody = document.getElementById('tblCustomer');

    tableBody.innerHTML = '';

    dataArray.forEach(item => {
        let row = document.createElement('tr');

        // Store the data in row attributes
        row.setAttribute('data-designation-id', item.designationId);
        row.setAttribute('data-name', item.name);
        row.setAttribute('data-remark', item.remark);

        row.innerHTML = `
            <td>${item.designationId}</td>
            <td>${item.name}</td>
            <td>${item.remark}</td>
            <td>
               <button class="btn btn-danger" type="button" onclick="deleteRow(this)">Delete</button>
               <button class="btn btn-warning" type="button" onclick="editRow(this)">Edit</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
}

function editRow(button) {
    // Get the parent row of the button
    let row = button.closest('tr');

    designationId = row.getAttribute('data-designation-id');
    let name = row.getAttribute('data-name');
    let remark = row.getAttribute('data-remark');

    console.log(designationId)

    // // Populate the form fields with the retrieved data
    // document.getElementById('designationId').value = designationId;
    document.getElementById('name').value = name;
    document.getElementById('remark').value = remark;
}

function updateDesignation(){
    let myForm = document.getElementById('designationForm');
    let formData = new FormData(myForm);

    console.log("id: " + designationId);
    console.log("Remark: " + formData.get('remark'));

    let data = {
        designationId: designationId,
        name: formData.get('name'),
        remark: formData.get('remark')
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
            getAllDesignations();
            clearFields();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function deleteRow(button) {
    let row = button.closest('tr');

    let designationId = row.getAttribute('data-designation-id');

    fetch(`${baseURL}/?id=${designationId}`, {
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
    // document.getElementById('designationId').value = '';
    document.getElementById('name').value = '';
    document.getElementById('remark').value = '';
}

getAllDesignations();
