var cube = document.querySelector('.cube');
var radioGroup = document.querySelector('.radio-group');
var currentClass = '';

var show = document.getElementById('show');

var min = 0;
var max = 5;
var face = "front";
var currentFace = face;

var faces = ["front", "back", "left", "right", "top", "bottom"];

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
	cube.classList.remove('show-' + currentFace); 
	currentFace = face;
	cube.classList.add( 'show-' + face );
	show.innerText = face;
}

function getRandom(max, min) {
	  return (Math.floor((Math.random() * max) + min));
	}

