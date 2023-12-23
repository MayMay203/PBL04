
//Content-search
const search_input = document.querySelector(".search-input")
const show_actitivy = document.querySelector(".show-activity")
const btn_close = document.getElementsByClassName("btn-close-detail")

// console.log(show_actitivy)
// const body = document.querySelector("body");
// const modal = document.querySelector(".modal_")
//const btn_close = document.getElementsByClassName("btn-close-detail")
// function convertToSlug(str) {
//     return str
//         .toLowerCase()
//         .normalize('NFKD')
//         .replace(/[\u0300-\u036f]/g, '')
//         .replace(/\s+/g, '-');
// }

search_input.addEventListener("keyup",  async e => {
  if(e.key==="Enter"){
      try {
          const title = search_input.value.trim()
          // const title = convertToSlug(value);
          // console.log(title);
          const response = await fetch(`/search/suggestion?nameTitle=${title}`)
          if (!response.ok) {
              console.error("Lỗi HTTP! Trạng thái " + response.status)
          }
          const responseData = await response.json()
          // console.log(responseData)
          const suggestionList = responseData.suggestionList;
          const countAct = responseData.countAct;
          const account = responseData.account;
          // const actListOfMember = responseData.actListOfMember;
          // console.log(actListOfMember);
          // const countEvaList = responseData.countEvaList;
          show_actitivy.innerHTML = "";
          if (suggestionList.length > 0) {
              suggestionList.forEach((sugg, index) => {
                  const containerItem = document.createElement("div");
                  containerItem.className = "fs-9 display-flex act-title";
                  //const moTa = sugg.moTa.length > 150 ? sugg.moTa.substring(0, 150) + "..." : sugg.moTa
                  const activity = document.createElement("div");
                  activity.className = "container px-5 activity";
                  const time = new Intl.DateTimeFormat("vi-VN","dd/MM/yyyy").format(new Date(sugg.thoiGianDeXuat))
                  activity.innerHTML =
                      `
                        <div class="container d-flex align-items-center p-3 border_bottom-solid">
                        <div class="container flex-5">
                            <h3 class="green-color fs-4">${sugg.maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6">${sugg.moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green"></i>${sugg.viTri}</p>
                              <p class="fs-7 fst-italic my-1">
                            <i class="bi bi-alarm-fill green"> Thời gian đề xuất: ${time}</i>
                            </p>
                        </div>
                        </div>
                        `
                  const iconAct = document.createElement("div")
                  iconAct.className = "container d-flex flex-1 justify-content-end align-items-center icon-act"
                  iconAct.innerHTML =
                      `
                      <button  class="no-border bg-white p-0 mx-4 cursor-pointer btn-activity-detail">
                        <i data-location="${sugg.viTri}" data-countAct="${countAct[index.index]}" class="bi bi-calendar4-week position-relative mb-1 h-1 green-color calendar cursor-pointer fs-5 ">
                        <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-calendar no-display number-sugg-name-title"
                        >${countAct[index]} hoạt động đã tổ chức tại đây</p>
                        </i>
                      </button>

                      <button class="no-border bg-white p-0 mx-3 cursor-pointer btn-organize-here">
                        <i data-name="${sugg.maChuDe.tenChuDe}" data-location="${sugg.viTri}" data-account="${account!=null?account.id:null}" class="bi bi-broadcast-pin position-relative mb-1 h-1 green-color broadcast cursor-pointer fs-5">
                        <p class="bg-green position-absolute b-100  grey_dark-color fw-medium p-0 m-0 w-10rem text-center radius-1 fs-7 notice-broadcast no-display">
                          Tổ chức tại đây</p>
                        </i>
                      </button>
                            `
                  containerItem.appendChild(activity)
                  containerItem.appendChild(iconAct)
                  show_actitivy.appendChild(containerItem)
              })
              const btn_viewDetail = document.querySelectorAll(".btn-activity-detail");
              console.log(btn_viewDetail)
              for(btn of btn_viewDetail){
                  btn.addEventListener("click",viewActByLocation)
              }
              for (btn of btn_close_detail) {
                  btn.addEventListener("click",closeViewAct);
              }
              const btn_organizeHere = document.querySelectorAll(".btn-organize-here");
              function organizeHere(e){
                  try{
                      const acc = e.target.getAttribute("data-account")
                      console.log(acc);
                      if(acc===null){
                          var header = document.getElementById("myHeader");
                          header.classList.remove("sticky");
                          $('#DangNhapModal').modal('show');
                      }
                      else{
                          const title = e.target.dataset.name;
                          const location = e.target.dataset.location;
                          $('#ThemHoatDongModal').modal('show');
                          $('#txt_Location').val(location);
                          $('#txt_Location').prop('disabled',true);
                          $('#txt_NameTopic').val(title);
                          $('#txt_NameTopic').prop('disabled',true);
                      }
                  }catch(error){
                      console.log(error);
                  }
              }
              for(btn of btn_organizeHere){
                  btn.addEventListener("click",organizeHere)
              }
          }
      }
      catch
          (Error)
      {
          console.error(Error)
      }
  }
})




