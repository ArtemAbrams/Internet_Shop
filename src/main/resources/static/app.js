const uri = '/product';
let products = [];

function getProducts() {
       fetch('product/getAll')
        .then(response => response.json())
        .then(data => _displayProducts(data))
        .catch(error => console.error('Unable to get products.', error));
}
function _displayProducts(data) {
    const tableBody = document.getElementById('products');
    tableBody.innerHTML = '';
    const button = document.createElement('button');
    data.forEach(product => {
        const row = document.createElement('tr');

        const nameCell = document.createElement('td');
        nameCell.textContent = product.name;
        row.appendChild(nameCell);

        const priceCell = document.createElement('td');
        priceCell.textContent = product.price;
        row.appendChild(priceCell);

        const descriptionCell = document.createElement('td');
        descriptionCell.textContent = product.description;
        row.appendChild(descriptionCell);

        const weightCell = document.createElement('td');
        weightCell.textContent = product.weight;
        row.appendChild(weightCell);

        const countryCell = document.createElement('td');
        countryCell.textContent = product.countryName;
        row.appendChild(countryCell);

        const actionsCell = document.createElement('td');

        let editButton = button.cloneNode(false);
        editButton.innerText = 'Edit';
        editButton.addEventListener('click', () => displayEditForm(product.id));
        actionsCell.appendChild(editButton);

        let deleteButton = button.cloneNode(false);
        deleteButton.innerText = 'Delete';
        deleteButton.addEventListener('click', () => deleteCategory(product.id));
        actionsCell.appendChild(deleteButton);

        row.appendChild(actionsCell);

        tableBody.appendChild(row);
    });
    console.log(data);
    products = data;
}


function createProduct() {
    const name = document.getElementById('add-name').value;
    const price = parseFloat(document.getElementById('add-price').value);
    const description = document.getElementById('add-description').value;
    const weight = parseFloat(document.getElementById('add-weight').value);
    const countryId = document.getElementById('add-country').value;

    const product = {
        name: name.trim(),
        price: price,
        description: description.trim(),
        weight: weight,
        country: countryId.trim()
    };
    fetch('product/createProduct', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => response.json())
        .then(() => {

        })
        .catch(error => console.error('Unable to create product.', error));
}
function deleteCategory(id) {
    fetch(`product/deleteProduct?id=${id}`, {
        method: 'DELETE'
    })
        .then(() => getProducts())
        .catch(error => console.error('Unable to delete category.', error));
}

function displayEditForm(id) {
    const product = products.find(product => product.id === id);
    document.getElementById('edit-id').value = product.id;
    document.getElementById('edit-name').value = product.name;
    document.getElementById('edit-description').value = product.description;
    document.getElementById('edit-price').value = product.price;
    document.getElementById('edit-weight').value = product.weight;
    const countrySelect = document.getElementById('edit-country');
    for (let i = 0; i < countrySelect.options.length; i++) {
        if (countrySelect.options[i].value === product.country) {
            countrySelect.options[i].selected = true;
            break;
        }
    }
    document.getElementById('editProduct').style.display = 'block';
}

function updateProduct() {
    alert("rerwerwerwerwe")
    const productId = document.getElementById('edit-id').value;
    const name = document.getElementById('edit-name').value;
    const price = parseFloat(document.getElementById('edit-price').value);
    const description = document.getElementById('edit-description').value;
    const weight = parseFloat(document.getElementById('edit-weight').value);
    const countryId = document.getElementById('edit-country').value;

    const product = {
        name: name.trim(),
        price: price,
        description: description.trim(),
        weight: weight,
        country: countryId.trim()
    };

    fetch(`product/updateProduct?productId=${productId}`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(() => getProducts())
        .catch(error => console.error('Unable to update category.', error));
    closeInput();
    return false;
}
function closeInput() {
    document.getElementById('editProduct').style.display = 'none';
}

