    //Function adds div of cat information to page. Iterated from array 
function killCat(id) {
    fetch(`http://127.0.0.1:9090/cat/${id}`, {
        method:'DELETE',
        credentials: 'include'  
    }).then(() => {
        getCatTable();        
    })
}

const eID = document.getElementById('editcat-id');
const eName = document.getElementById('editcat-name');
const eBreed = document.getElementById('editcat-breed');
const eGender = document.getElementById('editcat-gender');
const eColor = document.getElementById('editcat-color');
const eAge = document.getElementById('editcat-age');
const eImg = document.getElementById('editcat-image');
const eCosts = document.getElementById('editcat-costs');

editCatButton.addEventListener('click', (e) => {
    e.preventDefault();  
    fetch('http://127.0.0.1:9090/cat', {
      method:'PUT',
      body: `{"catID":"${eID.value}","catName":"${eName.value}","catBreed":"${eBreed.value}","catGender":"${eGender.value}","catColor":"${eColor.value}","catAge":"${eAge.value}","catImgName":"${eImg.value}","catCosts":"${eCosts.value}"}`,
      credentials: 'include'  
    }).then((res) => {
      return res.json();
    }).then((responseBody) => {
        getCatTable();
        $('#modal-editcats').modal('hide');
        alert ("Cat has been edited!");
    });

});

function getCat(id) {
    fetch(`http://127.0.0.1:9090/cat/${id}`, {
      method:'GET',
      credentials: 'include'  
    }).then((res) => {
      return res.json();
    }).then((responseBody) => {
      console.log(responseBody);
      putCatsinModal(responseBody);
    });
}

function putCatsinModal(rs) {
    console.log(rs);
    document.getElementById('editcat-id').setAttribute('value',rs.id);
    document.getElementById('editcat-id').disabled = true; //User cannot change the ID
    document.getElementById('editcat-name').value = rs.name;//setAttribute('value',rs.name);
    document.getElementById('editcat-breed').value = rs.breed;
    document.getElementById('editcat-color').setAttribute('value',rs.color);
    document.getElementById('editcat-age').value = rs.age;
    document.getElementById('editcat-gender').value = rs.gender;
    document.getElementById('editcat-costs').setAttribute('value',rs.costs);
    document.getElementById('editcat-image').setAttribute('value',rs.imageFile);
}

document.addEventListener('click', (e) => {
    let elementClass = e.target.className;
    let elementID = e.target.id;
    if (elementClass == 'tableButton') {

        if (e.target.getAttribute('data-action') == "edit") {
            getCat(e.target.getAttribute('data-id'));
        }
        if (e.target.getAttribute('data-action') == "delete") {
            if (confirm("Do you wish to delete this cat?")) {
                killCat(e.target.getAttribute('data-id'));
            } 
        }
    }
});