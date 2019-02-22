<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="all" href="/styles/sk.css" />
<link rel="stylesheet" media="all" href="/styles/dice.css" />
<link rel="stylesheet" media="all" href="/styles/smoke.css" />
<link rel="shortcut icon" href="/images/favicon.ico">
<title>SKUNK Game</title>


</head>
<body onload="sseSkunkGame()">

	<div id="container">

	<div>Choose your SkinK</div>


		<div id="overlay">
			<div class=box>
				<div id="cube1" class="cube1">
					<div class="cube1_face cube1_face_front"><img src="/images/seis.png"></div>
					<div class="cube1_face cube1_face_back"><img src="/images/jason.png"></div>
					<div class="cube1_face cube1_face_right"><img src="/images/cinco.png"></div>
					<div class="cube1_face cube1_face_left"><img src="/images/dois.png"></div>
					<div class="cube1_face cube1_face_top"><img src="/images/treis.png"></div>
					<div class="cube1_face cube1_face_bottom"><img src="/images/quatro.png"></div>
				</div>
				<div id="cube2" class="cube2">
					<div class="cube2_face cube2_face_front"><img src="/images/seis.png"></div>
					<div class="cube2_face cube2_face_back"><img src="/images/jason.png"></div>
					<div class="cube2_face cube2_face_right"><img src="/images/cinco.png"></div>
					<div class="cube2_face cube2_face_left"><img src="/images/dois.png"></div>
					<div class="cube2_face cube2_face_top"><img src="/images/treis.png"></div>
					<div class="cube2_face cube2_face_bottom"><img src="/images/quatro.png"></div>
				</div>
				<div id="cube3" class="cube3">
					<div class="cube3_face cube3_face_front"><img src="/images/seis.png"></div>
					<div class="cube3_face cube3_face_back"><img src="/images/jason.png"></div>
					<div class="cube3_face cube3_face_right"><img src="/images/cinco.png"></div>
					<div class="cube3_face cube3_face_left"><img src="/images/dois.png"></div>
					<div class="cube3_face cube3_face_top"><img src="/images/treis.png"></div>
					<div class="cube3_face cube3_face_bottom"><img src="/images/quatro.png"></div>
				</div>
			
			</div>

		</div>
	
		<div class="box">
			<div>
				<button id="skunk1_choose" class="on"
					onclick="skunkMove('skunk1', 'choose')">
					<img id="img_choose" src="images/sk_red_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk2_choose" class="on"
					onclick="skunkMove('skunk2', 'choose')">
					<img lass="img_choose" src="images/sk_red_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk3_choose" class="on"
					onclick="skunkMove('skunk3', 'choose')">
					<img id="img_choose" src="images/sk_red_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk4_choose" class="on"
					onclick="skunkMove('skunk4', 'choose')">
					<img src="images/boss_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk5_choose" class="on"
					onclick="skunkMove('skunk5', 'choose')">
					<img lass="img_choose" src="images/sk_red_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk6_choose" class="on"
					onclick="skunkMove('skunk6', 'choose')">
					<img lass="img_choose" src="images/sk_red_s.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk7_choose" class="on"
					onclick="skunkMove('skunk7', 'choose')">
					<img lass="img_choose" src="images/sk_red_s.png" alt="Skunk 7">
				</button>
			</div>
		</div>
	<!--  	
		<div class="box">
			<div class="normal">S</div>
			<div id="sk_letter_2" class="normal">K</div>
			<div id="sk_letter_3" class="normal">U</div>
			<div id="sk_letter_4" class="normal">N</div>
			<div class="normal">K</div>
		</div> -->
		<div>
		<section>
		<video src=“/images/smoke.mp4” autoplay muted></video>
			<h1 id="skunk_title">
				<span>S</span>
				<span>K</span>
				<span>U</span>
				<span>N</span>
				<span>K</span>
			</h1>
		</section>
		</div>
		<div class="box">
			<div>
				<button id="skunk1_play" class="off"
					onclick="skunkMove('skunk1', 'down')">
					<img src="images/dot.png" alt="skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk2_play" class="off"
					onclick="skunkMove('skunk2', 'down')">
					<img src="images/dot.png" alt="skunk 2">
				</button>
			</div>
			<div>
				<button id="skunk3_play" class="off"
					onclick="skunkMove('skunk3', 'down')">
					<img src="images/dot.png" alt="skunk 3">
				</button>
			</div>
			<div>
				<button id="skunk4_play" class="off">
					<img src="images/dot.png" alt="skunk 4">
				</button>
			</div>
			<div>
				<button id="skunk5_play" class="off"
					onclick="skunkMove('skunk5', 'down')">  
					<img src="images/dot.png" alt="skunk 5">
				</button>
			</div>
			<div>
				<button id="skunk6_play" class="off"
					onclick="skunkMove('skunk6', 'down')">
					<img src="images/dot.png" alt="skunk 6">
				</button>
			</div>
			<div>
				<button id="skunk7_play" class="off"
					onclick="skunkMove('skunk7', 'down')">
					<img src="images/dot.png" alt="skunk 7">
				</button>
			</div>
		</div>
		
		<div class="box">
			<div id="skunk1_score">
				<div id="skunk1_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk1_score_total" class="score_total on"></div>
			</div>
			<div id="skunk2_score">
				<div id="skunk2_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk2_score_total" class="score_total on"></div>
			</div>
			<div id="skunk3_score">
				<div id="skunk3_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk3_score_total" class="score_total on"></div>
			</div>
			
			<div>
				<div>
					<button id="play_finish" class="off"
						onclick="">
						<img src="images/dot.png" alt="Play - Finish">
					</button>
				</div>
			</div>
			
			<div id="skunk5_score">
				<div id="skunk5_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk5_score_total" class="score_total on"></div>
			</div>
			<div id="skunk6_score">
				<div id="skunk6_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk6_score_total" class="score_total on"></div>
			</div>
			<div id="skunk7_score">
				<div id="skunk7_score_ride" class="box on">
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
					<div class="score_ride"></div>
				</div>
				<div id="skunk7_score_total" class="score_total on"></div>
			</div>
		</div>
		
		<div>
			<div>
				<button id="dice1" class="off"
					onclick="skunkMove('skunk4', 'roll1')">
					<img src="images/1dado.png" alt="skunk 4" width="100px">
				</button>
			</div>
			<div>
				<button id="dice2" class="off"
					onclick="skunkMove('skunk4', 'roll2')">
					<img src="images/2dados.png" alt="skunk 4" width="100px">
				</button>
			</div>
			<div>
				<button id="dice3" class="off"
					onclick="skunkMove('skunk4', 'roll3')">
					<img src="images/3dados.png" alt="skunk 4" width="100px">
				</button>
			</div>
		</div>
