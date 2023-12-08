
//Content-search
const search_input = document.querySelector(".search-input")
const show_actitivy = document.querySelector(".show-activity")
const btn_close = document.getElementsByClassName("btn-close-detail")

console.log(show_actitivy)
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
    try {
        const value = search_input.value.trim()
        const title = convertToSlug(value);
        console.log(title);
        const response = await fetch(`/search/suggestion/${title}`)
        if (!response.ok) {
            console.error("Lỗi HTTP! Trạng thái " + response.status)
        }
        const responseData = await response.json()
        console.log(responseData)
        const suggestionList = responseData.suggestionList;
        const countSugg = responseData.countSugg;
        // const actListOfMember = responseData.actListOfMember;
        // console.log(actListOfMember);
        // const countEvaList = responseData.countEvaList;
        show_actitivy.innerHTML = "";
        if (suggestionList.length > 0) {
               suggestionList.forEach((sugg, index) => {
                   const containerItem = document.createElement("div");
                   containerItem.className = "fs-9 display-flex act-title";
                   const moTa = sugg.moTa.length > 150 ? sugg.moTa.substring(0, 150) + "..." : sugg.moTa
                   const activity = document.createElement("div");
                   activity.className = "container px-5 activity";
                   activity.innerHTML =
                       `
                        <div class="container d-flex align-items-center p-3 border_bottom-solid">
                        <div class="container flex-5">
                            <h3 class="green-color fs-4">${sugg.maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6">${moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green px-1"></i>${sugg.viTri}</p>
                        </div>
                        </div>
                        `
                        const iconAct = document.createElement("div")
                        iconAct.className = "container d-flex flex-1 justify-content-end align-items-center icon-act"
                        iconAct.innerHTML =
                       `
                            <button  class="no-border bg-white p-0 mx-4 cursor-pointer">
                            <i class="bi bi-calendar4-week position-relative mb-1 h-1 green-color calendar cursor-pointer fs-5 btn-suggestion-detail" data-value = "${sugg.maChuDe.id}">
                             <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-calendar no-display number-sugg-name-title"> ${countSugg[index] + ' lượt đề xuất cùng chủ đề'}
                            </p>
                            </i>
                            </button>
                            <button data-value="${sugg.maChuDe.tenChuDe}" class="no-border bg-white p-0 btn-suggestion-detail mx-3 cursor-pointer">
                                <i class="bi bi-broadcast-pin position-relative mb-1 h-1 green-color broadcast cursor-pointer fs-5">
                                    <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-broadcast no-display">
                                        Tổ chức tại đây</p>
                                </i>
                            </button>
                            `
                   containerItem.appendChild(activity)
                   containerItem.appendChild(iconAct)
                   show_actitivy.appendChild(containerItem)
               })
            const btn_viewDetail = document.querySelectorAll(".btn-suggestion-detail");
            for(let btnDel of btn_viewDetail){
                btnDel.addEventListener("click",viewDetailSuggestion)
            }
            for (let btn of btn_close_detail) {
                btn.addEventListener("click",closeViewDetailSugg);
            }
        }
    }
    catch
        (Error)
    {
        console.error(Error)
    }
})




