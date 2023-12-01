$(document).ready(function(){
    $('.sectionMember__aboutContent').slick({
        infinite: false,
        slidesToShow: 4,
        slidesToScroll: 2,
        arrow: true,
        draggable: false,
        prevArrow:"<button type='button' class='slick-prev pull-left'><i class='bi bi-caret-left'></i></button>",
        nextArrow:"<button type='button' class='slick-next pull-right'><i class='bi bi-caret-right' ></i></button>",
        dots: true,
    });

});