</body>
<script>



	var eventSource = null;
	var clientInitialSkunkId = "skunk0";

	var clientSkunkSseId = getCookie("skunkSseId");
	var clientSkunkGameId = getCookie("skunkGameId");
	var clientSkunkId = getCookie("skunkId");
	var skunkCookie = "skunkSseId=0&skunkId=" + clientInitialSkunkId
			+ "&skunkGameId=0";

	if ((clientSkunkSseId != null) && (clientSkunkSseId.length > 0)) {
		if ((clientSkunkGameId != null) && (clientSkunkGameId.length > 0)) {
			if ((clientSkunkId != null) && (clientSkunkId.length > 0)) {
				skunkCookie = "skunkSseId=" + clientSkunkSseId
						+ "&skunkGameId=" + clientSkunkGameId + "&skunkId="
						+ clientSkunkId;
			}
		}
	}
	console.log("skunkCookie = " + skunkCookie);

	function sseSkunkGame() {
		console.log("sseSkunkGame...");
		source = new EventSource('http://localhost:8080/SkunkGame?' + skunkCookie);
		/*source.onopen 	= function() {};
		eventSource.onmessage 	= function(message) {console.log(message.data)};
		eventSource.onerror	 	= function(merr) {console.log("error..." + merr)};*/

		source.addEventListener('message', function(e) {
			var data = e.data.split('\n').join('');
			console.log("message= " + data);
			eval(data);
		}, false);

		source.addEventListener('skunkSseId', function(e) {
			console.log("skunkSseId= " + e.data);
			setCookie("skunkSseId", e.data, 1);
		}, false);

		source.addEventListener('skunkGameId', function(e) {
			console.log("skunkGameId= " + e.data);
			setCookie("skunkGameId", e.data, 1);
		}, false);

		source.addEventListener('skunkId', function(e) {
			console.log("skunkId= " + e.data);
			setCookie("skunkId", e.data, 1);
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
	function skunkMove(skunkId, skunkAction) {
		// if different no move allowed
		console.log("compara skunkId=" + skunkId + " com o do cookie="
				+ getCookie("skunkId"));
		if ((skunkId == getCookie("skunkId") || ((getCookie("skunkId") == clientInitialSkunkId) && (skunkAction == "choose")))) {
			console.log("vai sse..parms:skunkSseId=" + getCookie("skunkSseId")
					+ "&skunkGameId=" + getCookie("skunkGameId") + "&skunkId="
					+ skunkId + "&skunkAction=" + skunkAction);
			var request = new XMLHttpRequest();

			request.open("GET", "/SkunkGame?skunkSseId="
					+ getCookie("skunkSseId") + "&skunkGameId="
					+ getCookie("skunkGameId") + "&skunkId=" + skunkId
					+ "&skunkAction=" + skunkAction, true);
			request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
			request.send(null);
			console.log("foi sse...???");
		}
	}
	function getCookie(cname) {
		var name = cname + "=";
		var decodedCookie = decodeURIComponent(document.cookie);
		var ca = decodedCookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return "";
	}
	function setCookie(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}
	function removeCookies() {
		var res = document.cookie;
		var multiple = res.split(";");
		for (var i = 0; i < multiple.length; i++) {
			var key = multiple[i].split("=");
			document.cookie = key[0]
					+ " =; expires = Thu, 01 Jan 1970 00:00:00 UTC";
		}
	}

</script>

</html>
