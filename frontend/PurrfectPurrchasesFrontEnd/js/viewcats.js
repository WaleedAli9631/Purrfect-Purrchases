
    //Function adds div of cat information to page. Iterated from array 
    function addCode(catID, imgName, catName, catBreed, catGender, catAge, catCost) {
      document.getElementById("catsquares").innerHTML +=
      "<div class=\"col\">" +
      "<div class=\"p-3 bg-light rounded-lg shadow\">" +
      "<img src=\"" + imgName + "\" class=\"w-100 catElement\">" +
      "<h2>" + catName + " - " + catBreed + "</h2>" +
      "<h3>Gender: <b>" + catGender + "</b> • Age: <b>" + catAge + "</b></h3>" +
      "<h4>$" + catCost + "</h4>" + 
      "</div></div>";
    }

    function breedDropDown(theArray)  {
      document.getElementById("bDropDown").innerHTML = "<a class=\"dropdown-item\" href=\"#\">All</a><div class=\"dropdown-divider\"></div>";
      let dropDownArray = new Set();
      for (var i=0; i<=theArray.length-1; i++) {
        dropDownArray.add(theArray[i][3]);
      }
      for (const item of dropDownArray.values()) {
        document.getElementById("bDropDown").innerHTML +=
        "<a class=\"dropdown-item\" href=\"#\">" + item + "</a>";
      }
    }

    function pushCats(breedType, catStart) {
      document.getElementById("catsquares").innerHTML = "";
      elemTotal = 0;
      while ((elemTotal < catsPerPage) && (catStart <= catinfo.length-1)) {
        if ((catinfo[catStart][3] == breedType) || (breedType == "All")) {
          addCode(catinfo[catStart][0], catinfo[catStart][1], catinfo[catStart][2], catinfo[catStart][3], catinfo[catStart][4], catinfo[catStart][5], catinfo[catStart][6]);
          elemTotal++;
        }
        catStart++;
      }

      document.getElementById("directionButtons").innerHTML="";
      if (startID > 0) {
        document.getElementById("directionButtons").innerHTML += 
        "<button class=\"btn btn-secondary btn-lg px-4 mx-2\" id=\"prev-button\">< Previous</button>";
      }
      if ((startID + catsPerPage < catinfo.length-1) && (elemTotal >= catsPerPage)) {
        document.getElementById("directionButtons").innerHTML +=
        "<button class=\"btn btn-secondary btn-lg px-4 mx-2\" id=\"next-button\">Next ></button>";      
      }
    }
    
    let catsPerPage = 14;
    let startID = 0;
    let catinfo = [
      //Array is iterated through to show up on frontpage
      //CatID, CatImage, CatName, CatBreed, CatGender, CatAge, CatCost
      [1,"assets/img/cat1.jpg", "Jodie", "Siamese", "Male",7, "yellow", 700],
      [2,"assets/img/cat2.jpg", "Marty", "British Shorthair", "Female",3, "yellow", 500],
      [3,"assets/img/cat3.jpg", "Tiffany", "Persian", "Male",2, "yellow", 50],
      [4,"assets/img/cat4.jpg", "Crystal", "Ragdoll", "Male",4, "yellow",60],
      [5,"assets/img/cat5.jpg", "Robbie", "Sphynx", "Female",4, "yellow",80],
      [6,"assets/img/cat6.jpg", "Tammy", "Abyssian", "Female",7, "yellow",90],
      [7,"assets/img/cat7.jpg", "Debby", "Burmese Cat", "Female",3, "yellow",900],
      [8,"assets/img/cat8.jpg", "Mikey", "Birman", "Male",2, "yellow",400],
      [9,"assets/img/cat9.jpg", "Corey", "Tabby", "Male",12, "yellow",5000],
      [10,"assets/img/cat10.jpg", "Jeremy", "Siamese", "Male",7, "yellow",700],
      [11,"assets/img/cat11.jpg", "Marina", "British Shorthair", "Female",3, "yellow",500],
      [12,"assets/img/cat12.jpeg", "Doug", "Persian", "Male",2, "yellow",50],
      [13,"assets/img/cat13.jpg", "Chaz", "Ragdoll", "Male",4, "yellow",60],
      [14,"assets/img/cat14.jpg", "Mary", "Sphynx", "Female",4, "yellow",80],
      [15,"assets/img/cat15.jpg", "Chitzi", "Abyssian", "Female",7, "yellow",90],
      [16,"assets/img/cat16.png", "Cat", "Burmese Cat", "Female",3, "yellow",900],
      [17,"assets/img/cat17.jpg", "Meowth", "Birman", "Male",2, "yellow",400],
      [18,"assets/img/cat18.png", "Meowtwo", "Tabby", "Male",12, "yellow",300],
      [19,"assets/img/cat19.jpg", "Hobbes", "Tabby", "Male",4, "yellow",200],
      [20,"assets/img/cat20.jpg", "Dopey", "Tabby", "Male",7, "yellow",150]
    ];

    pushCats("All",startID);
    breedDropDown(catinfo);

    document.addEventListener('click', (e) => {
      let elementClass = e.target.className;
      let elementID = e.target.id;
      if (elementClass == 'dropdown-item') {
        breedType = e.target.innerHTML;
        startID = 0;
        pushCats(breedType,startID);
      }
      if (elementID == 'next-button') {
        startID += catsPerPage;
        pushCats("All",startID);
      }
      if (elementID == 'prev-button') {
        startID -= catsPerPage;
        if (startID < 0) startID = 0;
        pushCats("All",startID);
      }
    });

