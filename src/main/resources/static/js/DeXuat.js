// Suggestions
const btn_close_sug = document.querySelector(".btn-close-suggest");
const btn_add_sug = document.querySelector(".btn-add-suggest");
const body = document.querySelector("body");
const modal = document.querySelector(".modal_")
const suggestionDetail = document.querySelector(".suggestion-detail");
const suggesstionForm = document.querySelector(".suggestion-form");
const suggestionByTitle = document.querySelector(".suggestion-by-title");
const btn_suggestionDetail = document.querySelectorAll(".btn-suggestion-detail");
const btn_close_detail = document.querySelector(".btn-close-detail");
btn_close_sug.addEventListener("click", () => {
    modal.classList.remove("display-flex")
    body.classList.remove("overflow-hidden")
    suggesstionForm.classList.add("no-display");
})

btn_close_detail.addEventListener("click", () => {
    suggestionByTitle.innerHTML = "";
    modal.classList.remove("display-flex")
    body.classList.remove("overflow-hidden")
    suggestionDetail.classList.add("no-display");
})

btn_add_sug.addEventListener("click", () => {
    modal.classList.add("display-flex")
    body.classList.add("overflow-hidden")
    suggesstionForm.classList.remove("no-display");
})

// function convertToSlug(str) {
//     return str
//         .toLowerCase()
//         .normalize('NFKD')
//         .replace(/[\u0300-\u036f]/g, '')
//         .replace(/\s+/g, '-');
// }

for(btn of btn_suggestionDetail){
    //Get data
    btn.addEventListener("click",async (e)=>{
        // const title = e.target.dataset.value.trim();
            const IdTitle = e.target.dataset.value;
        try{
            // const encode = convertToSlug(title);
            // console.log(encode);
            const response = await fetch(`/de-xuat/${IdTitle}`);
            if(!response.ok){
                throw new Error(`Lỗi HTTP! Trạng thái: ${response.status}`);
            }
            const titleSuggestionList = await response.json();
            // console.log(suggestionTitle)
            if(titleSuggestionList.length > 0){
                modal.classList.add("display-flex");
                body.classList.add("overflow-hidden");
                suggestionDetail.classList.remove("no-display");
                titleSuggestionList.forEach(sugg => {
                    const div = document.createElement("div")
                    div.classList.add("fs-9", "display-flex")
                    div.innerHTML = `
                <div class="container px-5">
                    <div class="container d-flex align-items-center p-3 border_bottom-solid">
                        <div class="container flex-5">
                            <h3 class="green-color fs-4">${sugg.maChuDe.tenChuDe}</h3>
                            <p class="mb-0 fs-6">${sugg.moTa}</p>
                            <p class="fs-7 fst-italic my-1"><i class="bi bi-geo-alt-fill green px-1"></i>${sugg.viTri}</p>
                        </div>
                    </div>
                </div>`
                    suggestionByTitle.appendChild(div)
                })
            }
        }catch(error) {
            console.error(error);
        }
    })
}