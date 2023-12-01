// Suggestions
let btn_close_sug = document.querySelector(".btn-close-suggest");
let btn_add_sug = document.querySelector(".btn-add-suggest");
let modal = document.querySelector(".modal_")
btn_close_sug.addEventListener("click", () => {
    modal.classList.remove("display-flex")
    body.classList.remove('overflow-hidden')
})
btn_add_sug.addEventListener("click", () => {
    modal.classList.add("display-flex")
    body.classList.add("overflow-hidden")
})