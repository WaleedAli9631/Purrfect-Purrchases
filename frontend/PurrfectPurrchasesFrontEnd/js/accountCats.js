import {
    getAuth,
    createUserWithEmailAndPassword,
    signOut,
    signInWithEmailAndPassword,
    onAuthStateChanged,
    getIdToken
} from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";
//Function adds div of cat information to page. Iterated from array 
const auth = await getAuth();
onAuthStateChanged(auth, (user) => {

  if (user) {
    // User is signed in, see docs for a list of available properties
    // https://firebase.google.com/docs/reference/js/firebase.User
    const uid = user.uid;

    getIdToken(user, true).then((token) => {
      const infoArray = [uid, token];
      fetch('http://127.0.0.1:9090/usercats/' + JSON.stringify(infoArray), {
        method: 'GET',
        credentials: 'include'
    }).then((res) => {
        return res.json();
    }).then((responseBody) => {
        pushCatCells(responseBody);
    });
    });
  } else {
    // User is signed out
    // ...
  }
});

function pushCatCells(catinfo) {
    console.log("test");

    let catPlacement = document.getElementById("ownedCats");
    catinfo.forEach((cat)=>{
        console.log(cat);
        let catEle;
        catEle += "<tr>" +
        "<td>" + cat.name + "</td>" +
        "<td>" + cat.breed + "</td>" +
        "<td>" + cat.gender + "</td>" +
        "<td>" + cat.age + "</td>" +
        "</tr>";
        $('#ownedCats > tbody:last-child').append(catEle);
    })
}

