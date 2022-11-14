
    //Function adds div of cat information to page. Iterated from array 
    function getCatList(breeds, start) {
      fetch('http://127.0.0.1:9090/allcats', {
        method:'GET',
        credentials: 'include'  
      }).then((res) => {
        return res.json();
      }).then((responseBody) => {
        console.log(responseBody);
        pushCats(responseBody, breeds,start);
      });
    }

    function addCode(catID, imgName, catName, catBreed, catGender, catAge, catCost) {
      document.getElementById("catsquares").innerHTML +=
      "<div class=\"col\">" +
      "<div class=\"p-3 bg-light rounded-lg shadow\">" +
      "<a href=\"checkout.html?id=" + catID + "\"><img src=\"" + imgName + "\" class=\"w-100 catElement\"></a>" +
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

    function pushCats(catinfo, breedType, catStart) {
      document.getElementById("catsquares").innerHTML = "";
      elemTotal = 0;
      while ((elemTotal < catsPerPage) && (catStart <= catinfo.length-1)) {
        if ((catinfo[catStart].breed == breedType) || (breedType == "All")) {
          addCode(catinfo[catStart].id, catinfo[catStart].imageFile, catinfo[catStart].name, catinfo[catStart].breed, catinfo[catStart].gender, catinfo[catStart].age, catinfo[catStart].costs);
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
    getCatList("All",startID);

    //breedDropDown(catinfo);

    document.addEventListener('click', (e) => {
      let elementClass = e.target.className;
      let elementID = e.target.id;
      if (elementClass == 'dropdown-item') {
        breedType = e.target.innerHTML;
        startID = 0;
        //pushCats(breedType,startID);
        getCatList(breedType, startID);
      }
      if (elementID == 'next-button') {
        startID += catsPerPage;
        //pushCats("All",startID);
        getCatList("All", startID);
      }
      if (elementID == 'prev-button') {
        startID -= catsPerPage;
        if (startID < 0) startID = 0;
        getCatList("All", startID);
      }
    });

