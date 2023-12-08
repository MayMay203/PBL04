// View detail of activity
const body = document.querySelector("body");
const view_link = document.getElementsByClassName("view-detail");
const modal = document.querySelector(".modal_")
const btn_close = document.getElementsByClassName("btn-close-detail")

async function handleViewDetail(e){
    try{
        const IdAct =+e.target.dataset.value;
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        // console.log(responseData);
        modal.classList.add('display-flex')
        body.classList.add('overflow-hidden')
        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenHD;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        document.querySelector(".time-post-act").innerText= responseData.registerInfor.thoiGianDK;
        //     ẢNH
        document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
        //Generate comment
        const evaluationOfAct = responseData.evaluationOfAct;
        //    console.log(evaluationOfAct);
        const evaluationContainer = document.querySelector(".modal-comment");
        if (evaluationOfAct.length > 0) {
            const criteriaDiv = document.createElement("div");
            criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end","p-2", "criteria-evaluation")
            criteriaDiv.innerHTML=
                `    <h4 class="fs-9 green-color fst-italic me-4 text-center">Hoạt động<br>hữu ích</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>thường xuyên</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>rộng rãi</h4>
                `
            evaluationContainer.appendChild(criteriaDiv)
            evaluationOfAct.forEach(eva => {
                const newEvaluationDiv = document.createElement("div");
                newEvaluationDiv.classList.add("container", "d-flex", "mb-3", "comment-member");
                newEvaluationDiv.innerHTML = `
            <!-- Text comment -->
            <div class="container radius-1 bg-green">
                <div class="container p-1">
                    <img src="../static/images/av3.png" class="w-2_5 h-2_5 p-1 radius-1_8 modal-avatar" alt="">
                    <h7 class="green-color fs-11 fst-italic fw-medium">${eva.maTK.tenDN}</h7>
                </div>
                <p class="fs-10 fst-italic m-0 px-3">${eva.binhLuan}</p>
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN","dd/MM/yyyy").format(new Date(eva.thoiGianBL))}</p>
            </div>
            <!-- Check comment -->
            <div class="container d-flex align-items-center justify-content-center">
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eva.tieuChi1}" ${eva.tieuChi1?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eva.tieuChi2}" ${eva.tieuChi2?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-3" value="${eva.tieuChi3}" ${eva.tieuChi3?'checked':''}>
            </div>
        `
                evaluationContainer.appendChild(newEvaluationDiv);
            });
        }
    }catch(error){
        console.error(error)
    }
}
for( let view of view_link){
    view.addEventListener("click", handleViewDetail)
}

// Close view detail
function closeViewDetail(e) {
    //     Delete comment every activity
    const modal_comment = document.querySelector('.modal-comment');
    modal_comment.innerHTML='';
    modal.classList.remove('display-flex')
    body.classList.remove('overflow-hidden')
    // view_comment.classList.add('no-display')
    // write_comment.classList.add('no-display')
    // evaluation.classList.add('no-display')
    // edit_content.classList.remove('max_height-29_5rem')
    // view_organized.classList.add('no-display')
}
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
// export {handleViewDetail,closeViewDetail}


