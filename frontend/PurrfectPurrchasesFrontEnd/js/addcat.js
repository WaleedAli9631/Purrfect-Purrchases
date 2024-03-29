const cName = document.getElementById('addcat-name');
const cBreed = document.getElementById('addcat-breed');
const cGender = document.getElementById('addcat-gender');
const cColor = document.getElementById('addcat-color');
const cAge = document.getElementById('addcat-age');
const cImg = document.getElementById('addcat-image');
const cCosts = document.getElementById('addcat-costs');
const addCatButton2 = document.getElementById('addCatButton2');

addCatButton2.addEventListener('click', (e) => {
  cUser = window.sessionStorage.getItem("user_id");
  if (cName.checkValidity() && cAge.checkValidity() && cCosts.checkValidity() && cImg.checkValidity() && cColor.checkValidity()) {
    e.preventDefault();
    fetch('http://127.0.0.1:9090/cat', {
        method:'POST',
        body: `{"userID":"${cUser}","catID":"-1","catName":"${cName.value}","catBreed":"${cBreed.value}","catGender":"${cGender.value}","catColor":"${cColor.value}","catAge":"${cAge.value}","catImgName":"${cImg.value}","catCosts":"${cCosts.value}"}`,
        credentials: 'include'  
      }).then((res) => {
        return res.json();
      }).then((responseBody) => {
        getCatTable();
        $('#modal-addcats').modal('hide');
        alert ("Cat has been added!");
      });
  }
});