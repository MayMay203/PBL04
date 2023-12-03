$(document).ready(function(){
  $('.act-slider').slick({
    dots:true,
    slidesToShow:1,
    arrows:false,
    vertical:false,
    speed:100
  })
});

$(document).ready(function(){
  $('.list_card').slick({
    autoplay:true,
    infinite:false,
    slidesToShow:3,
    vertical:true,
    prevArrow: '<div class="slick-prev"><i class= "bi bi-chevron-compact-up"></i></div>',
    nextArrow: '<div class="slick-next"><i class="bi bi-chevron-compact-down"></i></div>',
  })
});

document.addEventListener("DOMContentLoaded", function () {
  var search_input = document.querySelector(".search-input");
  var search_info = document.querySelector(".search-info");

  search_input.addEventListener("focus", function (e) {
      search_info.classList.add('display-block');
  });

  search_input.addEventListener("blur", function (e) {
      search_info.classList.remove('display-block');
  });
});


