// Evaluate
const modal = document.querySelector(".modal_")
const body = document.querySelector("body");
const view_comment = document.querySelector(".view-comment");
const evaluation = document.querySelector(".evaluation")
const write_comment = document.querySelector(".write-comment")
const btn_evaluate_member = document.getElementsByClassName("btn_eva_member");
const btn_evaluate = document.getElementsByClassName("btn-evaluate")
const edit_content = document.querySelector(".content-evaluate");
const view_organized = document.querySelector(".view-organized");
const image_activity = document.querySelector(".images-Activity")
const members_actitivy = document.querySelector(".members-Actitivy")
async function Evaluation(e)  {
    try{
        const IdAct =+e.target.dataset.value;
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        // console.log(responseData);
        modal.classList.add('display-flex')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')
        edit_content.classList.add('max_height-27rem')
        write_comment.classList.remove('no-display')
        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenhd;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        document.querySelector(".avatar-host").src = responseData.registerInfor.maTK.anhDaiDien;
        document.querySelector(".time-post-act").innerText= responseData.registerInfor.thoiGianDK;
        //     ẢNH
        image_activity.innerHTML = "";
        imagesList.forEach(image => {
            image_activity.innerHTML += `<img src="${image.anh}" alt="anhHoatDong" class="height-10rem flex-1 p-1">`;
        });
        document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
        const evaluationOfAct = responseData.evaluationOfAct;
        //    console.log(evaluationOfAct);
        const evaluationContainer = document.querySelector(".view-comment");
        if (evaluationOfAct.length > 0) {
            const criteriaDiv = document.createElement("div");
            criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end","p-2", "criteria-evaluation")
            criteriaDiv.innerHTML=
                `    <h4 class="fs-9 green-color fst-italic me-4 text-center">Hoạt động<br>hữu ích</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>thường xuyên</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>rộng rãi</h4>
                `
            evaluationContainer.appendChild(criteriaDiv)
            evaluationOfAct.forEach(eval => {
                const newEvaluationDiv = document.createElement("div");
                newEvaluationDiv.classList.add("container", "d-flex", "mb-3", "comment-member");
                newEvaluationDiv.innerHTML = `
            <!-- Text comment -->
            <div class="container radius-1 bg-green">
                <div class="container p-1">
                    <img src="${eval.maTK.anhDaiDien}" class="w-2_5 h-2_5 p-1 radius-1_8 modal-avatar" alt="">
                    <h7 class="green-color fs-11 fst-italic fw-medium">${eval.maTK.tenDN}</h7>
                </div>
                <p class="fs-10 fst-italic m-0 px-3">${eval.binhLuan}</p>
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN","dd/MM/yyyy").format(new Date(eval.thoiGianBL))}</p>
            </div>
            <!-- Check comment -->
            <div class="container d-flex align-items-center justify-content-center">
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi1}" ${eval.tieuChi1?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi2}" ${eval.tieuChi2?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-3" value="${eval.tieuChi3}" ${eval.tieuChi3?'checked':''}>
            </div>
        `
                evaluationContainer.appendChild(newEvaluationDiv);
            });
        }
    }catch(Error){
        console.error(Error);
    }
}
for (var btn of btn_evaluate) {
    btn.addEventListener("click", Evaluation)
}

