import {
    getAuth,
    createUserWithEmailAndPassword,
    signOut,
    signInWithEmailAndPassword,
    onAuthStateChanged,
    getIdToken
  } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";
  

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

const baseUrl = 'http://127.0.0.1:9090'
let cartTotal = 0;
let currentUser = window.sessionStorage.getItem("user_id");
let cats = [];
let adoptionCart = document.getElementById("adoption-cart");
document.getElementById("adoption-cart").innerHTML += "<h3 id=\"cart-total\">Cart Total: $" + cartTotal + "</h3>";
function getCatsFromDatabase(){
    for(let i = 0; i < sessionCart.length; i++) {
        fetch(`${baseUrl}/cat/${sessionCart[i].cat_id}`,{
            Method: "GET",
            credentials: 'include'
            }).then((res) => {
                return res.json();
            }).then((cat) => {
                //this updates total cost of cats and displays it
                if(cat.purchasedBy == null) {
                    cartTotal += Number(cat.costs);
                    cats.push(cat);
                    document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
                    showImages(cat);
                }
                else {
                    deleteOldCatInfoFromStorage(cat.id);
                    alert(`Sorry, but it appears ${cat.name} was adopted already. They have been deleted from your cart.`);
                }
            }).catch((error) => { 
                deleteOldCatInfoFromStorage(sessionCart[i].cat_id);
                alert(`Sorry, one of your cats has been deleted and is no longer available. Navigate back to Home to select more cats`);
            });   
    }
}
getCatsFromDatabase();

/* this pulls cats from wherever and adds them to users cart */
//let cats = window.sessionStorage.getItem("cats");
function showImages(cat) {
    adoptionCart.innerHTML +=  `<div id="${cat.id}" class="cat-in-cart"> 
      <img src="${cat.imageFile}" alt = "${cat.name}"  onerror=\"this.onerror=null; this.src='assets/img/404.jpg'\"> 
       <p id="${cat.name}"><b>Name: </b>${cat.name}</p>
       <p><b>Price: </b>$${cat.costs}</p>
       <button id=\"rb${cat.id}\"class="remove-cat-from-cart">Remove Cat</button>  
       </div>`;
}

document.addEventListener("click", (e) =>{
    let removeButton = e.target.getAttribute("class");
    if(removeButton === "remove-cat-from-cart"){
        if( ! confirm("You're about to delete this cat from your cart. Is this OK?") ){
        }else {
            /**This removes the cat image from the cart */
            let idOfCatToBeRemoved = Number(e.target.parentElement.id); //this is a string. It took me hours to figure that out
            e.target.parentElement.remove();
            deleteOldCatInfoFromStorage(idOfCatToBeRemoved);
            /**This updates the sum of the price of the cats in the cart */
            cartTotal -= Number(cats.find(obj => obj.id === idOfCatToBeRemoved).costs);
            document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
            /**This removes the cat from the cat array of objects */
            cats = cats.filter(obj => obj.id !== idOfCatToBeRemoved);
        }
    }
});

function deleteOldCatInfoFromStorage(idOfCatToBeRemoved){
    let removeFromTotalCart = cart.findIndex(obj => obj.user_id == currentUser && obj.cat_id == idOfCatToBeRemoved);
    cart.splice(removeFromTotalCart,1);
    window.localStorage.setItem("cart", JSON.stringify(cart));
    sessionCart = sessionCart.filter(obj => obj.cat_id != idOfCatToBeRemoved);
    window.sessionStorage.setItem("cart", JSON.stringify(sessionCart));
}

/* This is used for making the buttons collapsible and adding the input elements within the collapsed space */
function collapsibleContent() {
    const loginOrRegister = document.getElementsByClassName("collapsible");
    var i;    
    for (i = 0; i < loginOrRegister.length; i++) {
        loginOrRegister[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.display === "block") {
            content.style.display = "none";
        } 
        else {
            content.style.display = "block";
        }
        });
    }
}
collapsibleContent();

/*********************************************************************************************************************************** */
/** THIS SECTION CONTAINS CODE FOR LOGIN/LOGOUT/SIGNUP  */
/*********************************************************************************************************************************** */

//create authentication object
const auth = getAuth();

