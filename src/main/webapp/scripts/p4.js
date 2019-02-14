var cubespinner = document.querySelector('.cubespinner');

var currentClass = '';

var show = document.getElementById('show');

var min = 0;
var max = 6;
var face = "face1";
var currentFace = face;

var faces = ["front", "back", "right", "left", "top", "bottom"];

show.onclick = function() {
		loopFace();
	/*	myVar = setTimeout(loopFace, 500);
		myVar = setTimeout(loopFace, 1000);*/
		
}

function loopFace() {
	while (currentFace == face ) {
  		face = faces[getRandom(max, min)];
  		console.log(face);
  	}
	cubespinner.classList.remove(currentFace); 
	currentFace = face;
	cubespinner.classList.add(face);
	show.innerText = face;
}

function getRandom(max, min) {
	  return (Math.floor((Math.random() * max) + min));
	}

