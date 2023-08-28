var xmlhttp = new XMLHttpRequest();
var url =  "https://www.google.com/";
function callback(){console.log('response received')}; 
var call = function () {
        xmlhttp.open('GET',url, true);
	    xmlhttp.onreadystatechange = callback;
        xmlhttp.send(null);
    }
call();
