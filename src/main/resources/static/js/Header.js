window.onscroll = function() {stickyHeader()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;
//set thuộc tính sticky cho header
function stickyHeader() {
    var placeholder = document.getElementById("placeholder");

    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
        placeholder.style.display = "block";
    } else {
        header.classList.remove("sticky");
        placeholder.style.display = "none";
    }
}
document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;

    var navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(function (link) {
        var linkPage = link.getAttribute('data-page');
        if (linkPage && linkPage === currentPage) {
            link.style.color = 'var(--main-color)';
            link.style.backgroundColor = 'white';
        }
    });
});
// document.addEventListener('DOMContentLoaded', function () {
//     var buttons = document.querySelectorAll('.header__navbar .navbar-nav .nav-item a');
//
//     buttons.forEach(function (button) {
//         button.addEventListener('click', function () {
//             // Xoá lớp 'active' từ tất cả các nút
//             buttons.forEach(function (btn) {
//                 btn.classList.remove('active');
//             });
//             // Thêm lớp 'active' vào nút được nhấp
//             this.classList.add('active');
//         });
//     });
// });

//xóa input của trang login
function clearInput(inputId) {
    var inputElement = document.getElementById(inputId);
    if (inputElement) {
        inputElement.value = '';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formLogin').addEventListener('submit', function (e) {
        e.preventDefault();
        // Lấy giá trị từ form
        var username = $('#username').val();
        var password = $('#password').val();
        // Gọi AJAX đến endpoint xử lý đăng nhập
        $.ajax({
            type: 'POST',
            url: '/check-login',

            data: { 'tenDN': username, 'matKhau': password },
            success: function (data) {
                // console.log('data:', data);
                // var reloadPage = data.reloadPage;/* Đọc giá trị từ response hoặc từ nơi khác */;
                //
                if (data.reloadPage) {
                    // Thực hiện load lại trang
                    location.reload();
                    // $('#btn_userProfile').show();
                    // $('.modal-backdrop').remove();
                }


                if (!data.error) {
                    // Đăng nhập thành công
                    $('#DangNhapModal').modal('hide');
                    $('#btnLoginModal').hide();
                    $('#btn_userProfile').show();
                    $('.modal-backdrop').remove();

                } else {
                    // Đăng nhập thất bại, hiển thị thông báo lỗi
                    $('#error-message').text(data.error);
                    $('#DangNhapModal').modal('show');
                }
            },
            error: function (error) {
                console.error('Đã có lỗi xảy ra:', error);
            }
        });
    });
});


//
// $(document).ready(function() {
//     $('#DangNhapModal').on('hidden.bs.modal', function () {
//         // Xóa thuộc tính style của body
//         document.body.removeAttribute('style');
//     });
// });

//Sử dụng sự kiện hidden.bs.modal của Bootstrap để xử lý việc xóa các thuộc tính style khi modal được ẩn đi:
// $('#DangNhapModal').on('hidden.bs.modal', function () {
//     $('body').removeClass('modal-open');
//     $('.modal-backdrop').remove();
// });
// // Sự kiện khi modal hiện ra
// $('#modal-sign-up').on('shown.bs.modal', function () {
//     // Thêm các xử lý khi modal hiện ra
//     $('.modal-backdrop').remove();
//     // Nếu cần thêm modal-backdrop, bạn có thể thêm dòng sau:
//     // $('body').append('<div class="modal-backdrop fade show"></div>');
// });
var backdropCount = 0;

$('#modal-sign-up').on('show.bs.modal', function () {
    // Tăng biến đếm mỗi khi modal sắp hiển thị
    backdropCount++;

    // Nếu có nhiều hơn một backdrop, xóa đi các backdrop thừa
    if (backdropCount > 1) {
        $('.modal-backdrop').not(':last').remove();
    }
});
$('#modal-sign-up').on('shown.bs.modal', function () {
    var header = document.getElementById("myHeader");
    header.classList.remove("sticky");
});

