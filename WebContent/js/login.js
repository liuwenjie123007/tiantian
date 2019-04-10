function saveUsername(){
	var check = document.getElementById('check');
	var username = document.getElementById('username');
	if(check.checked == true){
		setCookie('username',username.value,7);
	}else{
		setCookie('username',"",-1);
	}
}

window.onload = function(){
	if(getCookie('username')!=''){
		var check = document.getElementById('check');
		var un = document.getElementById('username');
		un.value = getCookie('username');
		check.checked = true;
	}
}



function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
	}
	return "";
}
