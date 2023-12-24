$(document).ready(function(){
    $('.act-slider').slick({
        dots:true,
        slidesToShow:1,
        arrows:false,
        vertical:false,
        speed:100
    })
});

$(document).ready(function(){
    $('.list_card').slick({
        autoplay:true,
        infinite:false,
        slidesToShow:3,
        vertical:true,
        prevArrow: '<div class="slick-prev"><i class= "bi bi-chevron-compact-up"></i></div>',
        nextArrow: '<div class="slick-next"><i class="bi bi-chevron-compact-down"></i></div>',
    })
});
const search_input = document.querySelector(".search-input")
const act_slider = document.querySelector(".act-slider")
search_input.addEventListener("keyup",  async e => {
    if(e.key==="Enter"){
        try {
            const nameAct = search_input.value.trim()
            const response = await fetch(`/search/summary-home?nameSummary=${nameAct}`)
            if (!response.ok) {
                console.error("Lỗi HTTP! Trạng thái " + response.status)
            }
            const responseData = await response.json();
            const summaryList = responseData.summaryList;
            //const activityListOccured = responseData.activityListOccured;
            console.log(summaryList);
            act_slider.innerHTML = "";
            if (summaryList.length > 0) {
                for (let i = 0; i < Math.ceil(summaryList.length / 4) + ((i % 4 === 0 ? 0 : 1)); i++) {
                    const containerItem = document.createElement("div");
                    containerItem.className = "container-item";
                    for (let j = 0; j < 4; j++) {
                        const index = 4 * i + j;
                        if (index < summaryList.length) {
                            const moTa = summaryList[index].loiKet.length > 300 ? summaryList[index].loiKet.substring(0, 300) + "..." : summaryList[index].loiKet
                            const dateStart = new Date(summaryList[index].maHD.thoiGianBD)
                            const formattedDateStart = `${dateStart.getDate()}/${dateStart.getMonth() + 1}/${dateStart.getFullYear()}`;
                            const dateEnd = new Date(summaryList[index].maHD.thoiGianKT)
                            const formattedDateEnd = `${dateEnd.getDate()}/${dateEnd.getMonth() + 1}/${dateEnd.getFullYear()}`;
                            const innerContainer = document.createElement("div");
                            innerContainer.className = "container d-flex align-items-center float-start p-0 pb-2 border_bottom-solid cursor-pointer";

                            innerContainer.innerHTML =
                                        `
                                  <img src="${summaryList[index].maHD.anh}" alt="" class="float-start w-15 h-80 act-image"/>
                                <div class="card border-0">
                                    <div class="card-body fs-7">
                                        <div class="card-title fs-6 fw-bolder green-color lh-1 max-line-1">${summaryList[index].maHD.tenhd}</div>
                                        <div class="card-text">${moTa}</div>
                                        <div class="card-text">
                                            <i class="bi bi-calendar2 m-1 green-color"></i><span> ${formattedDateStart} - ${formattedDateEnd}</span>
                                        </div>
                                        <br>
                                        <div class="card-text bg-btn height-2rem radius-lghalf mt-7 white-color fs-9 p-1 pleft-6 fs-8 mt-9 text_align-center "><a class="btn-summary" href="/View-Summary?id=${summaryList[index].id}">Xem tổng kết</a></div>
                                    </div>
                                </div>
                                    `;
                            containerItem.appendChild(innerContainer);
                        }
                    }
                    act_slider.appendChild(containerItem)
                }
                for (let view of view_link) {
                    view.addEventListener("click", handleViewDetail)
                }
                for (let btn of btn_close) {
                    btn.addEventListener("click", closeViewDetail);
                }
                $(document).ready(function () {
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
        catch
            (Error)
        {
            console.error(Error)
        }
    }
})


