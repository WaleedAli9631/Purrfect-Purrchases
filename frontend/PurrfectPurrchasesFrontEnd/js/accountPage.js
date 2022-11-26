import {
  getAuth,
  createUserWithEmailAndPassword,
  signOut,
  signInWithEmailAndPassword,
  onAuthStateChanged,
  getIdToken
} from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";




const auth = getAuth();
onAuthStateChanged(auth, (user) => {
  const fname = document.getElementById('fname');
  const lname = document.getElementById('lname');
  const address = document.getElementById('street-address');
  const city = document.getElementById('city');
  const state = document.getElementById('state');
  if (user) {
    // User is signed in, see docs for a list of available properties
    // https://firebase.google.com/docs/reference/js/firebase.User
    const uid = user.uid;
    getIdToken(user, true).then((token) => {
      const infoArray = [uid, token];
      fetch("http://127.0.0.1:9090/accounts/" + encodeURI(JSON.stringify(infoArray)), {
        method: 'GET',
        credentials: 'include'
      }).then((res) => {
        return res.json();
      }).then((responseBody) => {
        console.log(responseBody);
        fname.value = responseBody.fname;
        lname.value = responseBody.lname;
        address.value = responseBody.streetAddress;
        city.value = responseBody.city;
        state.value = responseBody.state;
      });
    });
  } else {
    // User is signed out
    // ...
  }
});


// First we want to disable edit button, we are already in edit mode
$('#btnReadOnly').attr("disabled", true);

// Bind function to Read Only button
$('#btnReadOnly').click(async function () {
  // Get all input fields used for Domino
  var inputs = $('[data-dominofield]');
  // Process each field
  inputs.each(function () {
    // Build new DIV element
    var input = $(this);
    var input1 = '<input type="text" class="form-control" ';
    input1 += 'data-dominofield="' + input.data('dominofield') + '" ';
    input1 += 'value="' + input.val() + '" ';
    input1 += 'id="' + input.attr('id') + '" ';
    input1 += 'disabled="' + "true" + '">';
    // Insert the new div element in front of input field
    input.before(input1);
    // Remove input field
    input.remove();
  });
  $(".btn").attr('disabled', false);
  $(this).attr('disabled', true);
  const fname = document.getElementById('fname');
  const lname = document.getElementById('lname');
  const address = document.getElementById('street-address');
  const city = document.getElementById('city');
  const state = document.getElementById('state');
  const user = await getAuth().currentUser;
  getIdToken(user, true).then((token) => {
    const infoArray = [user.uid, fname.value, lname.value, address.value, city.value, state.value, token];
    console.log(infoArray);
    fetch("http://127.0.0.1:9090/accounts/" + encodeURI(JSON.stringify(infoArray)), {
      method: 'PUT',
      credentials: 'include'
    }).then((res) => {
      return res.json();
    }).then((responseBody) => {
      console.log(responseBody);
      fname.value = responseBody.fname;
      lname.value = responseBody.lname;
      address.value = responseBody.streetAddress;
      city.value = responseBody.city;
      state.value = responseBody.state;
    });
  });
});

// Bind function to Read Only button
$('#btnEdit').click(function () {
  // Get all input fields used for Domino
  var divs = $('[data-dominofield]');
  // Process each field
  divs.each(function () {
    // Build new INPUT element
    var div = $(this);
    var input = '<input type="text" class="form-control" ';
    input += 'data-dominofield="' + div.data('dominofield') + '" ';
    input += 'value="' + div.val() + '" ';
    input += 'id="' + div.attr('id') + '">';
    // Insert ther new iinp element in front of existing div field
    div.before(input);
    // Remove input field
    div.remove();
  });
  $(".btn").attr('disabled', false);
  $(this).attr('disabled', true);

 
});

