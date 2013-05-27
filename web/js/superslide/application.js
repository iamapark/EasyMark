$(document).ready(function() {
  $(document).on('init.slides', function() {
    $('.loading-container').fadeOut(function() {
      $(this).remove();
    });
  });

  $('#slides').superslides({
    slide_speed: 800,
    pagination: false,
    hashchange: true,
    scrollable: false
  });
});