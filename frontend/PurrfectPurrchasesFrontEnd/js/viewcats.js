
    //Function adds div of cat information to page. Iterated from array 
    //Breeds and Gender = "ALL" if we want all
    //Age = "0" if we want all
    function getCatList(breeds, age, gender, start) {
      fetch('http://127.0.0.1:9090/allcats/'+ JSON.stringify({"breed":breeds,"age":age,"gender":gender}), {
        method:'GET',
        credentials: 'include'  
      }).then((res) => res.json())
      .then((responseBody) => {
        pushCats(responseBody, start);
        if (responseBody.length == 0) alert("No cats found with these parameters!");
      })
      .catch((error) => {
        alert("A servor error was committed");
      })
    }

    function addCode(catID, imgName, catName, catBreed, catGender, catAge, catCost) {
      document.getElementById("catsquares").innerHTML +=
      "<div class=\"col\">" +
      "<div class=\"p-3 bg-light rounded-lg shadow\">" +
      "<a href=\"checkout.html?id=" + catID + "\"><img src=\"" + imgName + "\" class=\"w-100 catElement\"></a>" +
      "<h2>" + catName + " - <span class=\"BreedDeclaration\">" + catBreed + "</span></h2>" +
      "<h3>Gender: <b class=\"GenderDeclaration>\">" + catGender + "</b> • Age: <b class=\"AgeDeclaration\">" + catAge + "</b></h3>" +
      "<h4>$" + catCost + "</h4>" + 
      "</div></div>";
    }

    function pushCats(catinfo, catStart) {
      document.getElementById("catsquares").innerHTML = "";
      originalCat = catStart;             //save starting cat on page
      elemTotal = 0;
      while ((elemTotal < catsPerPage) && (catStart <= catinfo.length-1)) {
          addCode(catinfo[catStart].id, catinfo[catStart].imageFile, catinfo[catStart].name, catinfo[catStart].breed, catinfo[catStart].gender, catinfo[catStart].age, catinfo[catStart].costs);
          catStart++;
          elemTotal++;
      }

      document.getElementById("directionButtons").innerHTML="";
      if (originalCat > 0) {
        document.getElementById("directionButtons").innerHTML += 
        "<button class=\"btn btn-secondary btn-lg px-4 mx-2\" id=\"prev-button\">< Previous</button>";
      }
      if ((originalCat + catsPerPage < catinfo.length-1) && (elemTotal >= catsPerPage)) {
        document.getElementById("directionButtons").innerHTML +=
        "<button class=\"btn btn-secondary btn-lg px-4 mx-2\" id=\"next-button\">Next ></button>";      
      }
    }
    
    document.addEventListener('click', (e) => {
      let elementClass = e.target.className;
      let elementID = e.target.id;
      if (elementID == 'next-button') {
        startID += catsPerPage;
        getCatList(breedType, ageType, genderType, startID);
      }
      if (elementID == 'prev-button') {
        startID -= catsPerPage;
        if (startID < 0) startID = 0;
        getCatList(breedType, ageType, genderType, startID);
      }
    });

    filterCatButton.addEventListener('click', () => {
      genderType = document.getElementById("genderSelect").value;
      ageType = document.getElementById("ageSelect").value;
      breedType = document.getElementById("breedSelect").value;
      if (genderType == "Gender") genderType = "ALL";
      if ((ageType == "Age") || (ageType == "ALL")) ageType = 0;
      if (breedType == "Breed") breedType = "ALL";
      getCatList(breedType, ageType, genderType, startID);
    });

    let genderType = "ALL";
    let ageType = 0;
    let breedType = "ALL";
    let catsPerPage = 14;
    let startID = 0;
    getCatList(breedType,ageType,genderType,startID);

