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
const members_activity = document.querySelector(".members-activity")
const btn_send_cmt = document.querySelector(".btn-send-cmt");
const btn_star = document.querySelectorAll(".btn-star")
const text_cmt = document.querySelector(".text-comment")
const check_survey = document.querySelectorAll(".check-survey");
var score = 0;
var maHD;
var maHDTC;
var scoreMb;
var endActTime;
var maNTC;
const member_infor = document.querySelector(".member-infor");

//Danh gia hoat dong
async function Evaluation(e)  {
    try{
        const IdAct =+e.target.dataset.value;
        maHD = IdAct;
        const response =await fetch(`/hoat-dong/xem-chi-tiet?IdAct=${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        const accEval = responseData.evaluation;
        const activity = responseData.activity;
        endActTime = activity.thoiGianKT;
        modal.classList.add('display-flex')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')


       if(accEval===null){
           edit_content.classList.add('max_height-27rem')
           write_comment.classList.remove('no-display')
           const sendComment = document.querySelector(".btn-send-cmt");
       }
        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenhd;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        maNTC = responseData.registerInfor.maTK.id;
        document.querySelector(".avatar-host").src = responseData.registerInfor.maTK.anhDaiDien;
        const date = new Date(responseData.registerInfor.thoiGianDK);
        const datePart = date.toLocaleDateString("vi-VN", {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
        });

        const timePart = date.toLocaleTimeString("vi-VN", {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        });
        const registerTime = `${datePart} ${timePart}`;
        document.querySelector(".time-post-act").innerText = registerTime;
        image_activity.innerHTML = "";
        let count = 0;
        imagesList.forEach(image => {
            if(count === 3) return;
            image_activity.innerHTML += `<img src="${image.anh}" alt="anhHoatDong" class="height-10rem w-35 p-1">`;
            count++;
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
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(eval.thoiGianBL))}</p>
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
    }
}

// Ham luu va cap nhat lai thong tin danh gia
async function saveEvaluation(e) {
    let endTime = new Date(endActTime);
    endTime.setDate(endTime.getDate() + 7);
    endTime.setHours(0, 0, 0, 0)
    let now = new Date();
    now.setHours(0, 0, 0, 0)
    if(now > endTime) {
        function customAlert(message){
            Swal.fire({
                icon: "warning",
                confirmButtonText: "OK",
                text: message,
                customClass:{
                    popup: 'custom-alert-popup'
                }
            });
        }
        customAlert("Đã hết hạn thời gian đánh giá thành viên cho hoạt động này.");
        return;
    }
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
    // Them thong bao danh gia diem
    const noiDung = "Người tổ chức vừa đánh giá điểm hoạt động của bạn."
    const loaiTB = 0
    const ma = maHDTC;
    var noticeURL = `/them-thong-bao?maTK=${maTKDG}&noiDung=${noiDung}&loaiTB=${loaiTB}&ma=${ma}`
    var noticeRes = await fetch(noticeURL, {
        method: "POST"
    })
    if(!noticeRes.ok){
        console.log("Lỗi thêm thông báo đánh giá thành viên. Trạng thái "+noticeRes.status);
    }
    if (!response.ok) {
        console.log("Lỗi đánh giá thành viên! Trạng thái " + response.status)
    } else {
        //Cap nhat lai diem
        const response = await fetch(`/hoat-dong/xem-chi-tiet?IdAct=${maHDTC}`);
        if (!response.ok) {
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const membersList = responseData.membersList;
        const scores = responseData.scores;
        // members_activity.classList.add('min_height-35rem');
        members_activity.innerHTML = '';
        var i = 0;
        membersList.forEach(member => {
            members_activity.innerHTML += `
                     <div class="container p-2 col-6 radius-1_8 position-relative member-evaluate">
                            <div class="container d-flex">
                                <img class="w-2_5 h-2_5 ml-0_1 radius-1_8 p-1" src="${member.maTK.anhDaiDien}" alt="">
                                <h4 class="green-color fs-7 my-1 mt-1 p-2">${member.maTK.tenDN}</h4>
                                <h10 class="fs-7 p-2">${scores[i++]}<i class=" bi bi-star-fill yellow-color fs-7 p-1"></i></h10>
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
        if(membersList.length %2!==0){
            members_activity.innerHTML += `
            <div class="container p-2 col-6"></div>
        `
        }
        for (member of member_evaluate) {
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
        console.log(IdAct);
        const response =await fetch(`/hoat-dong/xem-chi-tiet?IdAct=${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        const membersList = responseData.membersList;
        const scores = responseData.scores;
        const activity = responseData.activity;
        endActTime = activity.thoiGianKT;
        modal.classList.add('display-flex')
        edit_content.classList.remove('max_height-27rem')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')

        view_organized.classList.remove('no-display')

        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenhd;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        document.querySelector(".avatar-host").src = responseData.registerInfor.maTK.anhDaiDien;
        const date = new Date(responseData.registerInfor.thoiGianDK);
        const datePart = date.toLocaleDateString("vi-VN", {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
        });

        const timePart = date.toLocaleTimeString("vi-VN", {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        });
        const registerTime = `${datePart} ${timePart}`;
        document.querySelector(".time-post-act").innerText= registerTime;
        //    image summary activity
           image_activity.innerHTML = "";
        let count = 0;
        imagesList.forEach(image => {
            if(count === 3) return;
            image_activity.innerHTML += `<img src="${image.anh}" alt="anhHoatDong" class="height-10rem w-35 p-1">`;
            count++;
        });
        document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
        const evaluationOfAct = responseData.evaluationOfAct;
        const evaluationContainer = document.querySelector(".view-comment");
            if(evaluationOfAct.length > 0){
                const criteriaDiv = document.createElement("div");
                criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end","p-2", "criteria-evaluation")
                criteriaDiv.innerHTML=
                    `<h4 class="fs-9 green-color fst-italic me-4 text-center">Hoạt động<br>hữu ích</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>thường xuyên</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>rộng rãi</h4>
                `
                evaluationContainer.appendChild(criteriaDiv)
            }
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
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(eval.thoiGianBL))}</p>
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

            if(membersList.length%2!==0){
                members_activity.innerHTML += `
            <div class="container p-2 col-6"></div>
        `
            }
            for (var member of member_evaluate) {
                member.addEventListener("click", formEvaluation)
            }
            // Ham chuc nang danh gia thanh vien
            const btn_save = document.querySelectorAll(".btn-save");
            for(btn of btn_save){
                btn.addEventListener("click",saveEvaluation)
            }
    }catch(Error){
        console.error(Error);
    }
}
for (btn of btn_evaluate_member) {
    btn.addEventListener("click",EvaluationMember)
}

// Close form evaluation
const btn_close = document.getElementsByClassName("btn-close-detail")
function closeFormEvaluation(e) {
    const modal_comment = document.querySelector('.view-comment');
    members_activity.innerHTML = '';
    btn_evaluate_mb.classList.remove("green-cb")
    modal_comment.innerHTML='';
    modal.classList.remove('display-flex')
    body.classList.remove('overflow-hidden')
    view_comment.classList.add('no-display')
    write_comment.classList.add('no-display')
    evaluation.classList.add('no-display')
    edit_content.classList.remove('max_height-27rem')
    view_organized.classList.add('no-display')
}
for (btn of btn_close) {
    btn.addEventListener("click",closeFormEvaluation);
}


//Đánh giá thành viên
function formEvaluation(e) {
    const target = e.target;
    const container = target.closest(".member-evaluate");
    var allForms = document.querySelectorAll(".form-evaluate");
    for (const form of allForms) {
        form.classList.remove("display-block");
    }
    const childForm = container.querySelector(".form-evaluate");
    childForm.classList.add("display-block")
}
const member_evaluate = document.getElementsByClassName("member-evaluate")
for (const member of member_evaluate) {
    member.addEventListener("click", formEvaluation)
}

//Chưa được

// save



//View evaluation
const btn_view_evaluation = document.querySelector(".btn-view-evaluation");
const btn_evaluate_mb = document.querySelector(".btn-evaluate-member");
btn_view_evaluation.addEventListener("click", function (e) {
    btn_view_evaluation.classList.add("green-cb")
    btn_evaluate_mb.classList.remove("green-cb")
    evaluation.classList.add("no-display")
    view_comment.classList.remove('no-display')
})
btn_evaluate_mb.addEventListener("click", function (e) {
    btn_view_evaluation.classList.remove("green-cb")
    btn_evaluate_mb.classList.add("green-cb")
    view_comment.classList.add('no-display')
    evaluation.classList.remove('no-display')
})

//Evaluate star
for (btn of btn_star) {
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
    //Sau 7 ngày không được đánh giá hoạt động nữa
    let endTime = new Date(endActTime);
    endTime.setDate(endTime.getDate() + 7);
    endTime.setHours(0, 0, 0, 0)
    let now = new Date();
    now.setHours(0, 0, 0, 0)
     if(now > endTime) {
        function customAlert(message){
            Swal.fire({
                icon: "warning",
                confirmButtonText: "OK",
                text: message,
                customClass:{
                    popup: 'custom-alert-popup'
                }
            });
        }
        customAlert("Đã hết hạn thời gian đánh giá hoạt động này.");
        return;
    }
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
    if(diemTC===0 || ((tieuChi1 || tieuChi2 || tieuChi3) === false) || binhLuan === "")
    {
        function customAlert(message) {
            Swal.fire({
                text: message,
                icon: 'info',
                confirmButtonText: 'OK'
            });
        }
        customAlert("Vui lòng đánh giá đầy đủ thông tin yêu cầu!");
        return;
    }
    const url = `/danh-gia/danh-gia-hoat-dong?maTK=${maTK}&maHD=${maHD}&diemTC=${diemTC}&tieuChi1=${tieuChi1}&tieuChi2=${tieuChi2}&tieuChi3=${tieuChi3}&binhLuan=${binhLuan}&thoiGianBL=${thoiGianBL}`;
    const response = await fetch(url, {
        method: 'POST'
    })

    // Them thong bao danh gia hoat dong
    const noiDung = "Một thành viên vừa đánh giá hoạt động của bạn."
    const loaiTB = 0
    const ma = maHD;
    var noticeURL = `/them-thong-bao?maTK=${maNTC}&noiDung=${noiDung}&loaiTB=${loaiTB}&ma=${ma}`
    var noticeRes = await fetch(noticeURL, {
        method: "POST"
    })

    if(!noticeRes.ok){
        console.log("Lỗi thêm thông báo đánh giá hoạt động. Trạng thái "+noticeRes.status);
    }

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
        const response =await fetch(`/hoat-dong/xem-chi-tiet?IdAct=${maHD}`);
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
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(eval.thoiGianBL))}</p>
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
    //Ẩn bình luận đánh giá đi
    write_comment.classList.add('no-display')
    evaluation.classList.add('no-display')
    edit_content.classList.remove('max_height-27rem')
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

$(document).ready(function () {
    var btnViewSummary = document.getElementsByClassName("view-summary");
    for(btn of btnViewSummary){
        btn.addEventListener("click", async (e) => {
            var maTongKet = e.target.dataset.hoatdongId;
            $.ajax({
                type: 'GET',
                url: '/Check-Summary',
                data: { 'maHD': maTongKet},

                success: function (data) {
                    if (data.summaryExists) {
                        window.location.href = '/View-Summary?id=' +maTongKet;
                    } else {
                        $('#noSummaryModal').modal('show');
                    }
                },

                error: function (error) {
                    console.error('Error:', error);
                }
            });
        })
    }
    $('.closeModalLogout').on('click', function () {
        $('#noSummaryModal').modal('hide');
        $('#noSummaryModal').modal('hide');
    });
})