$('#modal-sign-up').on('hidden.bs.modal', function () {
    // Giảm biến đếm mỗi khi modal được đóng
    backdropCount--;
    // var header = document.getElementById("myHeader");
    // header.classList.add("sticky");
});
$('#notifyOTPModal').on('shown.bs.modal', function () {
    var header = document.getElementById("myHeader");
    header.classList.remove("sticky");
});
$('#notifyOTPModal').on('show.bs.modal', function () {
    // var header = document.getElementById("myHeader");
    // header.classList.add("sticky");
    $('.modal-backdrop').remove();
    // Tăng biến đếm mỗi khi modal sắp hiển thị
    // backdropCount++;
    // // Nếu có nhiều hơn một backdrop, xóa đi các backdrop thừa
    // if (backdropCount > 1) {
    //     $('.modal-backdrop').not(':last').remove();
    // }

});
$('#notifyOTPModal').on('hidden.bs.modal', function () {
    backdropCount--;
    //mỗi khi modal được đóng
    // var header = document.getElementById("myHeader");
    // header.classList.add("sticky");
});
// =================================================================
// $('.modal').on('show.bs.modal', function () {
//
//     // Tăng biến đếm mỗi khi modal sắp hiển thị
//     backdropCount++;
//
//     // Nếu có nhiều hơn một backdrop, xóa đi các backdrop thừa
//     if (backdropCount > 1) {
//         $('.modal-backdrop').not(':last').remove();
//     }
// });
// $('.modal').on('hidden.bs.modal', function () {
//     backdropCount--;
//     //mỗi khi modal được đóng
//     // var header = document.getElementById("myHeader");
//     // header.classList.add("sticky");
// });
// ===============================================================
//
$('#DangNhapModal').on('hidden.bs.modal', function () {
    //mỗi khi modal được đóng
    // var header = document.getElementById("myHeader");
    // header.classList.add("sticky");
});

$('#DangNhapModal').on('show.bs.modal', function () {
    console.log("Có chạy mà =======");
    // Đặt độ trễ để đảm bảo việc xóa class "sticky" được thực hiện sau khi modal hiển thị
    setTimeout(function() {
        var header = document.getElementById("myHeader");
        header.classList.remove("sticky");
    }, 100);
});

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('checkShowPass').addEventListener('change', function (event) {
        var password = document.getElementById("password");
        var checkBox = document.getElementById("checkShowPass");
        password.type = checkBox.checked ? "text" : "password";
    });
});
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('checkShowTwoPass').addEventListener('change', function (event) {
        var oldpassword = document.getElementById("old-password");
        var newpassword = document.getElementById("new-password");
        var checkBox = document.getElementById("checkShowTwoPass");
        oldpassword.type = checkBox.checked ? "text" : "password";
        newpassword.type = checkBox.checked ? "text" : "password";
    });
});
//load lại trang
// Trong mã JavaScript
$(document).ready(function() {
    $('#confirmLogout').click(function(e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: '/logout',
            success: function (data) {
                // Xử lý JSON response
                if (data.success) {
                    // Chuyển hướng đến trang đăng nhập hoặc trang chính
                    if(location.pathname==="/trang-ca-nhan"){
                        location.href="/trang-chu";
                    }
                    else location.reload();

                }
            }
        });
    });
});
// custom.js


var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
});


$('#btnLogout').click(function() {
    $('#logoutModal').modal('show');
});
$('#confirmLogout').click(function() {
    $('#logoutModal').modal('hide');
});
$(document).ready(function() {
    $('.closeModalLogout').on('click', function() {
        $('#logoutModal').modal('hide');
    });
});
$(document).ready(function() {
    $('.btn-close-register').on('click', function() {
        $('#btn-sign-up').modal('hide');
        $('.modal-backdrop').remove();
        location.reload();
    });
});
// $('#btn-sign-up').click(function() {
//     $('#modal-sign-up').modal('show');
// });
// $(document).ready(function() {
//     $('#btn-close-register').on('click', function() {
//         $('#modal-sign-up').modal('hide');
//     });
// });
// $(document).ready(function() {
//     $('.closeModalLogout').on('click', function() {
//         $('#logoutModal').modal('hide');
//     });
// });


