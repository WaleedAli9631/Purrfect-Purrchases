# Purrfect Purrchases

Purrfect Purrchases is a site for cat lovers who are trying to find their feline friends. Users can register to the page, log in, and start filtering for their purrfect friend. Users can go on the site, and view from a list of available cats looking for a home. Once they find that purrfect friend, they can select their furry companion, complete their adoption form, check out, and make their home complete. Regular users can come back to the site at any time, and see information about the cats they’ve adopted, including medical records, physical descriptions, and photos.

## Tech Stack
### FrontEnd
- Deployed on GCP Busket
- HTML
- CSS
- JavaScript

### Backend
- Java
- PostgreSQL
- GCP SQL Application
- Javalin
- JDBC
- The Cat API: https://thecatapi.com/

### Testing
- Selenium
- Cucumber
- TestNG

##Authentication
---
`GET /cat`

Allows user to get cat information. When the user submits a cat id successfully, the server will send a JSON object with cat information.

### Request
Headers
| Key | Value | Description |
| :-- | :---- | :---------- |
| id | integer | The database id of the respective cat |

### Response
Response Body
```json
{
  "id":id,
  "name": name,
  "breed": breed,
  "gender": gender,
  "color": color,
  "age": age,
  "imageFile": imageFile,
  "costs": cost,
  "purchasedBy", purchasedBy
}
```
Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 Okay | Cat information was found |
| 400 | Poor cat search data |

---
`GET /allcats`

Allows user to get multiple cats. When the user submits breed, gender, and age info successfully, the server will send a JSON object with multiple cat information.

### Request
Headers
| Key | Value | Description |
| :-- | :---- | :---------- |
| age | integer | The database id of the respective cat |

### Response
Response Body
```json
{
    "id":id,
    "name": name,
    "breed": breed,
    "gender": gender,
    "color": color,
    "age": age,
    "imageFile": imageFile,
    "costs": cost,
    "purchasedBy", purchasedBy
}
```
Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 Okay | Cat information was found |
| 400 | Poor cat search data |

---
`POST /cat`

Allows admin to add cat. When the user submits name, breed, gender, color, imageName, costs, and age info successfully, a new cat will be added to the database.

### Request
Headers
| Key | Value | Description |
| :-- | :---- | :---------- |
| userid | string | User ID of admin posting cat data |
| catName | string | Name of cat |
| catBreed | string | Breed of cat |
| catGender | string | Gender of cat |
| catColor | string | Color of cat |
| catAge | integer | Age of cat |
| catImgName | string | Image file of cat |
| catCosts | integer | Cost of cat |

### Response
Response Body
```json
{
    "id":id,
    "name": name,
    "breed": breed,
    "gender": gender,
    "color": color,
    "age": age,
    "imageFile": imageFile,
    "costs": cost,
    "purchasedBy", purchasedBy
}
```
Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 Okay | Cat was added |
| 400 | Cat was not added |