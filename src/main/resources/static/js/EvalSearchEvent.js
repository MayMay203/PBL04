//Hoat dong cua tai khoan dang nhap
//Content-search
const search_input = document.querySelector(".search-input")
const surround_div = document.querySelector(".surround-left")
//Du lieu tim kiem duoc
function searchData(actList){
    surround_div.innerHTML = "";
    const act_slider = document.createElement("div");
    act_slider.className = "act-slider"
    const sizeList = actListOfMember.length;
    if (sizeList > 0) {
        for (let i = 0; i < Math.floor(actListOfMember.length / 4) + ((sizeList % 4 === 0 ? 0 : 1)); i++) {
            const containerItem = document.createElement("div");
            containerItem.className = "container-item";
            for (let j = 0; j < 4; j++) {
                const index = 4 * i + j;
                if (index < actListOfMember.length) {
                    const moTa = actListOfMember[index].moTa.length > 150 ? actListOfMember[index].moTa.substring(0, 150) + "..." : actListOfMember[index].moTa
                    const dateStart = new Intl.DateTimeFormat("vi-VN", { day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(actListOfMember[index].thoiGianBD))
                    const dateEnd = new Intl.DateTimeFormat("vi-VN", { day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(actListOfMember[index].thoiGianKT))
                    const innerContainer = document.createElement("div");
                    innerContainer.className = "container d-flex align-items-center float-start p-0 pb-2 border_bottom-solid";
                    innerContainer.innerHTML =
                        `
                    <img src="${actListOfMember[index].anh}" class="float-start col-2 h-80" alt="" />
                    <div class="card border-0 col-10">
                        <div class="card-body fs-7">
                            <div class="card-title fs-6 fw-bolder green-color">${actListOfMember[index].tenhd}</div>
                            <div class="card-text my-0">${moTa}</div>
                            <div class="card-text mx-2 my-1">
                                <i class="bi bi-calendar2 m-1 green-color">  ${dateStart} - ${dateEnd}</i>
                            </div>
                            <div class="card-link">
                                <a
                                   class="btn text-decoration-none green_light-color fw-medium fst-italic float-sm-end me-md-3 mb-md-2 no-border fs-7 view-summary" href="/View-Summary?id=${actListOfMember[index].id}">
                                    Xem tổng kết...</a>
                            </div>
                           <button class="card-text bg-btn height-2rem radius-lghalf white-color fs-9 p-1 pleft-6 fs-8 btn-evaluate w-100 no-border" data-value="${actListOfMember[index].id}">
                                 Đánh giá
                            </button>
                        </div>
                    </div>
                   </div>
                          `
                    containerItem.appendChild(innerContainer);
                }
            }
            act_slider.appendChild(containerItem)
        }
        surround_div.appendChild(act_slider);
        //Tao slickslider
        if(act_slider.children.length > 0){
            $(document).ready(function(){
                $('.act-slider').slick({
                    dots: true,
                    slidesToShow: 1,
                    arrows: false,
                    vertical: false,
                    speed: 100
                });
            });
        }
}
search_input.addEventListener("keyup",  async e => {
        if (e.key === "Enter") {
            try{
                const nameAct = search_input.value.trim()
                const IdAcc = search_input.dataset.account;
                const currentLocation = document.location.href;
                let checkLocation = false;
                console.log(location);
                if(currentLocation.includes("/trang-chu-danh-gia")){
                    //hoat dong da dien ra
                    const response = await fetch(`/search/evaluation-home?nameAct=${nameAct}`)
                    if (!response.ok) {
                        console.error("Lỗi HTTP! Trạng thái " + response.status)
                    }
                    const responseData = await response.json()
                    const activityList = responseData.activityList;
                    const countEvaList = responseData.countEvaList;

                    for (let view of view_link) {
                        view.addEventListener("click", handleViewDetail)
                    }
                    for (let btn of btn_close) {
                        btn.addEventListener("click", closeViewDetail);
                    }
                }
                else{
                    checkLocation = true;
                    //hoat dong cua tai khoan do
                    const response = await fetch(`/search/evaluation?nameAct=${nameAct}&IdAcc=${IdAcc}`)
                    if (!response.ok) {
                        console.error("Lỗi HTTP! Trạng thái " + response.status)
                    }
                    const actListOfMember = await response.json()
                    searchData(actListOfMember);
                    for (btn of btn_evaluate) {
                        btn.addEventListener("click", Evaluation)
                    }
                    for (btn of btn_evaluate_member) {
                        btn.addEventListener("click", EvaluationMember)
                    }
                    for (btn of btn_close) {
                        btn.addEventListener("click", closeFormEvaluation);
                    }
                }
                } catch (Error) {
                    console.error(Error)
                }
        }
    }
    )}







