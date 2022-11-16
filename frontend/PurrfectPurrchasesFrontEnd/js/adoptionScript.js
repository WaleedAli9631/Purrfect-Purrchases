import { getAuth,
    createUserWithEmailAndPassword,
    signOut,
    signInWithEmailAndPassword,
    onAuthStateChanged } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";

/* this pulls cats from wherever and adds them to users cart */
let cats = [
    {way:'assets/img/cat1.jpg',description: 'Jodie', price:'500', id:"1"},
    {way:'assets/img/cat2.jpg',description: 'Marty', price:'250', id:"2"}
  ];
  var adoptionCart = document.getElementById("adoption-cart");
  function showImages() {
    for(let i = 0; i < cats.length; i++){
      adoptionCart.innerHTML +=  `<div id="${cats[i].id}" class="cat-in-cart"> 
      <img src="${cats[i].way}" alt = "${cats[i].description}" 
       width="200" height="200" style="border: 2px solid black"> 
       <p><b>Name: </b>${cats[i].description}</p>
       <p><b>Price: </b>$${cats[i].price}</p>
       <button id=\"rb${cats[i].id}\"class="remove-cat-from-cart">Remove Cat</button>  
       </div>`;
    }
  }
  showImages();

/** This is for adding the price of all the cats in the cart and dislpaying it */
let cartTotal = 0;
function updateCartTotal(){
    for (let i = 0; i < cats.length; i++) {
        cartTotal += Number(cats[i].price);
    }
    document.getElementById("adoption-cart").innerHTML += "<h3 id=\"cart-total\">Cart Total: $" + cartTotal + "</h3>";
}
updateCartTotal();

/** This removes a cat from the cart and deletes it from the array of cat objects*/
let removeButtons = document.getElementsByClassName("remove-cat-from-cart");
console.log(removeButtons);
Array.from(removeButtons).forEach( (e) => e.addEventListener ("click", function(){
    if( ! confirm("You're about to delete this cat from your cart. Is this OK?") ){
        e.preventDefault();
    }else {
        /**This removes the cat image from the cart */
        let idOfCatToBeRemoved = e.parentElement.id;
        console.log(e.parentElement);
        e.parentElement.remove();
        /**This updates the sum of the price of the cats in the cart */
        cartTotal -= Number(cats.find(obj => obj.id === idOfCatToBeRemoved).price);
        document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
        /**This removes the cat from the cat array of objects */
        cats = cats.filter(obj => obj.id !== idOfCatToBeRemoved);
        console.log(removeButtons);
    }
}));

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

//sessionStorage.
/*********************************************************************************************************************************** */
/** THIS SECTION CONTAINS CODE FOR LOGIN/LOGOUT/SIGNUP  */
/*********************************************************************************************************************************** */


//create authentication object
const auth = getAuth();

/** This is for signing up */
const signUpForm = document.querySelector('#signup-form')
signUpForm.addEventListener('submit', (e)=>{
    // e.preventDefault();

    //get user info
    const firstName = signUpForm['signup-first-name'].value;
    const lastName = signUpForm['signup-last-name'].value;
    const email = signUpForm['signup-email'].value;
    const password = signUpForm['signup-password'].value;
    const confirmPassword = signUpForm['signup-confirm-password'].value;
    const streetAddress = signUpForm['signup-street-address'].value;
    const city = signUpForm['signup-city'].value;
    const state = signUpForm['signup-state'].value;

    //sign up the user
    createUserWithEmailAndPassword(auth,firstName,lastName,email,password,confirmPassword,streetAddress,city,state).then((userCredential) => {
        // Signed in 
        const user = userCredential.user;
        signUpForm.reset();
        alert("You have signed up and are now logged in");
    })
    .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // make an alert
    });
});

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
    })
    .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // make an alert
    });
});

/** This is for signing out */
const signOutEle = document.querySelector('#logout')
signOutEle.addEventListener('click', ()=>{
    if( ! confirm("You are about to log out. Is this OK?") ){
        e.preventDefault(); // ! => don't want to do this
    } else {
        //want to do this! => maybe do something about it?
        signOut(auth).then(()=>{
        }).catch((error)=>{
            console.log(error.message)
        });   
    }
});
let currentUser;
onAuthStateChanged(auth, (user)=>{
    console.log('user status changed: ', user)
    currentUser = user;
    if(user == null){
        $('#loginOrSignup').show();
        $('#logout').hide();
  
    }else{
        $('#loginOrSignup').hide();
        $('#logout').show();
    }
  });

/*********************************************************************************************************************************** */
/** THIS SECTION CONTAINS CODE FOR COMPLETING ORDERS  */
/*********************************************************************************************************************************** */
function getCurrentDate(){
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = mm + '/' + dd + '/' + yyyy;
    return today;
};

const baseUrl = 'http://127.0.0.1:9090';
function createOrder(cat){
    fetch(`${baseUrl}/adoptionorders`, {
        method: 'POST',
        body: `{"user_id":"${currentUser["uid"]}","cat_id":"${cat.id}","adoption_date":"${getCurrentDate()}"}`,
        credentials:"include"
    });
};

/* This is used for sending alert to user when they try to purchase cats on their cart */
const completeButton = document.getElementById("Complete");
completeButton.addEventListener("click", function( e ){ //e => event
        if (currentUser !== null) {
            if (cats.length !== 0){
                if( ! confirm("Are you sure you want to adopt this cat?") ){
                    e.preventDefault(); // gives choice to not adopt cat
                } else {
                    let catNames = [];
                    catNames.push(cats[0].description);
                    let catid = cats[0].id;
                    createOrder(cats[0]);
                    /**This updates the sum of the price of the cats in the cart */
                    cartTotal -= Number(cats.find(obj => obj.id === catid).price);
                    document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
                    cats = cats.filter(obj => obj.id !== catid);
                    document.getElementById(catid).remove();
                    /**The above removes the first cat. So the second cat will be the new first cat (the only cat) */
                    if(cats.length !== 0) {
                        catNames.push(cats[0].description);
                        let catid = cats[0].id;  
                        createOrder(cats[0]);
                        /**This updates the sum of the price of the cats in the cart */
                        cartTotal -= Number(cats.find(obj => obj.id === catid).price);
                        document.getElementById("cart-total").innerText = "Cart Total: $" + cartTotal;
                        cats = cats.filter(obj => obj.id !== catid);
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