async function EvaluationMember(e) {

    try{
        const IdAct =+e.target.dataset.value;
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        const membersList = responseData.membersList;
        // console.log(responseData);
        modal.classList.add('display-flex')
        edit_content.classList.remove('max_height-27rem')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')
        view_organized.classList.remove('no-display')

        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenhd;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        document.querySelector(".avatar-host").src = responseData.registerInfor.maTK.anhDaiDien;
        document.querySelector(".time-post-act").innerText= responseData.registerInfor.thoiGianDK;
        //    image summary activity
        image_activity.innerHTML = "";
        imagesList.forEach(image => {
            image_activity.innerHTML += `<img src="${image.anh}" alt="anhHoatDong" class="height-10rem flex-1 p-1">`;
        });
        document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
        const evaluationOfAct = responseData.evaluationOfAct;
        //    console.log(evaluationOfAct);
        const evaluationContainer = document.querySelector(".view-comment");
        if (evaluationOfAct.length > 0) {
            const criteriaDiv = document.createElement("div");
            criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end","p-2", "criteria-evaluation")
            criteriaDiv.innerHTML=
                `    <h4 class="fs-9 green-color fst-italic me-4 text-center">Hoạt động<br>hữu ích</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>thường xuyên</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>rộng rãi</h4>
                `
            evaluationContainer.appendChild(criteriaDiv)
            evaluationOfAct.forEach(eval => {
                const newEvaluationDiv = document.createElement("div");
                newEvaluationDiv.classList.add("container", "d-flex", "mb-3", "comment-member");
                newEvaluationDiv.innerHTML = `
            <!-- Text comment -->
            <div class="container radius-1 bg-green">
                <div class="container p-1">
                    <img src="${eval.maTK.anhDaiDien}" class="w-2_5 h-2_5 p-1 radius-1_8 modal-avatar" alt="">
                    <h7 class="green-color fs-11 fst-italic fw-medium">${eval.maTK.tenDN}</h7>
                </div>
                <p class="fs-10 fst-italic m-0 px-3">${eval.binhLuan}</p>
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN","dd/MM/yyyy").format(new Date(eval.thoiGianBL))}</p>
            </div>
            <!-- Check comment -->
            <div class="container d-flex align-items-center justify-content-center">
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi1}" ${eval.tieuChi1?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi2}" ${eval.tieuChi2?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-3" value="${eval.tieuChi3}" ${eval.tieuChi3?'checked':''}>
            </div>
        `
                evaluationContainer.appendChild(newEvaluationDiv);
            });
            //Members of activity
            members_actitivy.innerHTML = '';
            console.log(membersList);
            membersList.forEach(member=>{
                members_actitivy.innerHTML += `
                     <div class="container p-2 col-6 radius-1_8 position-relative member-evaluate">
                            <div class="container d-flex">
                                <img class="w-2_5 h-2_5 ml-0_1 radius-1_8 p-1" src="${member.maTK.anhDaiDien}" alt="">
                                <h4 class="green-color fs-7 my-1 mt-1 p-2">${member.maTK.tenDN}</h4>
                                <h10 class="fs-7 p-2">5<i class="bi bi-star-fill yellow-color fs-7 py-1"></i></h10>
                            </div>
                            <!-- Form evaluate -->
                            <div
                                    class="container bg-light radius-1 p-1 position-absolute w-75 top-75 start-0 end-0 no-display form-evaluate">
                                <div class="container px-4 py-2">
                                    <p class="fs-9 grey-dark-color">Rất tích cực <input type="checkbox"></p>
                                    <p class="fs-9 grey-dark-color">Tích cực <input type="checkbox"></p>
                                    <p class="fs-9 grey-dark-color m-0">Không tích cực <input type="checkbox"></p>
                                </div>
                                <div class="container d-flex justify-content-center">
                                    <div class="btn bg-btn fs-11 fw-bold white-color p-1 px-2 m-2 button-exit">Thoát</div>
                                    <div class="btn bg-btn fs-11 fw-bold white-color p-1 px-3 m-2 button-save">Lưu</div>
                                </div>
                            </div>
                        </div>
                `;
            })
        }
    }catch(Error){
        console.error(Error);
    }
}
for (var btn of btn_evaluate_member) {
    btn.addEventListener("click",EvaluationMember)
}

// Close form evaluation
const btn_close = document.getElementsByClassName("btn-close-detail")
function closeFormEvaluation(e) {
    const modal_comment = document.querySelector('.view-comment');
    modal_comment.innerHTML='';
    modal.classList.remove('display-flex')
    body.classList.remove('overflow-hidden')
    view_comment.classList.add('no-display')
    write_comment.classList.add('no-display')
    evaluation.classList.add('no-display')
    edit_content.classList.remove('max_height-27rem')
    view_organized.classList.add('no-display')
}
for (var btn of btn_close) {
    btn.addEventListener("click",closeFormEvaluation);
}


//Evalutate members
const member_evaluate = document.getElementsByClassName("member-evaluate")
var btn_eva_exit = document.getElementsByClassName("btn-exit")
for (var member of member_evaluate) {
    member.addEventListener("click", function(e) {
        const target = e.target;
        const container = target.closest(".member-evaluate");
        var allForms = document.querySelectorAll(".form-evaluate");
        for (var form of allForms) {
            form.classList.remove("display-block");
        }
        const childForm = container.querySelector(".form-evaluate");
        childForm.classList.add("display-block");
    });
}

//Chưa được
var btn_exit = document.getElementsByClassName("button-exit");
for (var btn of btn_exit) {
    btn.addEventListener("click", function (e) {
        var allForms = document.querySelectorAll(".form-evaluate");
        for (var form of allForms) {
            form.classList.remove("display-block");
        }
    })
}


// save

//View evaluation
const btn_view_evaluation = document.querySelector(".btn-view-evaluation");
const btn_evaluate_mb = document.querySelector(".btn-evaluate-member");
btn_view_evaluation.addEventListener("click", function (e) {
    btn_view_evaluation.classList.add("green")
    btn_evaluate_mb.classList.remove("green")
    evaluation.classList.add("no-display")
    view_comment.classList.remove('no-display')
})
btn_evaluate_mb.addEventListener("click", function (e) {
    btn_view_evaluation.classList.remove("green")
    btn_evaluate_mb.classList.add("green")
    view_comment.classList.add('no-display')
    evaluation.classList.remove('no-display')
})

//Evaluate star
var btn_star = document.getElementsByClassName("btn-star");
for (var btn of btn_star) {
    btn.addEventListener("click", (e) => {
        for (var star of btn_star) {
            star.classList.remove("yellow-color")
        }
        e.target.classList.add("yellow-color");
        var previousSibling = e.target.previousElementSibling;
        while (previousSibling) {
            previousSibling.classList.add("yellow-color");
            previousSibling = previousSibling.previousElementSibling;
        }
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
    $('.origanized-slider').slick({
        infinite: false,
        slidesToShow: 3,
        vertical: true,
        prevArrow: '<div class="slick-prev"><i class="bi bi-chevron-compact-up"></i></div>',
        nextArrow: '<div class="slick-next"><i class="bi bi-chevron-compact-down"></i></div>',
    })
});