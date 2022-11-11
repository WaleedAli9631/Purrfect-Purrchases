import { getAuth,
   createUserWithEmailAndPassword,
   signOut,
   signInWithEmailAndPassword,
   onAuthStateChanged } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";

const auth = getAuth();

// signup
const signUpForm = document.querySelector('#signup-form')
signUpForm.addEventListener('submit', (e)=>{
  e.preventDefault();

  //get user info
  const email = signUpForm['signup-email'].value;
  const password = signUpForm['signup-password'].value;

  //sign up the user
  createUserWithEmailAndPassword(auth,email,password).then((userCredential) => {
    // Signed in 
    const user = userCredential.user;
    const modal = document.querySelector("#modal-signup");
    M.Modal.getInstance(modal).close();
    signUpForm.reset();
  })
  .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
    // make an alert
  });
})

// signOut
const signOutEle = document.querySelector('#logout')
signOutEle.addEventListener('click', ()=>{

  signOut(auth).then(()=>{
  }).catch((error)=>{
    console.log(error.message)
  })

})

// Signing in
const signInForm = document.querySelector('#login-form')
signInForm.addEventListener('submit', (e)=>{
  e.preventDefault();

  //get user info
  const email = signInForm['login-email'].value;
  const password = signInForm['login-password'].value;

  //sign in the user
  signInWithEmailAndPassword(auth,email,password).then((userCredential) => {
    // Signed in 
    const user = userCredential.user;
    const modal = document.querySelector("#modal-login");
    M.Modal.getInstance(modal).close();
    signInForm.reset();
  })
  .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
    // make an alert
  });
})

// on auth changes

onAuthStateChanged(auth, (user)=>{
  console.log('user status changed: ', user)
})