/** This is for signing up */
const signUpForm = document.querySelector('#signup-form');
var firstSignUp = false;
signUpForm.addEventListener('submit', (e)=>{
  e.preventDefault();

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
        firstSignUp = true;
        currentUser = user["uid"];
        window.sessionStorage.setItem("user_id",currentUser);
        deleteAllCatsFromCart();
        updateSessionAndLocalCartAfterLogin();
        getCatsFromDatabase();

    }).catch(function (error) {
        // Handle error
      }).then(async () => {
        const token = await getIdToken(await getAuth().currentUser, true);
        const infoArray = [getAuth().currentUser.uid, fname, lname, address, city, state, token,null];
        fetch('http://127.0.0.1:9090/accounts/' + encodeURIComponent(JSON.stringify(infoArray)), {
          method: 'POST',
          credentials: 'include'
        })
        alert("You have signed up and are now signed in!");
        getAndDisplayShippingInfo();
        signUpForm.reset();
      }).catch((error) => {
        console.log(error.message)
      });
  }
})   

/** This is for signing in */
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
        signInForm.reset();
        alert("You have just signed in");
        deleteAllCatsFromCart();
        currentUser = user["uid"];
        window.sessionStorage.setItem("user_id",currentUser);
        updateSessionAndLocalCartAfterLogin();
        getCatsFromDatabase();
        //checkIfCatsHaveBeenPurchased();
        getAndDisplayShippingInfo();
    })
    .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // make an alert
    });
});

function updateSessionAndLocalCartAfterLogin(){
    if (sessionCart.length > 0) {
        //throw out users old cart. They wouldn't be adding new ones if they wanted the old ones
        cart = cart.filter(obj => obj.user_id != currentUser);
        for (let i = 0; i < sessionCart.length; i++) {
          cart.push({user_id:currentUser, cat_id:sessionCart[i].cat_id});
          sessionCart[i].user_id = currentUser;
        }
        //commit the changes to storage once new user has logged in
        window.localStorage.setItem("cart",JSON.stringify(cart));
        window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
      }
      else if(sessionCart.length === 0){ //user did not add any cats before logging in
        //so we should add previous cats back into their current cart
        sessionCart = cart.filter(obj => obj.user_id == currentUser);
        window.sessionStorage.setItem("cart",JSON.stringify(sessionCart));
      }
}

/** This is for signing out */
const signOutEle = document.querySelector('#logout-link')
signOutEle.addEventListener('click', ()=>{
    if( ! confirm("You are about to log out. Is this OK?") ){      
    } else {       
        signOut(auth).then(()=>{
            deleteAllCatsFromCart();
            sessionCart  = [];
            window.sessionStorage.setItem("cart", JSON.stringify(sessionCart));
            window.sessionStorage.setItem("user_id", null);
            currentUser = null;
            document.getElementById("shipping-info").remove();
        }).catch((error)=>{
            console.log(error.message)
        });   
    }
});

function deleteAllCatsFromCart() {
    while (cats.length > 0) {
        document.getElementById(cats[0].id).remove();
        cartTotal -= Number(cats[0].costs);
        document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
        cats.splice(0,1);
    }
}

onAuthStateChanged(auth, (user)=>{
    //console.log('user status changed: ', user)
    if(user == null){
        $('#loginOrSignup').show();
        $('#logout-link').hide();
    }else{
        $('#loginOrSignup').hide();
        $('#logout-link').show();
    }
  });

