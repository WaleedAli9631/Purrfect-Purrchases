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
Array.from(removeButtons).forEach( (e, index) => e.addEventListener ("click", function(){
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
}));


/* This is used for sending alert to user when they try to purchase cats on their cart */
const completeButton = document.getElementById("Complete");
completeButton.addEventListener("click", function( e ){ //e => event
        if( ! confirm("Are you sure you want to adopt this cat?") ){
            e.preventDefault(); // ! => don't want to do this
        } else {
            //want to do this! => maybe do something about it?
            alert('Congratulations on your adoption! We know our friend will enjoy it\'s new forever home with you!');
        }
});

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

/*const queryString = window.location.href;
console.log(queryString);*/