const cName = document.getElementById('addcat-name');
const cBreed = document.getElementById('addcat-breed');
const cGender = document.getElementById('addcat-gender');
const cColor = document.getElementById('addcat-color');
const cAge = document.getElementById('addcat-age');
const cImg = document.getElementById('addcat-image');
const cCosts = document.getElementById('addcat-costs');

const addCatButton2 = document.getElementById('addCatButton2');

addCatButton2.addEventListener('click', (e) => {
  e.preventDefault();  
  fetch('http://127.0.0.1:9090/cat', {
      method:'POST',
      body: `{"catID":"-1","catName":"${cName.value}","catBreed":"${cBreed.value}","catGender":"${cGender.value}","catColor":"${cColor.value}","catAge":"${cAge.value}","catImgName":"${cImg.value}","catCosts":"${cCosts.value}"}`,
      credentials: 'include'  
    }).then((res) => {
      return res.json();
    }).then((responseBody) => {
      getCatTable();
      $('#modal-addcats').modal('hide');
    });
});