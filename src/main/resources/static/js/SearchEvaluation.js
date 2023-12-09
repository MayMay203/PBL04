
//Content-search
const search_input = document.querySelector(".search-input")
const act_slider = document.querySelector(".act-slider")
// const body = document.querySelector("body");
// const modal = document.querySelector(".modal_")
//const btn_close = document.getElementsByClassName("btn-close-detail")
function convertToSlug(str) {
    return str
        .toLowerCase()
        .normalize('NFKD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/\s+/g, '-');
}

search_input.addEventListener("keydown",  async e => {
    // search_infor.classList.add('display-block');
    try {
        const value = search_input.value.trim()
        const valueURL = convertToSlug(value);
        // console.log(valueURL)
        const response = await fetch(`/search/evaluation/${valueURL}`)
        if (!response.ok) {
            console.error("Lỗi HTTP! Trạng thái " + response.status)
        }
        const actListOfMember = await response.json()
        // const actListOfMember = responseData.actListOfMember;
        // console.log(actListOfMember);
        // const countEvaList = responseData.countEvaList;
        act_slider.innerHTML = "";
        if (actListOfMember.length > 0) {
            for (let i = 0; i < Math.ceil(actListOfMember.length / 4) + ((i % 4 === 0 ? 0 : 1)); i++) {
                const containerItem = document.createElement("div");
                containerItem.className = "container-item";
                for (let j = 0; j < 4; j++) {
                    const index = 4 * i + j;
                    if (index < actListOfMember.length) {
                        const moTa = actListOfMember[index].moTa.length > 150 ? actListOfMember[index].moTa.substring(0, 150) + "..." : actListOfMember[index].moTa
                        const dateStart = new Date(actListOfMember[index].thoiGianBD)
                        const formattedDateStart = `${dateStart.getDate()}/${dateStart.getMonth() + 1}/${dateStart.getFullYear()}`;
                        const dateEnd = new Date(actListOfMember[index].thoiGianKT)
                        const formattedDateEnd = `${dateEnd.getDate()}/${dateEnd.getMonth() + 1}/${dateEnd.getFullYear()}`;
                        const innerContainer = document.createElement("div");
                        innerContainer.className = "container d-flex align-items-center float-start p-0 pb-2 border_bottom-solid";
                        innerContainer.innerHTML =
                            `
                    <img src="${actListOfMember[index].anh}" class="float-start w-10 h-80" alt="" />
                    <div class="card border-0">
                        <div class="card-body fs-7">
                            <div class="card-title fs-6 fw-bolder green-color">${actListOfMember[index].tenHD}</div>
                            <div class="card-text my-0">${moTa}</div>
                            <div class="card-text mx-2 my-1">
                                <i class="bi bi-calendar2 m-1 green-color">  ${formattedDateStart} - ${formattedDateEnd}</i>
                            </div>
                            <div class="card-link">
                                <a
                                   class="btn text-decoration-none green_light-color fw-medium fst-italic float-sm-end me-md-3 mb-md-4 no-border fs-7 view-summary" href="/View-Summary/id=7">
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
            for (var btn of btn_evaluate) {
                btn.addEventListener("click", Evaluation)
            }
            for (var btn of btn_evaluate_member) {
                btn.addEventListener("click",EvaluationMember)
            }

            for (var btn of btn_close) {
                btn.addEventListener("click",closeFormEvaluation);
            }
        }
    }
    catch
        (Error)
    {
        console.error(Error)
    }
})

$(document).ready(function () {
    $('.act-slider').slick({
        dots: true,
        slidesToShow: 1,
        arrows: false,
        vertical: false,
        speed: 100
    });
});




