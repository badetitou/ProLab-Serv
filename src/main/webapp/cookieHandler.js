function isConnected() {
	var username = getCookie('log');
	if (username == "") {
		window.location.href = "index.html";
	}
}

function isntConnected() {
	var username = getCookie('log');
	if (username != "") {
		window.location.href = "hub.html";
	}
}

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires +";path=/";
}
function getip() {
	if (window.XMLHttpRequest)
		xmlhttp = new XMLHttpRequest();
	else
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

	xmlhttp.open("GET", "http://api.hostip.info/get_html.php", false);
	xmlhttp.send();

	hostipInfo = xmlhttp.responseText.split("\n");

	for (i = 0; hostipInfo.length >= i; i++) {
		ipAddress = hostipInfo[i].split(":");
		if (ipAddress[0] == "IP")
			return ipAddress[1];
	}

	return false;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0)
			return c.substring(name.length, c.length);
	}
	return "";
}
function deleteCookie(name) {
	alert('test');
	var exp=new Date();
	exp.setTime(exp.getTime() - 100000);
	var cval=getCookie(name);
	alert(cval);
	alert(exp);
	document.cookie=name+"="+cval+"; expires="+exp.toGMTString();
	alert('test2');
	window.location.href = "index.html";
}
function getLog() {
	var log = getCookie('log');
	log = log.charAt(0).toUpperCase() + log.slice(1);
	return log;
}
function getProject() {
	var project = getCookie('project');
	project = project.charAt(0) + project.slice(1);
	return project;
}
