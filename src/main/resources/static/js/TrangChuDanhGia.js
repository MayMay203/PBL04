// View detail of activity
const body = document.querySelector("body");
const view_link = document.getElementsByClassName("view-detail");
const modal = document.querySelector(".modal_")
const btn_close = document.getElementsByClassName("btn-close-detail")
const image_activity = document.querySelector(".images-Activity")
//Ham handleViewDetail dat ben header
for( let view of view_link){
    view.addEventListener("click", handleViewDetail)
}

//Ham closeViewDetail dung chung dat ben header
for (let btn of btn_close) {
    btn.addEventListener("click",closeViewDetail );
}

$(document).ready(function(){
    $('.act-slider').slick({
        dots: true,
        slidesToShow: 1,
        arrows: false,
        vertical: false,
        speed: 100
    });
});

const btn_evaluaion_Act = document.querySelector(".btn-evaluation-Act");
btn_evaluaion_Act.addEventListener("click",(e)=> {
    if (btn_evaluaion_Act.dataset.value == null) {
        var header = document.getElementById("myHeader");
        header.classList.remove("sticky");
        $('#DangNhapModal').modal('show');
    } else {
        window.location.href = `/danh-gia?id=${btn_evaluaion_Act.dataset.value}`;
    }
})

$(document).ready(function () {
    $('.cmt-slider').slick({
        autoplay: true,
        autoplaySpeed: 2500,
        vertical: true,
        slidesToShow: 3,
        arrows: false,
        pauseOnHover: true,
    })
})

