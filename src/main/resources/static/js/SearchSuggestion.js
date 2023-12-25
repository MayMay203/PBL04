
//Content-search
const search_input = document.querySelector(".search-input")
const btn_close = document.getElementsByClassName("btn-close-detail")
const surround_div = document.querySelector(".surround")
search_input.addEventListener("keyup",  async e => {
    if (e.key === "Enter") {
        try {
            const title = search_input.value.trim()
            const response = await fetch(`/search/suggestion?nameTitle=${title}`)
            if (!response.ok) {
                console.error("Lỗi HTTP! Trạng thái " + response.status)
            }
            const responseData = await response.json();
            const suggestionList = responseData.suggestionList;
            const countAct = responseData.countAct;
            const account = responseData.account;

            surround_div.innerHTML = "";
            const sugg_slider = document.createElement("div");
            sugg_slider.className = "sugg-slider"
            const sizeList = suggestionList.length;
            if (sizeList > 0) {
                for (let i = 0; i < Math.floor(sizeList / 5) + ((sizeList % 5 === 0 ? 0 : 1)); i++) {
                    const containerItem = document.createElement("div");
                    containerItem.className = "show-suggestion";
                    for (let j = 0; j < 5; j++) {
                        const index = 5 * i + j;
                        if (index < suggestionList.length) {
                            const container = document.createElement("div");
                            container.className = "fs-9 display-flex act-title";
                            //const moTa = sugg.moTa.length > 150 ? sugg.moTa.substring(0, 150) + "..." : sugg.moTa
                            const activityDiv = document.createElement("div");
                            activityDiv.className = "container px-5 flex-5";
                            const time = new Intl.DateTimeFormat("vi-VN", {
                                day: '2-digit',
                                month: '2-digit',
                                year: 'numeric'
                            }).format(new Date(suggestionList[index].thoiGianDeXuat))
                            activityDiv.innerHTML =
                                `
                        <div class="container d-flex align-items-center p-3 border_bottom-solid">
                        <div class="container flex-5">
                            <h3 class="green-color fs-4">${suggestionList[index].maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6">${suggestionList[index].moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green"></i>${suggestionList[index].viTri}</p>
                              <p class="fs-7 fst-italic my-1">
                            <i class="bi bi-alarm-fill green"> Thời gian đề xuất: ${time}</i>
                            </p>
                        </div>
                        </div>
                        `
                            const iconAct = document.createElement("div")
                            iconAct.className = "container p-0 d-flex flex-1 align-items-center icon-act"
                            iconAct.innerHTML =
                                `
                      <button  class="no-border bg-white p-0 mx-4 cursor-pointer btn-activity-detail col-4">
                        <i data-location="${suggestionList[index].viTri}" data-countAct="${countAct[index]}" class="bi bi-calendar4-week position-relative mb-1 h-1 green-color calendar cursor-pointer fs-5 ">
                        <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-calendar no-display number-sugg-name-title"
                        >${countAct[index]} hoạt động đã tổ chức tại đây</p>
                        </i>
                      </button>

                      <button class="no-border bg-white p-0 cursor-pointer btn-organize-here col-5">
                        <i data-name="${suggestionList[index].maChuDe.tenChuDe}" data-location="${suggestionList[index].viTri}" data-account="${account != null ? account.id : null}" class="bi bi-broadcast-pin position-relative mb-1 h-1 green-color broadcast cursor-pointer fs-5">
                        <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-broadcast no-display">
                          Tổ chức tại đây</p>
                        </i>
                      </button>
                            `
                            container.appendChild(activityDiv);
                            container.appendChild(iconAct);
                            containerItem.appendChild(container);
                        }
                    }
                    sugg_slider.appendChild(containerItem)
                }
                surround_div.appendChild(sugg_slider);
                const btn_viewDetail = document.querySelectorAll(".btn-activity-detail");
                for (btn of btn_viewDetail) {
                    btn.addEventListener("click", viewActByLocation)
                }
                for (btn of btn_close_detail) {
                    btn.addEventListener("click", closeViewAct);
                }
                const btn_organizeHere = document.querySelectorAll(".btn-organize-here");
                for (btn of btn_organizeHere) {
                    btn.addEventListener("click", organizeHere)
                }
            }

            if (sugg_slider.children.length > 0) {
                console.log(sugg_slider)
                $(".sugg-slider").slick({
                    dots: true,
                    slidesToShow: 1,
                    arrows: false,
                    vertical: false,
                    speed: 20
                });
            }
        } catch
            (Error) {
            console.error(Error)
        }
    }
})










