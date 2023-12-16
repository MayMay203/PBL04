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
const members_activity = document.querySelector(".members-Actitivy")
const btn_send_cmt = document.querySelector(".btn-send-cmt");
const btn_star = document.querySelectorAll(".btn-star")
const text_cmt = document.querySelector(".text-comment")
const check_survey = document.querySelectorAll(".check-survey");
var score = 0;
var maHD;
var maHDTC;
var scoreMb;
const member_infor = document.querySelector(".member-infor");

//Danh gia hoat dong
async function Evaluation(e)  {
    try{
        const IdAct =+e.target.dataset.value;
        maHD = IdAct;
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
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
                <p class="fs-10 green_light-color fst-italic m-0 px-3">Điểm cho ban tổ chức: ${eval.diemTC}</p>
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

//Danh gia thanh vien
function hideFormEvaluation(e) {
    const allForms = document.querySelectorAll(".form-evaluate");
    for (let form of allForms) {
        form.classList.remove("display-block");
        // console.log(form)
    }
}

// Ham luu va cap nhat lai thong tin danh gia
async function saveEvaluation(e) {
    var maTKDG = +e.target.value;
    const evaluationContainer = e.target.closest(".form-evaluate");
    const evaluate_memb = evaluationContainer.querySelectorAll(".evaluate-memb")
    var valueScore = [];
    for (eval of evaluate_memb) {
        valueScore.push(eval.checked)
    }
    scoreMb = 0;
    valueScore.forEach(value => {
        for (let i = 0; i < valueScore.length; i++) {
            if (valueScore[i] === true) {
                if (i === 0) scoreMb = 5;
                else if (i === 1) scoreMb = 4;
                else scoreMb = 1;
                break;
            }
        }
    })
    // Luu diem thanh vien vao co so du lieu
    var url = `/danh-gia/danh-gia-thanh-vien?maTK=${maTKDG}&maHD=${maHDTC}&diemTNV=${scoreMb}`
    var response = await fetch(url, {
        method: "POST"
    })
    if (!response.ok) {
        console.log("Lỗi HTTP! Trạng thái " + response.status)
    } else {
        //Cap nhat lai diem
        const response = await fetch(`/hoat-dong/xem-chi-tiet/${maHDTC}`);
        if (!response.ok) {
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const membersList = responseData.membersList;
        const scores = responseData.scores;
        members_activity.innerHTML = '';
        var i = 0;
        membersList.forEach(member => {
            members_activity.innerHTML += `
                     <div class="container p-2 col-6 radius-1_8 position-relative member-evaluate">
                            <div class="container d-flex">
                                <img class="w-2_5 h-2_5 ml-0_1 radius-1_8 p-1" src="${member.maTK.anhDaiDien}" alt="">
                                <h4 class="green-color fs-7 my-1 mt-1 p-2">${member.maTK.tenDN}</h4>
                                <h10 class="fs-7 p-2">${scores[i++]}<i class="bi bi-star-fill yellow-color fs-7 py-1"></i></h10>
                            </div>
                            <!-- Form evaluate -->
                            <div
                                 class="container bg-light radius-1 p-1 position-absolute w-75 top-75 start-0 end-0 no-display form-evaluate">
                                <div class="container px-4 py-2">
                                    <p class="fs-9 grey-dark-color">Rất tích cực<input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                    <p class="fs-9 grey-dark-color">Tích cực <input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                    <p class="fs-9 grey-dark-color m-0">Không tích cực <input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                </div>
                                <div class="container d-flex justify-content-center">
                                    <button class="btn bg-btn fs-10 fw-bold white-color w-50 m-2 btn-save" value="${member.maTK.id}">Lưu đánh giá</button>
<!--                                    <button class="btn bg-btn fs-11 fw-bold white-color p-1 px-3 m-2 btn-exit">Thoát</button>-->
                                </div>
                            </div>
                     </div>
                `;
        })
        for (var member of member_evaluate) {
            member.addEventListener("click", formEvaluation)
        }
        // Ham chuc nang danh gia thanh vien
        const btn_save = document.querySelectorAll(".btn-save");
        for(btn of btn_save){
            btn.addEventListener("click",saveEvaluation)
        }
    }
}
async function EvaluationMember(e) {

    try{
        const IdAct =+e.target.dataset.value;
        maHDTC = IdAct;
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        const membersList = responseData.membersList;
        const scores = responseData.scores;
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
                <p class="fs-10 green_light-color fst-italic m-0 px-3">Điểm cho ban tổ chức: ${eval.diemTC}</p>
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
            members_activity.innerHTML = '';
            var i=0;
            membersList.forEach(member=>{
                members_activity.innerHTML += `
                     <div class="container p-2 col-6 radius-1_8 position-relative member-evaluate">
                            <div class="container d-flex">
                                <img class="w-2_5 h-2_5 ml-0_1 radius-1_8 p-1" src="${member.maTK.anhDaiDien}" alt="">
                                <h4 class="green-color fs-7 my-1 mt-1 p-2">${member.maTK.tenDN}</h4>
                                <h10 class="fs-7 p-2">${scores[i++]}<i class="bi bi-star-fill yellow-color fs-7 py-1"></i></h10>
                            </div>
                            <!-- Form evaluate -->
                            <div
                                 class="container bg-light radius-1 p-1 position-absolute w-75 top-75 start-0 end-0 no-display form-evaluate">
                                <div class="container px-4 py-2">
                                    <p class="fs-9 grey-dark-color">Rất tích cực<input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                    <p class="fs-9 grey-dark-color">Tích cực <input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                    <p class="fs-9 grey-dark-color m-0">Không tích cực <input type="radio" name="radio" class="mt-1 py-2 evaluate-memb"></p>
                                </div>
                                <div class="container d-flex justify-content-center">
                                    <button class="btn bg-btn fs-10 fw-bold white-color w-50 m-2 btn-save" value="${member.maTK.id}">Lưu đánh giá</button>
<!--                                    <button class="btn bg-btn fs-11 fw-bold white-color p-1 px-3 m-2 btn-exit">Thoát</button>-->
                                </div>
                            </div>
                     </div>
                `;
            })
            for (var member of member_evaluate) {
                member.addEventListener("click", formEvaluation)
            }
            // Ham chuc nang danh gia thanh vien
            const btn_save = document.querySelectorAll(".btn-save");
            for(btn of btn_save){
                btn.addEventListener("click",saveEvaluation)
            }

            // const btn_exit = document.querySelectorAll(".btn-exit");
            // btn_exit.forEach(btn => {
            //     btn.addEventListener("click", function (e) {
            //         const memberEvaluateContainer = btn.closest(".member-evaluate");
            //         const formEvaluate = memberEvaluateContainer.querySelector(".form-evaluate");
            //         formEvaluate.classList.remove("display-block","important")
            //         console.log(formEvaluate)
            //     });
            // });
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


//Đánh giá thành viên
function formEvaluation(e) {
    const target = e.target;
    const container = target.closest(".member-evaluate");
    var allForms = document.querySelectorAll(".form-evaluate");
    for (var form of allForms) {
        form.classList.remove("display-block");
    }
    const childForm = container.querySelector(".form-evaluate");
    childForm.classList.add("display-block")
}
const member_evaluate = document.getElementsByClassName("member-evaluate")
for (var member of member_evaluate) {
    member.addEventListener("click", formEvaluation)
}

//Chưa được

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
for (var btn of btn_star) {
    btn.addEventListener("click", (e) => {
        for (var star of btn_star) {
            star.classList.remove("yellow-color")
        }
        score = (Array.from(btn_star).indexOf(e.target)+1)*2;
        e.target.classList.add("yellow-color");
        var previousSibling = e.target.previousElementSibling;
        while (previousSibling) {
            previousSibling.classList.add("yellow-color");
            previousSibling = previousSibling.previousElementSibling;
        }
    });
}
btn_send_cmt.addEventListener("click", async function (e) {
    var maTK = +member_infor.dataset.value;
    var diemTC = score;
    //Lay danh gia tieu chi
    var checkValue = [];
    for(check of check_survey){
        checkValue.push(check.checked);
    }
    var tieuChi1 = checkValue[0];
    var tieuChi2 = checkValue[1];
    var tieuChi3 = checkValue[2];
    //Lay binh luan
    var binhLuan = text_cmt.value;
    var currentDate = new Date();
    var thoiGianBL = currentDate.toISOString();
    const url = `/danh-gia/danh-gia-hoat-dong?maTK=${maTK}&maHD=${maHD}&diemTC=${diemTC}&tieuChi1=${tieuChi1}&tieuChi2=${tieuChi2}&tieuChi3=${tieuChi3}&binhLuan=${binhLuan}&thoiGianBL=${thoiGianBL}`;
    const response = await fetch(url, {
        method: 'POST'
    })
    if(!response.ok){
        console.log("HTTP lỗi. Trạng thái " + response.status)
    }
    else{
        text_cmt.value = "";
        for(star of btn_star){
            star.classList.remove("yellow-color")
        }
        for(check of check_survey){
            check.checked = false;
        }
        //Cap nhat lai modal binh luan
        const response =await fetch(`/hoat-dong/xem-chi-tiet/${maHD}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        else {
            const responseData = await response.json();
            document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
            const evaluationOfAct = responseData.evaluationOfAct;
            //    console.log(evaluationOfAct);
            const evaluationContainer = document.querySelector(".view-comment");
            evaluationContainer.innerHTML="";
            if (evaluationOfAct.length > 0) {
                const criteriaDiv = document.createElement("div");
                criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end", "p-2", "criteria-evaluation")
                criteriaDiv.innerHTML =
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
                <p class="fs-10 green_light-color fst-italic m-0 px-3">Điểm cho ban tổ chức: ${eval.diemTC}</p>
                <p class="fs-10 fst-italic m-0 px-3">${eval.binhLuan}</p>
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN", "dd/MM/yyyy").format(new Date(eval.thoiGianBL))}</p>
            </div>
            <!-- Check comment -->
            <div class="container d-flex align-items-center justify-content-center">
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi1}" ${eval.tieuChi1 ? 'checked' : ''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eval.tieuChi2}" ${eval.tieuChi2 ? 'checked' : ''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-3" value="${eval.tieuChi3}" ${eval.tieuChi3 ? 'checked' : ''}>
            </div>
        `
                    evaluationContainer.appendChild(newEvaluationDiv);
                });
            }
        }
    }
});

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
    $('.hosted-slider').slick({
        infinite: false,
        slidesToShow: 3,
        vertical: true,
        prevArrow: '<div class="slick-prev"><i class="bi bi-chevron-compact-up"></i></div>',
        nextArrow: '<div class="slick-next"><i class="bi bi-chevron-compact-down"></i></div>',
    })
});
