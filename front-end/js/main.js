const txtName = $("#txt-name");
const txtAddress = $("#txt-address");
const txtContact = $("#txt-contact");
const btnSave = $("#btn-save");
const tableBodyElm = $("#table-customer tbody");
const API_BASE_URL = "http://localhost:8080/dvd/api/v1/customer";

[txtName,txtAddress,txtContact].forEach(elm=>{
    elm.addClass("animate__animated");
})

getCustomers();

btnSave.on('click',()=>{
    console.log("awa")
    if (!isValid()) return;
    saveCustomer();
})

function saveCustomer() {
    const name = txtName.val().trim();
    const address = txtAddress.val().trim();
    const contactNumber = txtContact.val().trim();

    const customer = {name,address,contactNumber};

    const jqxhr = $.ajax(API_BASE_URL,{
        method:'POST',
        contentType:'application/json',
        data: JSON.stringify(customer)
    });
    jqxhr.done(()=>{
        getCustomers();
        resetForm(true);
        txtName.trigger('focus');

    })
    jqxhr.fail(()=>{
        console.log("failed");
    })
}

function getCustomers() {
    const jqxhr = $.ajax(API_BASE_URL,{
        method: 'GET',
    })
    jqxhr.done((customerList)=>{
        if (customerList) tableBodyElm.empty();
        customerList.forEach(customer=>{
            const rowElm = $(`<tr>
                                    <td>${customer.id}</td>
                                    <td>${customer.name}</td>
                                    <td>${customer.address}</td>
                                    <td>${customer.contactNumber}</td>
                              </tr>`);
            tableBodyElm.append(rowElm);
        })
    });
}

function isValid() {
    const name = txtName.val().trim();
    const address = txtAddress.val().trim();
    const contact = txtContact.val().trim();
    let valid = true;
    resetForm();

    if (!name){
        valid= validate(txtName,"Name can't be empty");
    }else if(!/[A-Za-z]{3,}/.test(name)){
        valid = validate(txtName, "Invalid name");
    }
    if (!address){
        valid = validate(txtAddress, "Address can't be empty");
    }else if (!/[A-Za-z]{3,}/.test(address)){
        valid = validate(txtAddress, "Invalid address");
    }
    if (!contact){
        valid = validate(txtContact, "Contact Number can't be empty");
    }else if (!/\d{3}-\d{7}/.test(contact)){
        valid = validate(txtContact, "Invalid contact number");
    }
    return valid;
}

function validate(txt,msg){
    setTimeout(() => txt.addClass("is-invalid animate__shakeX"), 0);
    txt.next().text(msg);
    return false;
}

function resetForm(clearData) {
    [txtName,txtAddress,txtContact].forEach(elm=>{
        elm.removeClass("is-invalid animate__shakeX");
        if (clearData) elm.val("");
    })
}