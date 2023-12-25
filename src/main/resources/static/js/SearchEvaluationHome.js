
//Content-search
const search_input = document.querySelector(".search-input")
const act_slider = document.querySelector(".act-slider")

search_input.addEventListener("keyup",  async e => {
    // search_infor.classList.add('display-block');
 if(e.key==="Enter"){
     try {
         const nameAct = search_input.value.trim()
         // const valueURL = convertToSlug(value);
         // console.log(valueURL)
         const response = await fetch(`/search/evaluation-home?nameAct=${nameAct}`)
         if (!response.ok) {
             console.error("Lỗi HTTP! Trạng thái " + response.status)
         }
         const responseData = await response.json()
         const activityList = responseData.activityList;
         const countEvaList = responseData.countEvaList;
         act_slider.innerHTML = "";
         if (activityList.length > 0) {
             for (let i = 0; i < Math.ceil(activityList.length / 4) + ((i % 4 === 0 ? 0 : 1)); i++) {
                 const containerItem = document.createElement("div");
                 containerItem.className = "container-item";
                 for (let j = 0; j < 4; j++) {
                     const index = 4 * i + j;
                     if (index < activityList.length) {
                         const moTa = activityList[index].moTa.length > 150 ? activityList[index].moTa.substring(0, 150) + "..." : activityList[index].moTa
                         const dateStart = new Intl.DateTimeFormat("vi-VN", { day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(activityList[index].thoiGianBD))
                         const dateEnd = new Intl.DateTimeFormat("vi-VN", { day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(activityList[index].thoiGianKT))
                         const innerContainer = document.createElement("div");
                         innerContainer.className = "container d-flex align-items-center float-start p-0 pb-2 border_bottom-solid";
                         innerContainer.innerHTML =
                             `
                    <img src="${activityList[index].anh}" class="float-start col-2 h-80" alt="" />
                    <div class="card border-0 col-10">
                        <div class="card-body fs-7">
                            <div class="card-title fs-6 fw-bolder green-color">${activityList[index].tenhd}</div>
                            <div class="card-text my-0">${moTa}</div>
                            <div class="card-text mx-2 my-1">
                                <i class="bi bi-calendar2 m-1 green-color">  ${dateStart} - ${dateEnd}</i>
                            </div>
                            <div class="card-link p-0">
                                <button
                                        class="btn text-decoration-none green_light-color fw-medium fst-italic float-sm-end me-md-3 mb-md-1 no-border fs-7 view-detail" data-value = "${activityList[index].id}">Xem
                                    chi tiết...</button>
                            </div>
                            <div
                                    class="card-text bg-btn height-2rem radius-lghalf mt-7 white-color fs-9 p-1 pleft-6 fs-8 mt-9 text_align-center"><p class="ml-20p">${countEvaList[index]} lượt đánh giá</p>
                               </div>
                        </div>
                    </div>
                   </div>
                          `
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

$(document).ready(function () {
    $('.act-slider').slick({
        dots: true,
        slidesToShow: 1,
        arrows: false,
        vertical: false,
        speed: 100
    });
});




