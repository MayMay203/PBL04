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
const surround_div = document.querySelector(".surround-left")

document.addEventListener("DOMContentLoaded", () => {

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
        console.log(activityListNotHappening);
        surround_div.innerHTML = "";
        const act_slider = document.createElement("div");
        act_slider.className = "act-slider"
        if (activityListNotHappening.length > 0) {
          for (let i = 0; i < Math.floor(activityListNotHappening.length / 4) + ((activityListNotHappening.length % 4 === 0 ? 0 : 1)); i++) {
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

        }
        surround_div.appendChild(act_slider)
        if(act_slider.children.length > 0){
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
      } catch
          (Error) {
        console.error(Error)
      }
    }
  })
})
$(document).ready(function() {
  $('#btnFilter').on('click', function() {
    $('#modalFilter').modal('show');
  });
});
$(document).ready(function() {
  $('#btn-closeFilter').on('click', function() {
    $('#modalFilter').modal('hide');
  });
});

$(document).ready(function() {
  $('#btnSearch').on('click', function() {
    var maChuDe = $('.optState option:selected').data('value');
    console.log("machude:",maChuDe);
    $.ajax({
      type: 'POST',
      url: '/get-activity-by-topic',
      data: {  'maCD': maChuDe },
      success: function (data) {
        console.log('Success:', data);
        $('#modalFilter').modal('hide');
           surround_div.innerHTML = "";

        const allSlidersContainer = document.createElement("div");
        allSlidersContainer.className = "all-sliders-container";

        data.forEach(function(activity, index) {
          // Nếu là bài viết đầu tiên hoặc là bài viết thứ 5, tạo một slider mới
          if (index % 4 === 0) {
            // Tạo một slider mới
            const act_slider = document.createElement("div");
            act_slider.className = "act-slider";

            // Thêm slider vào div chứa tất cả slider
            allSlidersContainer.appendChild(act_slider);
          }

          const containerItem = document.createElement("div");
          containerItem.className = "container-item";

          // Tạo các phần tử HTML cho thông tin hoạt động
          const moTa = activity.moTa.length > 170 ? activity.moTa.substring(0, 170) + "..." : activity.moTa;
          const dateStart = new Date(activity.thoiGianBD);
          const formattedDateStart = `${dateStart.getDate()}/${dateStart.getMonth() + 1}/${dateStart.getFullYear()}`;
          const dateEnd = new Date(activity.thoiGianKT);
          const formattedDateEnd = `${dateEnd.getDate()}/${dateEnd.getMonth() + 1}/${dateEnd.getFullYear()}`;

          const innerContainer = document.createElement("div");
          innerContainer.className = "card mb-5";

          innerContainer.innerHTML =
              `
                <div class="row g-0">
                    <div class="col-md-3">
                        <img src="${activity.anh}" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-9">
                        <div class="card-body">
                            <h5 class="card-title"><a class="card-title-a" href="">${activity.tenhd}</a></h5>
                            <p class="card-text"><a class="card-text-a" href="">${moTa}</a></p>
                            <p class="card-text"><small class="text-body-secondary">
                                <span class="bi bi-calendar-date"></span><span> ${formattedDateStart} - ${formattedDateEnd}</span>
                                <button type="submit" class="btn btn-success btn-join-acti">
                                    <a class="btn-Join-detail view-detail" href="/Join?id=${activity.id}">Tham gia</a>
                                </button>
                            </small></p>
                        </div>
                    </div>
                </div>
                `;
          containerItem.appendChild(innerContainer);

          // Lấy slider cuối cùng (do đã thêm mới nếu cần) và thêm containerItem vào đó
          const lastSlider = allSlidersContainer.lastChild;
          lastSlider.appendChild(containerItem);
        });

        // Thêm div chứa tất cả slider vào surround_div
        surround_div.appendChild(allSlidersContainer);

        // Kích hoạt Slick cho tất cả slider
        if (allSlidersContainer.children.length > 0) {
          $(document).ready(function() {
            $('.all-sliders-container').slick({
              dots: true,
              slidesToShow: 1,
              arrows: false,
              vertical: false,
              speed: 100
            });
          });
        }

        console.log("thanh cong");
      },
      error: function(error) {
        console.error('Error:', error);
      }
    });
  });
  $('#btnResetFilter').on('click', function() {
    location.reload();
  })
})
