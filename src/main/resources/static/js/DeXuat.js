// Suggestions
let btn_close_sug = document.querySelector(".btn-close-suggest");
let btn_add_sug = document.querySelector(".btn-add-suggest");
let body = document.querySelector("body");
let modal = document.querySelector(".modal_")
let suggestionDetail = document.querySelector(".suggestion-detail");
let suggesstionForm = document.querySelector(".was-validated");
let btn_suggestionDetail = document.querySelectorAll(".btn-suggestion-detail");
let btn_close_detail = document.querySelector(".btn-close-detail");
btn_close_sug.addEventListener("click", () => {
    modal.classList.remove("display-flex")
    body.classList.remove("overflow-hidden")
    suggesstionForm.classList.add("no-display");
})

btn_close_detail.addEventListener("click", () => {
    modal.classList.remove("display-flex")
    body.classList.remove("overflow-hidden")
    suggestionDetail.classList.add("no-display");
})

btn_add_sug.addEventListener("click", () => {
    modal.classList.add("display-flex")
    body.classList.add("overflow-hidden")
    suggesstionForm.classList.remove("no-display");
})

function convertToSlug(str) {
    return str
        .toLowerCase()
        .normalize('NFKD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/\s+/g, '');
}

for(btn of btn_suggestionDetail){
    //Get data
    btn.addEventListener("click",async (e)=>{
        const title = e.target.dataset.value;
        try{
            const encode = convertToSlug(title);
            const response = await fetch(`/de-xuat/${encode}`);
            // console.log(encode);
            if(!response.ok){
                throw new Error(`Lỗi HTTP! Trạng thái: ${response.status}`);
            }
            const suggestionTitle = await response.json();
            modal.classList.add("display-flex");
            body.classList.add("overflow-hidden");
            suggestionDetail.classList.remove("no-display");
        }catch(error) {
            console.error(error);
        }
    })
}