/*********************************************************************************************************************************** */
/** THIS SECTION CONTAINS CODE FOR COMPLETING ORDERS  */
/*********************************************************************************************************************************** */
function getAndDisplayShippingInfo(){
    onAuthStateChanged(auth, (user)=>{
        if(user){
            getIdToken(user, true).then((token)=>{
                const infoArray = [user.uid, token];

                fetch("http://127.0.0.1:9090/accounts/" + encodeURI(JSON.stringify(infoArray)),{
                    method: "GET",
                    credentials: "include"
            
                }).then((res) => {
                    return res.json();
                }).then((userInfo) => {
                    console.log(userInfo);
                    let checkoutDiv = document.getElementById("Checkout");
                    let shippingInfoDiv = document.createElement("div");
                    shippingInfoDiv.setAttribute("id", "shipping-info");
                    let h3 = document.createElement("h3");
                    h3.textContent = "Shipping Information";
                    let p1 = document.createElement("p");
                    p1.innerHTML = `Address: ${userInfo.streetAddress}`;
                    let p2 = document.createElement("p");
                    p2.innerHTML = `City: ${userInfo.city}`;
                    let p3 = document.createElement("p");
                    p3.innerHTML = `State: ${userInfo.state}`;
                    let complete = document.getElementById("Complete");
                    document.getElementById("Complete").remove();
                    shippingInfoDiv.appendChild(h3);
                    shippingInfoDiv.appendChild(document.createElement("hr"));
                    shippingInfoDiv.appendChild(p1);
                    shippingInfoDiv.appendChild(p2);
                    shippingInfoDiv.appendChild(p3);
                    checkoutDiv.appendChild(shippingInfoDiv);
                    checkoutDiv.appendChild(complete);
                });
            })
        }
    })
    
}
if (currentUser != "null" && currentUser != null) {
    getAndDisplayShippingInfo();
}

function getCurrentDate(){
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = mm + '/' + dd + '/' + yyyy;
    return today;
};

function createOrder(cat){
    fetch(`${baseUrl}/adoptionorders`, {
        method: 'POST',
        body: `{"user_id":"${currentUser}","cat_id":"${cat.id}","adoption_date":"${getCurrentDate()}"}`,
        credentials:"include"
    });
};

function getCatNames() {
    if(cats.length == 1){
        return `${cats[0].name}`;
    }
    else if(cats.length == 2) {
        return `${cats[0].name} and ${cats[1].name}`;
    }  
}

function updateCatPurchasedBy(cat){
    fetch(`${baseUrl}/cat`, {
        method: "PUT",
        body: `{"catID":"${Number(cat.id)}","catName":"${cat.name}","catBreed":"${cat.breed}","catGender":"${cat.gender}",
        "catColor":"${cat.color}","catAge":"${Number(cat.age)}","catImgName":"${cat.imageFile}","catCosts":"${Number(cat.costs)}",
        "purchasedBy":"${currentUser}"}`,
        credentials: "include"
    });
}

/* This is used for sending alert to user when they try to purchase cats on their cart */
const completeButton = document.getElementById("Complete");
completeButton.addEventListener("click", function( e ){ //e => event
        if (currentUser != "null") { //don't want user to checkout if they aren't logged in
            if (cats.length !== 0){
                if( ! confirm(`Are you sure you want to adopt ${getCatNames()}?`) ){
                } else {
                    let catNames = [];
                    catNames.push(cats[0].name);
                    let catid = cats[0].id;
                    createOrder(cats[0]);
                    updateCatPurchasedBy(cats[0]);
                    deleteOldCatInfoFromStorage(catid);
                    /**This updates the sum of the price of the cats in the cart */
                    cartTotal -= Number(cats.find(obj => obj.id == catid).costs);
                    document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
                    cats = cats.filter(obj => obj.id != catid);
                    document.getElementById(catid).remove();
                    /**The above removes the first cat. So the second cat will be the new first cat (the only cat) */
                    if(cats.length !== 0) {
                        catNames.push(cats[0].name);
                        let catid = cats[0].id;  
                        createOrder(cats[0]);
                        updateCatPurchasedBy(cats[0]);
                        deleteOldCatInfoFromStorage(catid);
                        /**This updates the sum of the price of the cats in the cart */
                        cartTotal -= Number(cats.find(obj => obj.id == catid).costs);
                        document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
                        cats = cats.filter(obj => obj.id != catid);
                        document.getElementById(catid).remove();
                    }
                    if (catNames.length === 1) {
                        alert(`Congratulations on your adoption of ${catNames[0]}`);
                    }
                    else {
                        alert(`Congratulations on your adoption of ${catNames[0]} and ${catNames[1]}`);
                    }
                }
            }
            else{
                alert("You have no cats in your cart! Add some before trying to checkout!");
            }   
        }
        else {
            alert("You must sign in or sign up first if you want to purchase a cat");
        }   
});