<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	 <link rel="stylesheet" media="all" href="styles/sk.css" />
    <link rel="shortcut icon" href="favicon.ico">
	<title>SKUNK Game</title>
</head>
<body>
ola.....
<%= request.getAttribute("result") %>
####
<div id="container">

		<div>
			Choose your SkinK
		</div>
			<form action="/SkunkGame" method="get">
		<div class="box">
				<div id="skp1" class="skp">
					<button name="skin" value="skin1"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp2" class="skp">
					<button name="skin" value="skin2"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp3" class="skp">
					<button name="skin" value="skin3"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp4" class="skp">
					<button name="skin" value="skin4"><img src="images/boss_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp5" class="skp">
					<button name="skin" value="skin5"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp7" class="skp">
					<button name="skin" value="skin6"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp7" class="skp">
					<button name="skin" value="skin7"><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
		</div>
			</form>
		<div class="box">
			<div id="skl1" class="skl">S</div>
			<div id="skl2" class="skl">K</div>
			<div id="skl3" class="skl">U</div>
			<div id="skl4" class="skl">N</div>
			<div id="skl5" class="skl">K</div>
		</div>
		<div class="box">
				<div id="skp1" class="skp">
					<button  onclick='seat()'><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp2" class="skp">
					<button ><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp3" class="skp">
					<button ><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp4" class="skp">
					<button ><img src="images/boss_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp2" class="skp">
					<button ><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp3" class="skp">
					<button ><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
				<div id="skp4" class="skp">
					<button ><img src="images/sk_red_s.png" alt="Submit Form"></button>
				</div>
		</div>



<script>
	var eventSource = null;
	function seat() {
		console.log("seating...");
		source = new EventSource('http://localhost:8080/SkunkGame',  {withCredentials: true});
		/*eventSource.onopen 		= function() {};
		eventSource.onmessage 	= function(message) {console.log(message.data)};
		eventSource.onerror	 	= function(merr) {console.log("error..." + merr)};*/
		source.addEventListener('message', function(e) {
			  console.log(e.data);
			}, false);

			source.addEventListener('open', function(e) {
			  // Connection was opened.
			}, false);

			source.addEventListener('error', function(e) {
			  if (e.readyState == EventSource.CLOSED) {
			    // Connection was closed.
			  }
			}, false);
	}
</script>
</body>
</html>
