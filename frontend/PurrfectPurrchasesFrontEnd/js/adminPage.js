
    //Function adds div of cat information to page. Iterated from array 
    function getCatTable() {
      fetch('http://127.0.0.1:9090/allcats/' + JSON.stringify({"breed":"ALL","age":0,"gender":"ALL"}), {
        method:'GET',
        credentials: 'include'  
      }).then((res) => {
        return res.json();
      }).then((responseBody) => {
        pushCatCells(responseBody);
      });
    }

    function addTableCode(catID, imgName, catName, catBreed, catGender, catAge, catCost) {
      tableHTML += "<tr>" +
      "<td>" + catID + "</td>" +
      "<td>" + catName + "</td>" +
      "<td>" + catBreed + "</td>" +
      "<td>" + catGender + "</td>" +
      "<td>" + catAge + "</td>" +
      "<td>" + catCost + "</td>" +
      "<td>" + imgName + "</td>" +
      "<td><a href=\"#\" ><img src=\"assets/img/pencil-fill.svg\" class=\"tableButton\" data-id=\"" + catID + "\" data-action=\"edit\" data-toggle=\"modal\" data-target=\"#modal-editcats\"></a></td>" +
      "<td><a href=\"#\" ><img src=\"assets/img/trash3-fill.svg\" class=\"tableButton\" data-id=\"" + catID + "\" data-action=\"delete\"></a></td>" +
      "</tr>";
    }

    function pushCatCells(catinfo) {
      tableHTML = "<p class=\"text-center mx-auto\"><button class=\"btn btn-secondary btn-lg px-4 mx-2\" id=\"addCatButton\" data-toggle=\"modal\" data-target=\"#modal-addcats\">Add Cat</button></p><p class=\"p-3\"><table class=\"table table-light table-striped table-hover\"><thead class=\"thead-dark\"><tr><th>ID</th><th>Name</th><th>Breed</th><th>Gender</th><th>Age</th><th>Costs</th><th>Image File</th><th></th><th></th></tr></thead><tbody>";
      i = 0;
      while (i <= catinfo.length-1) {
          addTableCode(catinfo[i].id, catinfo[i].imageFile, catinfo[i].name, catinfo[i].breed, catinfo[i].gender, catinfo[i].age, catinfo[i].costs);
          i++;
      }
      tableHTML += "</tbody></table></p>";
      document.getElementById("content").innerHTML = tableHTML;
    }

    document.addEventListener('click', (e) => {
      tableHTML = "";
        let elementID = e.target.id;
      if (elementID == "adminPageLink") {
        getCatTable();
      }
    });

    tableHTML = "";

