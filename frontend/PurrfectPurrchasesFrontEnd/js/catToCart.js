import { getAuth,
  createUserWithEmailAndPassword,
  signOut,
  signInWithEmailAndPassword,
  onAuthStateChanged } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";

const auth = getAuth();

let cart = [];
let sessionCart = [];

function makeSureCartArraysUpToDate(){
  if (window.localStorage.getItem("cart") !== null) {
    cart = JSON.parse(window.localStorage.getItem("cart"));
    }
  if (window.sessionStorage.getItem("cart") !== null) {
  sessionCart = JSON.parse(window.sessionStorage.getItem("cart"));
  }
}
makeSureCartArraysUpToDate();

let currentUserId = window.sessionStorage.getItem("user_id");
//this runs every time someone logs in or logs out
onAuthStateChanged(auth, (user)=>{
if (user !== null) {
  window.sessionStorage.setItem("user_id", user["uid"]);
  currentUserId = window.sessionStorage.getItem("user_id");
  //this will get triggered if the user adds items to there cart before logging in
  if (sessionCart.length > 0) {
    nullToLoggedIn(user["uid"]);
    //commit the changes to storage once new user has logged in
    window.localStorage.setItem("cart",JSON.stringify(cart));
    window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
  }
  else if(sessionCart.length === 0){ //user did not add any cats before logging in
    //so we should add previous cats back into their current cart
    sessionCart = cart.filter(obj => obj.user_id === user["uid"]);
    window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
  }
}
//this code gets run when a user logs out
else{
  window.sessionStorage.setItem("user_id", null);
  currentUserId = window.sessionStorage.getItem("user_id");
  sessionCart = [];
  window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
}
});

function nullToLoggedIn(id){
  //throw out users old cart. They wouldn't be adding new ones if they wanted the old ones
    cart = cart.filter(obj => obj.user_id !== id);
    for (let i = 0; i < sessionCart.length; i++) {
      cart.push({user_id:id, cat_id:sessionCart[i].cat_id});
      sessionCart[i].user_id = id;
    }
}

function addCatToCart(u_id, c_id, catName){
if(sessionCart.length < 2) {
  //user has no cats in cart or only has one and isn't trying to add the same one again
  if(sessionCart.length == 0 || (sessionCart.length == 1 && sessionCart[0]["cat_id"] !== c_id)) {
    //if user clicks cancel, the cat will not be added to the cart
    if(confirm("You're about to add this cat to your cart. Is this OK?") ){
      //only add to entire shopping cart if user is logged in
      if(currentUserId !== "null") { //is not actually null but is the string "null"
        cart.push({user_id:u_id,cat_id:c_id});
        window.localStorage.setItem("cart",JSON.stringify(cart));
      }
      sessionCart.push({user_id:u_id,cat_id:c_id});
      window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
      alert("You have added "+ catName + " to your cart");
    }
  }
  else if(sessionCart.length == 1 && sessionCart[0]["cat_id"] === c_id) {
    alert("You already have " + catName + " in your cart!");
  } 
}
else{
  alert("You already have 2 cats in your cart. Please navigate to the cart page and remove one before adding another");
}
};

document.addEventListener("click", (e) =>{
  let addCatButton = e.target.getAttribute("class"); 
  if(addCatButton === "cart-button") {
    let topParent = e.target.parentElement.parentElement;
    let catName = topParent.getElementsByClassName("cat-name")[0].textContent.split(" -")[0];
    let currentCatId = topParent.getAttribute("id");
    addCatToCart(currentUserId, currentCatId, catName);
  }
});