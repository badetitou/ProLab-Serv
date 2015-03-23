function logIn() {
	var pseudo = $('#pseudo').val();
	var password =  $('#pwd').val();

$.getJSON("v1/users/" + pseudo + "&" + password, function(data) {
    if (data.log != "") {
    setCookie('log', pseudo, 265);
      window.location.href = "hub.html"
    } else{
      alert('identifiant ou mdp invalid')
      consol.log('erreur');
    }
  });
}

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires +";path=/";
}