import {
  getAuth,
  createUserWithEmailAndPassword,
  signOut,
  signInWithEmailAndPassword,
  onAuthStateChanged
} from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";

const auth = getAuth();

// signup
const signUpForm = document.querySelector('#signup-form')
signUpForm.addEventListener('submit', (e) => {
  // e.preventDefault();

  //get user info
  const email = signUpForm['signup-email'].value;
  const password = signUpForm['signup-password'].value;
  const passwordConfirm = signUpForm['signup-password-confirm'].value;
  const fname = signUpForm['signup-fname'].value;
  const lname = signUpForm['signup-lname'].value;
  const address = signUpForm['signup-address'].value;
  const city = signUpForm['signup-city'].value;
  const state = signUpForm['signup-state'].value;

  if (password.length < 6) {
    alert("Password length is below 6");
  }
  else if (password != passwordConfirm) {
    alert("Password mismatch");
  } else {
    //sign up the user
    createUserWithEmailAndPassword(auth, email, password).then((userCredential) => {
      // Signed in 

      const user = userCredential.user;
      const infoArray = [user.uid, fname, lname, address, city, state];

      fetch('http://127.0.0.1:9090/accounts/' + encodeURIComponent(JSON.stringify(infoArray)), {
        method: 'POST',
        credentials: 'include'
      })
      $('#modal-signup').modal('hide');
      signUpForm.reset();
    })
      .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // make an alert
      });
  }

})

// signOut
const signOutEle = document.querySelector('#logout')
signOutEle.addEventListener('click', () => {

  signOut(auth).then(() => {
  }).catch((error) => {
    console.log(error.message)
  })

})

// Signing in
const signInForm = document.querySelector('#login-form')
signInForm.addEventListener('submit', (e) => {
  e.preventDefault();

  //get user info
  const email = signInForm['login-email'].value;
  const password = signInForm['login-password'].value;

  //sign in the user
  signInWithEmailAndPassword(auth, email, password).then((userCredential) => {
    // Signed in 
    const user = userCredential.user;
    const modal = document.querySelector("#modal-login");
    $('#modal-login').modal('hide');
    signInForm.reset();
  })
    .catch((error) => {
      const errorCode = error.code;
      const errorMessage = error.message;
      // make an alert
    });
})

// on auth changes

onAuthStateChanged(auth, (user) => {
  if (user == null) {
    $('#signUpLi').show();
    $('#loginLi').show();
    $('#logoutLi').hide();
    $('#accountLi').hide();

  } else {
    $('#signUpLi').hide();
    $('#loginLi').hide();
    $('#logoutLi').show();
    $('#accountLi').show();

  }
})