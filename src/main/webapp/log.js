function logIn() {
	var email = $('#email').val();
	var password =  $('#pwd').val();

$.getJSON("v1/users/" + email + "&" + password, function(data) {
    if (data.log != "") {
      window.location.href = "hub.html"
    } else{
      alert('identifiant ou mdp invalid')
      consol.log('erreur');
    }
  });
}