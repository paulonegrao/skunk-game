$(document).ready(function(){

});

function smokeAll() {

  let once = {
    once : true
  };

  var video = document.getElementById('skunk_smoke');

  video.play();

  video.addEventListener('ended', function() {
    $("#letter1, #letter2, #letter3, #letter4, #letter5").addClass('turn_white');
  }, once);

};

function smokeLetter(letter) {

  var video = document.getElementById('skunk_smoke');

  video.classList.remove('hide');
  $('#' + letter).removeClass('turn_white');

  function matchDuration() {
      if (video.currentTime >= $('#' + letter).attr('data_stop')) {
         video.removeEventListener('timeupdate', matchDuration);
         video.classList.add('hide');
      };
  };

  video.currentTime = $('#' + letter).attr('data_start');
  video.addEventListener('timeupdate', matchDuration);
  video.play();
};
