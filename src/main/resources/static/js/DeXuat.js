// Suggestions
const btn_close_sug = document.querySelector(".btn-close-suggest");
const btn_add_sug = document.querySelector(".btn-add-suggest");
const body = document.querySelector("body");
const modal = document.querySelector(".modal_")
const actDetail = document.querySelector(".act-detail");
const suggesstionForm = document.querySelector(".suggestion-form");
const actByLocation = document.querySelector(".act-by-location");
const btn_actDetail = document.querySelectorAll(".btn-activity-detail");
const btn_organizeHere = document.querySelectorAll(".btn-organize-here");
const btn_close_detail = document.getElementsByClassName("btn-close-detail")
btn_close_sug.addEventListener("click", () => {
    modal.classList.remove("display-flex")
   // body.classList.remove("overflow-hidden")
    body.classList.remove("disable-scrollbar")
    suggesstionForm.classList.add("no-display");
})

function closeViewAct() {
    actByLocation.innerHTML = "";
    modal.classList.remove("display-flex")
   // body.classList.remove("overflow-hidden")
    body.classList.remove("disable-scrollbar")
    actDetail.classList.add("no-display");
}
for (btn of btn_close_detail) {
    btn.addEventListener("click",closeViewAct);
}

btn_add_sug.addEventListener("click", () => {
    if(btn_add_sug.dataset.value==null){
        var header = document.getElementById("myHeader");
        header.classList.remove("sticky");
        $('#DangNhapModal').modal('show');
    }
    else{
        modal.classList.add("display-flex")
       // body.classList.add("overflow-hidden")
        body.classList.add("disable-scrollbar")
        suggesstionForm.classList.remove("no-display");
    }
})

async function viewActByLocation(e){
    const count = +e.target.dataset.countAct;
    if(count!==0){
        const location = e.target.dataset.location;
        try{
            const response = await fetch(`/hoat-dong-theo-vi-tri?location=${location}`);
            if(!response.ok){
                throw new Error(`Lỗi HTTP! Trạng thái: ${response.status}`);
            }
            const actList = await response.json();
            if(actList.length > 0){
                modal.classList.add("display-flex");
                // body.classList.add("overflow-hidden");
                body.classList.add("disable-scrollbar")
                actDetail.classList.remove("no-display");
                actList.forEach(act => {
                    var startTime = new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(act.thoiGianBD))
                    var endTime = new Intl.DateTimeFormat("vi-VN",{ day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(act.thoiGianKT))
                    const div = document.createElement("div")
                    div.classList.add("fs-9", "display-flex")
                    div.innerHTML = `
                <div class="container px-2">
                    <div class="container d-flex align-items-center p-1 border_bottom-solid d-flex">
                    <img src="${act.anh}" class="float-start w-10 h-80" alt="" />
                        <div class="container flex-5">
                            <h3 class="green-color fs-5 p-0 mb-0">${act.maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6 p-0">${act.tenhd}</p>
                            <p class="mb-0 fs-7">${act.moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green px-1"></i>${act.diaDiem}</p>
                            <p class="fs-7 fst-italic my-1">
                                <i class="bi bi-calendar2 m-1 green-color"></i>${startTime + '  -  ' + endTime}</p>              
                        </div>
                    </div>
                </div>`
                    actByLocation.appendChild(div)
                })
            }
        }catch(error) {
            console.error(error);
        }
    }
}
for(btn of  btn_actDetail){
    btn.addEventListener("click",viewActByLocation)
}

// btn_Huy.addEventListener("click",(e)=>{
//     document.f.thonPhuong.value = "";
//     document.f.quanHuyen.value = "";
//     document.f.tinhThanhPho.value = "";
//     document.f.moTa.value = "";
//     document.f.chuDe.selectedIndex = 0;
// })

function organizeHere(e){
   try{
       const acc = +e.target.dataset.account;
       console.log(acc)
       console.log(e.target)
       if(!isNaN(acc)){
           const title = e.target.dataset.name;
           const location = e.target.dataset.location;
           $('#ThemHoatDongModal').modal('show');
           $('#txt_Location').val(location);
           $('#txt_Location').prop('disabled',true);
           $('#txt_NameTopic').val(title);
           $('#txt_NameTopic').prop('disabled',true);
       }
       else{
           const header = document.getElementById("myHeader");
           header.classList.remove("sticky");
           $('#DangNhapModal').modal('show');
           $('.btn-organize').each(function() {
               $(this).data("account", account.id);
           });
          // e.target.setAttribute("data-account", account.id);
       }
   }catch(error){
       console.error(error);
   }
   //  console.log("Outside if-else");
   //  const acc = e.target.getAttribute("data-account")
   //  console.log(e.target)
   //  console.log("Data-account value: " + acc);
   //
   //  if (acc !== null) {
   //      console.log("Inside if");
   //      // Các dòng code xử lý trong if
   //  } else {
   //      console.log("Inside else");
   //      // Các dòng code xử lý trong else
   //  }
}
//btn ToChucTaiDay
for(btn of btn_organizeHere){
    btn.addEventListener("click",organizeHere)
}