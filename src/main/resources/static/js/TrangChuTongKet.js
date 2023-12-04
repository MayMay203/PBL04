var search_input = document.querySelector(".search-input")
var search_infor = document.querySelector(".search-info")
search_input.addEventListener("focus", function (e) {
    search_infor.classList.add('display-block');
});
search_input.addEventListener("blur", function (e) {
    search_infor.classList.remove('display-block');
});



