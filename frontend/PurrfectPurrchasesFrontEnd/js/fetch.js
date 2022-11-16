fetch('http://127.0.0.1:9090/allcats', {
    method:'GET',
    credentials: 'include'  
  }).then((res) => {
    return res.json();
  }).then((responseBody) => {
    console.log(responseBody);
});

