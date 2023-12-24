$(document).ready(function(){
    $('.sectionMember__aboutContent').slick({
        infinite: false,
        slidesToShow: 4,
        slidesToScroll: 4,
        arrow: true,
        draggable: false,
        prevArrow:"<button type='button' class='slick-prev pull-left'><i class='bi bi-caret-left'></i></button>",
        nextArrow:"<button type='button' class='slick-next pull-right'><i class='bi bi-caret-right' ></i></button>",
        dots: true,
    });

});

// const body = document.querySelector("body");
// const modal = document.querySelector(".modal_")
// const btn_close = document.getElementsByClassName("btn-close-detail")
// const image_activity = document.querySelector(".images-Activity")


var view_link_more = document.getElementsByClassName("link-more-evaluate");
const modal = document.querySelector(".modal_")
const body = document.querySelector("body");
const image_activity = document.querySelector(".images-Activity")
const btn_close = document.getElementsByClassName("btn-close-detail")
for( let view_more of view_link_more){
    view_more.addEventListener("click", handleViewDetail)
}
for (let btn of btn_close) {
    btn.addEventListener("click",closeViewDetail );
}


