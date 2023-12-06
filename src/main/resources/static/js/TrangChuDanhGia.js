// View detail of activity
const body = document.querySelector("body");
const view_link = document.getElementsByClassName("view-detail");
const modal = document.querySelector(".modal_")
for( let view of view_link){
    view.addEventListener("click", async (e) =>{
        try{
            const idhd =+e.target.dataset.value;
            const response =await fetch(`/hoat-dong/xem-chi-tiet/${idhd}`);
            if(!response.ok){
                throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
            }
            const responseData = await response.json();
            console.log(responseData);
            modal.classList.add('display-flex')
            body.classList.add('overflow-hidden')
            document.querySelector(".modal-tenHD").innerText = responseData.activity.tenHD;
            document.querySelector(".modal-moTa").innerText = responseData.activity.moTa;
        //     ẢNH
            document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá";
        }catch(error){
            console.error(error)
        }
        }
    )
}



// Close view detail
const btn_close = document.getElementsByClassName("btn-close-detail")
for (let btn of btn_close) {
    btn.addEventListener("click", function (e) {
        modal.classList.remove('display-flex')
        body.classList.remove('overflow-hidden')
        // view_comment.classList.add('no-display')
        // write_comment.classList.add('no-display')
        evaluation.classList.add('no-display')
        edit_content.classList.remove('max_height-29_5rem')
        view_organized.classList.add('no-display')
    });
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




