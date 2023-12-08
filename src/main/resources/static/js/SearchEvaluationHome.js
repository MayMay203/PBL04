
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
      console.log(valueURL)
      const response = await fetch(`/search/evalatuation-home/${valueURL}`)
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
                      const dateStart = new Date(activityList[index].thoiGianBD)
                      const formattedDateStart = `${dateStart.getDate()}/${dateStart.getMonth() + 1}/${dateStart.getFullYear()}`;
                      const dateEnd = new Date(activityList[index].thoiGianKT)
                      const formattedDateEnd = `${dateEnd.getDate()}/${dateEnd.getMonth() + 1}/${dateEnd.getFullYear()}`;
                      const innerContainer = document.createElement("div");
                      innerContainer.className = "container d-flex align-items-center float-start p-0 pb-2 border_bottom-solid";
                      innerContainer.innerHTML =
                          `
                    <img src="" class="float-start w-10 h-80" alt="" />
                    <div class="card border-0">
                        <div class="card-body fs-7">
                            <div class="card-title fs-6 fw-bolder green-color">${activityList[index].tenHD}</div>
                            <div class="card-text my-0">${moTa}</div>
                            <div class="card-text mx-2 my-1">
                                <i class="bi bi-calendar2 m-1 green-color">  ${formattedDateStart} - ${formattedDateEnd}</i>
                            </div>
                            <div class="card-link p-0">
                                <button
                                        class="btn text-decoration-none green_light-color fw-medium fst-italic float-sm-end me-md-3 mb-md-1 no-border fs-7 view-detail" data-value = "${activityList[index].id}">Xem
                                    chi tiết...</button>
                            </div>
                            <div
                                    class="card-text bg-btn height-2rem radius-lghalf mt-7 white-color fs-9 p-1 pleft-6 fs-8 mt-9 text_align-center"><p class="ml-7_5rem">${countEvaList[index]} lượt đánh giá</p>
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




