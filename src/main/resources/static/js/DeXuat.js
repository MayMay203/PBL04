// Suggestions
var btn_close_sug = document.querySelector(".btn-close-suggest");
var btn_add_sug = document.querySelector(".btn-add-suggest");
var modal = document.querySelector(".modal_")
btn_close_sug.addEventListener("click", () => {
    modal.classList.remove("display-flex")
    body.classList.remove('overflow-hidden')
})
btn_add_sug.addEventListener("click", () => {
    modal.classList.add("display-flex")
    body.classList.add("overflow-hidden")
})