<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

<link rel="stylesheet" media="all" href="/styles/sk.css" />
<link rel="stylesheet" media="all" href="/styles/dice.css" />
<link rel="stylesheet" media="all" href="/styles/smoke.css" />
<link rel="shortcut icon" href="/images/favicon.ico">
<title>SKUNK Game</title>


</head>
<body onload="sseSkunkGame()">

	<div id="container">

	<div>Choose your Skin</div>
		<div id="overlay">
			<div class=box>
				<div id="cube1" class="cube1">
					<div class="cube1_face cube1_face_front"><img src="/images/seis.png"></div>
					<div id="cube1_boss" class="cube1_face cube1_face_back default"><img src="/images/dot.png"></div>
					<div class="cube1_face cube1_face_right"><img src="/images/cinco.png"></div>
					<div class="cube1_face cube1_face_left"><img src="/images/dois.png"></div>
					<div class="cube1_face cube1_face_top"><img src="/images/treis.png"></div>
					<div class="cube1_face cube1_face_bottom"><img src="/images/quatro.png"></div>
				</div>
				<div id="cube2" class="cube2">
					<div class="cube2_face cube2_face_front"><img src="/images/seis.png"></div>
					<div id="cube2_boss" class="cube2_face cube2_face_back default"><img src="/images/dot.png"></div>
					<div class="cube2_face cube2_face_right"><img src="/images/cinco.png"></div>
					<div class="cube2_face cube2_face_left"><img src="/images/dois.png"></div>
					<div class="cube2_face cube2_face_top"><img src="/images/treis.png"></div>
					<div class="cube2_face cube2_face_bottom"><img src="/images/quatro.png"></div>
				</div>
				<div id="cube3" class="cube3">
					<div class="cube3_face cube3_face_front"><img src="/images/seis.png"></div>
					<div id="cube3_boss" class="cube3_face cube3_face_back default"><img src="/images/dot.png"></div>
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
					<img id="img_choose" src="images/skunk1_choose.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk2_choose" class="on"
					onclick="skunkMove('skunk2', 'choose')">
					<img lass="img_choose" src="images/skunk2_choose.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk3_choose" class="on"
					onclick="skunkMove('skunk3', 'choose')">
					<img id="img_choose" src="images/skunk3_choose.png" alt="Skunk 1">
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
					<img lass="img_choose" src="images/skunk5_choose.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk6_choose" class="on"
					onclick="skunkMove('skunk6', 'choose')">
					<img lass="img_choose" src="images/skunk6_choose.png" alt="Skunk 1">
				</button>
			</div>
			<div>
				<button id="skunk7_choose" class="on"
					onclick="skunkMove('skunk7', 'choose')">
					<img lass="img_choose" src="images/skunk7_choose.png" alt="Skunk 7">
				</button>
			</div>
		</div>

		<section>
				<video id="skunk_smoke" src="images/smoke1.mp4" type="video/mp4" muted class=""></video>
				<h1>
					<span id="letter1" onclick="smokeLetter('letter1')" data_start="0" data_stop=".5">S</span>
					<span id="letter2" onclick="smokeLetter('letter2')" data_start="1.2" data_stop="1.20001">K</span>
					<span id="letter3" onclick="smokeLetter('letter3')" data_start="2.1" data_stop="2.1">U</span>
					<span id="letter4" onclick="smokeLetter('letter4')" data_start="3" data_stop="3">N</span>
					<span id="letter5" onclick="smokeLetter('letter5')" data_start="4" data_stop="4">K</span>
				</h1>
		</section>

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
					<img src="images/1dado.png" alt="skunk 4" width="180px">
				</button>
			</div>
			<div>
				<button id="dice2" class="off"
					onclick="skunkMove('skunk4', 'roll2')">
					<img src="images/2dados.png" alt="skunk 4" width="180px">
				</button>
			</div>
			<div>
				<button id="dice3" class="off"
					onclick="skunkMove('skunk4', 'roll3')">
					<img src="images/3dados.png" alt="skunk 4" width="180px">
				</button>
			</div>
		</div>
<script src="/js/smoke.js"></script>
<script>
	var eventSource = null;
	var clientInitialSkunkId = "skunk0";

	var clientSkunkSseId = getCookie("skunkSseId");
	var clientSkunkGameId = getCookie("skunkGameId");
	var clientSkunkId = getCookie("skunkId");
	var skunkCookie = "skunkSseId=0&skunkId=" + clientInitialSkunkId + "&skunkGameId=0";

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
		smokeAll();
		if (location.hostname === "localhost" || location.hostname === "127.0.0.1") {
			source = new EventSource('http://localhost:8080/SkunkGame?' + skunkCookie);
		} else {
			source = new EventSource('http://skunkgame.herokuapp.com/SkunkGame?' + skunkCookie);
		}
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
</body>
</html>