// main.js

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formRegisterAccount').addEventListener('submit', function (e) {
        e.preventDefault();
        // Gọi AJAX đến endpoint xử lý đăng nhập
        $.ajax({
            type: 'POST',
            url: '/dang-ky-tai-khoan',
            data: new FormData(this),
            contentType: false,
            processData: false,
            success: function (response) {
                if (response.success) {
                    var header = document.getElementById("myHeader");
                    header.classList.remove("sticky");
                    // location.reload();

                    $('#modal-sign-up').modal('hide');
                    $('.modal-backdrop').remove();
                    // location.reload();
                    alert("Đăng ký thành công!");
                    // $('#DangNhapModal').modal('show');
                    // $('.modal-login').css('display', 'block');
                    // $('.modal-backdrop').remove();
                } else {
                    // Hiển thị thông báo lỗi
                    alert(response.message);
                }
            },
            error: function (error) {
                console.error('Đã có lỗi xảy ra:', error);
            }
        });
    });
});
// -----gửi OTP----
var otp_email = "";
var email_receiveOTP = "";
var otp_input = "";
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('btn-send-otp').addEventListener('click', function (e) {
        e.preventDefault();
        // Lấy giá trị từ form
        email_receiveOTP = $('#emailReceiveOTP').val().toString();
        if(!email_receiveOTP){

        }
        else{
            console.log("CÓ CHạy");
            // Lấy giá trị từ form
            // var email = $('#emailReceiveOTP').val().toString();
            // Gọi AJAX đến endpoint xử lý đăng nhập
            console.log("CÓ Chạy email:" + email_receiveOTP);
            $.ajax({
                type: 'POST',
                url: '/send-otp',

                data: { 'email': email_receiveOTP},
                success: function (data) {
                    console.log('data:', data);
                    if (data.success) {
                        otp_email = data.otp;
                        // email = data.email;
                        //thành công
                        console.log("CÓ Chạy tiếp:" + otp_email + "----" + email_receiveOTP);

                    } else {
                        //thất bại, hiển thị thông báo lỗi
                        // $('#error-notify').text(data.error);
                        // $('#ChangePasswordModal').modal('show');
                    }
                },
                error: function (error) {
                    console.error('Đã có lỗi xảy ra:', error);
                }
            });
        }
    });
});
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('btn-confirm-otp').addEventListener('click', function (e) {
        e.preventDefault();
        otp_input = $('#inputOTP').val();
        $.ajax({
            type: 'POST',
            url: '/confirm-otp',
            data: {"otp_input": otp_input, "otp_email": otp_email, "email": email_receiveOTP},
            success: function (data) {
                console.log('ok');
                if (!data.error) {
                    //thành công
                    $('#ForgetPasswordModal').modal('hide');
                    $('#confirmOTP').text(data.success);
                    $('#notifyOTPModal').modal('show');
                } else {
                    //thất bại, hiển thị thông báo lỗi
                    $('#ForgetPasswordModal').modal('hide');
                    $('#confirmOTP').text(data.error);
                    // $('#confirmOTP').css('color', 'red');
                    // $('#ChangePasswordModal').modal('show');
                    $('#notifyOTPModal').modal('show');
                }

            },
            error: function (error1) {
                console.error('Đã có lỗi xảy ra:', error1);
            }
        });
    });
});
// -----thay đổi mật khẩu----
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('btn-change-password').addEventListener('click', function (e) {
        console.log("--hiện modal thay đổi mật khẩu--");
        $('#ChangePasswordModal').modal('show');
    });
});
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formChangePassword').addEventListener('submit', function (e) {
        e.preventDefault();
        console.log("CÓ CHạy");
        // Lấy giá trị từ form
        var username = $('#txt_username').val();
        var oldpassword = $('#old-password').val();
        var newpassword = $('#new-password').val();
        // Gọi AJAX đến endpoint xử lý đăng nhập
        $.ajax({
            type: 'POST',
            url: '/change-password',

            data: { 'tenDN': username, 'matKhau': oldpassword, 'matKhauMoi': newpassword },
            success: function (data) {
                console.log('data:', data);
                if (!data.error) {
                    //thành công
                    $('#ChangePasswordModal').modal('hide');
                    $('#notifyChangePassModal').modal('show');

                } else {
                    //thất bại, hiển thị thông báo lỗi
                    $('#error-notify').text(data.error);
                    $('#ChangePasswordModal').modal('show');
                }
            },
            error: function (error) {
                console.error('Đã có lỗi xảy ra:', error);
            }
        });
    });
});
//===============================modal chung xem đánh giá của hoạt động=============================
async function handleViewDetail(e){
    e.preventDefault(); // Ngăn chặn sự kiện mặc định
    try{
        const IdAct =+e.target.dataset.value;
        const response =await fetch(`/hoat-dong/xem-chi-tiet?IdAct=${IdAct}`);
        if(!response.ok){
            throw new Error(`Lỗi HTTP. Trạng thái: ${response.status}`)
        }
        const responseData = await response.json();
        const imagesList = responseData.imagesList;
        // console.log(responseData);
        modal.classList.add('display-flex')
        body.classList.add('overflow-hidden')
        document.querySelector(".modal-nameAct").innerText = responseData.activity.tenhd;
        document.querySelector(".modal-Description").innerText = responseData.activity.moTa;
        document.querySelector(".avatar").src = responseData.registerInfor.maTK.anhDaiDien;
        document.querySelector(".name-host-act").innerText = responseData.registerInfor.maTK.tenDN;
        const date = new Date(responseData.registerInfor.thoiGianDK);
        const datePart = date.toLocaleDateString("vi-VN", {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
        });

        const timePart = date.toLocaleTimeString("vi-VN", {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        });
        const registerTime = `${datePart} ${timePart}`;
        document.querySelector(".time-post-act").innerText = registerTime;
        image_activity.innerHTML = "";
        let count = 0;
        imagesList.forEach(image => {
            if(count === 3) return;
            image_activity.innerHTML += `<img src="${image.anh}" alt="anhHoatDong" class="height-10rem w-35 p-1">`;
            count++;
        });

        document.querySelector(".modal-number-evaluation").innerText = responseData.numberEvaluation + " lượt đánh giá"
        //Generate comment
        const evaluationOfAct = responseData.evaluationOfAct;
        const evaluationContainer = document.querySelector(".modal-comment");
        if (evaluationOfAct.length > 0) {
            const criteriaDiv = document.createElement("div");
            criteriaDiv.classList.add("container", "d-flex", "justify-content-sm-end","p-2", "criteria-evaluation")
            criteriaDiv.innerHTML=
                `    <h4 class="fs-9 green-color fst-italic me-4 text-center">Hoạt động<br>hữu ích</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>thường xuyên</h4>
                    <h4 class="fs-9 green-color fst-italic me-4 text-center">Cần tổ chức<br>rộng rãi</h4>
                `
            evaluationContainer.appendChild(criteriaDiv)
            evaluationOfAct.forEach(eva => {
                const newEvaluationDiv = document.createElement("div");
                newEvaluationDiv.classList.add("container", "d-flex", "mb-3", "comment-member");
                newEvaluationDiv.innerHTML = `
            <!-- Text comment -->
            <div class="container radius-1 bg-green">
                <div class="container p-1">
                    <img src="${eva.maTK.anhDaiDien}" class="w-2_5 h-2_5 p-1 radius-1_8 modal-avatar" alt="">
                    <h7 class="green-color fs-11 fst-italic fw-medium">${eva.maTK.tenDN}</h7>
                </div>
                <p class="fs-10 fst-italic m-0 px-3">${eva.binhLuan}</p>
                <p class="green_light-color fst-italic fs-10 my-1 mx-3">${new Intl.DateTimeFormat("vi-VN", { day: '2-digit', month: '2-digit', year: 'numeric' }).format(new Date(eva.thoiGianBL))}</p>
            </div>
            <!-- Check comment -->
            <div class="container d-flex align-items-center justify-content-center">
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eva.tieuChi1}" ${eva.tieuChi1?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-2" value="${eva.tieuChi2}" ${eva.tieuChi2?'checked':''}>
                     <input type="checkbox" disabled class="flex-1 p-4 fs-3" value="${eva.tieuChi3}" ${eva.tieuChi3?'checked':''}>
            </div>
        `
                evaluationContainer.appendChild(newEvaluationDiv);
            });
        }
    }catch(error){
        console.error(error)
    }
}
// Close view detail
function closeViewDetail(e) {
    //     Delete comment every activity
    const modal_comment = document.querySelector('.modal-comment');
    modal_comment.innerHTML='';
    modal.classList.remove('display-flex')
    body.classList.remove('overflow-hidden');
}

