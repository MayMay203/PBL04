// View detail of activity
var body = document.querySelector("body");
var view_link = document.getElementsByClassName("view-detail");
var modal = document.querySelector(".modal_")
for( var view of view_link){
    view.addEventListener("click", function (e) {
            modal.classList.add('display-flex')
            body.classList.add('overflow-hidden')
        }
    )
};

// Close view detail
var btn_close = document.getElementsByClassName("btn-close-detail")
for (var btn of btn_close) {
    btn.addEventListener("click", function (e) {
        modal.classList.remove('display-flex')
        body.classList.remove('overflow-hidden')
        view_comment.classList.add('no-display')
        write_comment.classList.add('no-display')
        evaluation.classList.add('no-display')
        edit_content.classList.remove('max_height-29_5rem')
        view_organized.classList.add('no-display')
    });
}



