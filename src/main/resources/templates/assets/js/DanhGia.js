// Evaluate
const modal = document.querySelector(".modal_")
const body = document.querySelector("body");
const view_comment = document.querySelector(".view-comment");
const evaluation = document.querySelector(".evaluation")
const write_comment = document.querySelector(".write-comment")
const btn_evaluate_member = document.getElementsByClassName("btn_eva_member");
const btn_evaluate = document.getElementsByClassName("btn-evaluate")
const edit_content = document.querySelector(".content-evaluate");
const view_organized = document.querySelector(".view-organized");
for (var btn of btn_evaluate) {
    btn.addEventListener("click", function (e) {
        modal.classList.add('display-flex')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')
        edit_content.classList.add('max_height-28_5rem')
        write_comment.classList.remove('no-display')
    })
}

for (var btn of btn_evaluate_member) {
    btn.addEventListener("click", function (e) {
        modal.classList.add('display-flex')
        edit_content.classList.remove('max_height-28_5rem')
        view_comment.classList.remove('no-display')
        body.classList.add('overflow-hidden')
        view_organized.classList.remove('no-display')
    })
}

// Close form evaluation
const btn_close = document.getElementsByClassName("btn-close-detail")
for (var btn of btn_close) {
    btn.addEventListener("click", function (e) {
        modal.classList.remove('display-flex')
        body.classList.remove('overflow-hidden')
        view_comment.classList.add('no-display')
        write_comment.classList.add('no-display')
        evaluation.classList.add('no-display')
        edit_content.classList.remove('max_height-28_5rem')
        view_organized.classList.add('no-display')
    });
}


//Evalutate members
const member_evaluate = document.getElementsByClassName("member-evaluate")
var btn_eva_exit = document.getElementsByClassName("btn-exit")

// for (var member of member_evaluate) {
//     member.addEventListener("click", function(e) {
//         const target = e.target;
//         const childForm = target.closest(".member-evaluate").querySelector(".form-evaluate");
//         childForm.classList.add("display-block");
//     });
for (var member of member_evaluate) {
    member.addEventListener("click", function(e) {
        const target = e.target;
        const container = target.closest(".member-evaluate");

        // Lặp qua tất cả các .form-evaluate và xóa class "display-block"
        var allForms = document.querySelectorAll(".form-evaluate");
        for (var form of allForms) {
            form.classList.remove("display-block");
        }

        // Thêm class "display-block" cho .form-evaluate của thành viên được nhấn
        const childForm = container.querySelector(".form-evaluate");
        childForm.classList.add("display-block");
    });
}
//}

var btn_eva_exit = document.getElementsByClassName("btn-exit");
for (var btn of btn_eva_exit) {
    btn.addEventListener("click", function (e) {
        const target = e.target;
        const parent = target.closest(".form-evaluate");

        if (parent) {
            parent.classList.remove("display-block");
        }
    })
}


// save

//View evaluation
const btn_view_evaluation = document.querySelector(".btn-view-evaluation");
const btn_evaluate_mb = document.querySelector(".btn-evaluate-member");
btn_view_evaluation.addEventListener("click", function (e) {
     btn_view_evaluation.classList.add("green")
      btn_evaluate_mb.classList.remove("green")
    evaluation.classList.add("no-display")
    view_comment.classList.remove('no-display')
})  
btn_evaluate_mb.addEventListener("click", function (e) {
    btn_view_evaluation.classList.remove("green")
    btn_evaluate_mb.classList.add("green")
    view_comment.classList.add('no-display')
    evaluation.classList.remove('no-display')
})

//Evaluate star
var btn_star = document.getElementsByClassName("btn-star");
for (var btn of btn_star) {
    btn.addEventListener("click", (e) => {
        for (var star of btn_star) {
            star.classList.remove("yellow-color")
        }
        e.target.classList.add("yellow-color");
        var previousSibling = e.target.previousElementSibling;
        while (previousSibling) {
            previousSibling.classList.add("yellow-color");
            previousSibling = previousSibling.previousElementSibling;
        }
    });
}