<!--=========================================== alert chung ===============================================-->
function showAlert(message) {
// Tạo phần tử div cho cảnh báo
    const alertDiv = document.createElement("div");
    alertDiv.classList.add("custom-alert");

// Thêm icon (đổi thành đường dẫn hình ảnh thực của bạn)
    alertDiv.innerHTML = '<i class="bi bi-exclamation-square"></i>';

// Thêm thông điệp cảnh báo
    alertDiv.innerHTML += `<p>${message}</p>`;

// Thêm phần tử vào body
    document.body.appendChild(alertDiv);

// Đợi một khoảng thời gian (ví dụ: 3 giây) và sau đó xóa cảnh báo
    setTimeout(() => {
        document.body.removeChild(alertDiv);
    }, 3000);  // 3000 milliseconds = 3 seconds
}

//button read ở thông báo
const notice_item = document.querySelectorAll(".notification-item")
$(document).ready(function (){
    $("#btn-read").click(async function (e){
        e.stopPropagation(); //Ngăn chặn sự kiện lan toả lên menu
        var idList = [];
        for(notice of notice_item){
            if(notice.dataset.status==='false'){
                notice.classList.remove("bg-grey")
                const id=+notice.dataset.id;
                idList.push(id);
            }
        }
        console.log(idList);
        //Cập nhật trạng thái thông báo
        if(idList.length > 0) {
            const response = await fetch(`cap-nhat-trang-thai-doc-thong-bao?idList=${idList}`,{
                method: 'POST'
            });
            if (!response.ok) {
                console.log("Lỗi cập nhật trạng thái thông báo. Trạng thái " + response.status);
                return;
            }
        }
    })
})

function handleItemClick(event, item) {
    event.stopPropagation();
}