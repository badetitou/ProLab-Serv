function logIn() {
	var email = $('#email').val();
	var password =  $('#pwd').val();
	alert(password);

$.getJSON("v1/users/" + email + "&" + password, function(data) {
    if (data.log != "") {
      window.location.href = "app/hub.html"
    } else{
      consol.log('erreur');
    }
	alert('identifiant ou mdp invalid')
  });
}