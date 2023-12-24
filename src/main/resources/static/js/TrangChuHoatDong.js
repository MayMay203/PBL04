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



document.addEventListener("DOMContentLoaded", () => {
  const search_input = document.querySelector(".search-input")
  const act_slider = document.querySelector(".act-slider")
  search_input.addEventListener("keyup", async e => {
    if (e.key === "Enter") {
      try {
        const nameAct = search_input.value.trim()
        const response = await fetch(`/search/activity-home?nameAct=${nameAct}`)
        if (!response.ok) {
          console.error("Lỗi HTTP! Trạng thái " + response.status)
        }
        const responseData = await response.json();
        const activityListNotHappening = responseData.activityListNotHappening;
        //const activityListOccured = responseData.activityListOccured;
        console.log(activityListNotHappening);
        act_slider.innerHTML = "";
        if (activityListNotHappening.length > 0) {
          for (let i = 0; i < Math.ceil(activityListNotHappening.length / 4) + ((i % 4 === 0 ? 0 : 1)); i++) {
            const containerItem = document.createElement("div");
            containerItem.className = "container-item";
            for (let j = 0; j < 4; j++) {
              const index = 4 * i + j;
              if (index < activityListNotHappening.length) {
                const moTa = activityListNotHappening[index].moTa.length > 170 ? activityListNotHappening[index].moTa.substring(0, 170) + "..." : activityListNotHappening[index].moTa
                const dateStart = new Date(activityListNotHappening[index].thoiGianBD)
                const formattedDateStart = `${dateStart.getDate()}/${dateStart.getMonth() + 1}/${dateStart.getFullYear()}`;
                const dateEnd = new Date(activityListNotHappening[index].thoiGianKT)
                const formattedDateEnd = `${dateEnd.getDate()}/${dateEnd.getMonth() + 1}/${dateEnd.getFullYear()}`;
                const innerContainer = document.createElement("div");
                innerContainer.className = "card mb-5";

                innerContainer.innerHTML =
                    `
          <div class="row g-0">
            <div class="col-md-3">
              <img src="${activityListNotHappening[index].anh}" class="img-fluid rounded-start" alt="...">
            </div>
            <div class="col-md-9">
              <div class="card-body">
                <h5 class="card-title"><a class="card-title-a" href="">${activityListNotHappening[index].tenhd}</a></h5>
                <p class="card-text"><a class="card-text-a" href="">${moTa}</a></p>
                <p class="card-text"><small class="text-body-secondary">
                  <span class="bi bi-calendar-date"></span><span> ${formattedDateStart} - ${formattedDateEnd}</span>
                  <button type="submit" class="btn btn-success btn-join-acti">
                    <a class="btn-Join-detail view-detail" href="/Join?id=${activityListNotHappening[index].id}">Tham gia</a>
                  </button>
                </small></p>
              </div>
            </div>
          </div>
          `;
                containerItem.appendChild(innerContainer);
              }
            }
            act_slider.appendChild(containerItem)
          }
          act_slider.slick({
            dots: true,
            slidesToShow: 1,
            arrows: false,
            vertical: false,
            speed: 100
          });
          for (let view of view_link) {
            view.addEventListener("click", handleViewDetail)
          }
          for (let btn of btn_close) {
            btn.addEventListener("click", closeViewDetail);
          }
          // const actSlider = document.getElementsByClassName(".act-slider");
          // actSlider.slick({
          //   dots: true,
          //   slidesToShow: 1,
          //   arrows: false,
          //   vertical: false,
          //   speed: 100
          // });
        }
      } catch
          (Error) {
        console.error(Error)
      }
    }
  })
})


