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
    var header = document.getElementById("myHeader");
    header.classList.add("sticky");
});
$('.modal').on('show.bs.modal', function () {

    // Tăng biến đếm mỗi khi modal sắp hiển thị
    backdropCount++;

    // Nếu có nhiều hơn một backdrop, xóa đi các backdrop thừa
    if (backdropCount > 1) {
        $('.modal-backdrop').not(':last').remove();
    }
});
//
$('#DangNhapModal').on('hidden.bs.modal', function () {
    //mỗi khi modal được đóng
    var header = document.getElementById("myHeader");
    header.classList.add("sticky");
});
$('.modal').on('hidden.bs.modal', function () {
    //mỗi khi modal được đóng
    var header = document.getElementById("myHeader");
    header.classList.add("sticky");
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




// document.addEventListener('DOMContentLoaded', function () {
//     // Gọi AJAX đến endpoint xử lý đăng nhập
//     $.ajax({
//         type: 'POST',
//         url: '/check-login',
//         dataType: 'json',
//         data: { 'tenDN': 'your_username', 'matKhau': 'your_password' },  // Thay đổi thành giá trị thực tế
//         success: function (data) {
//             var isLogin = data.loginState;
//             if (isLogin) {
//                 if (data.reloadPage) {
//                     // Load lại trang và ẩn modal đăng nhập, hiển thị nút btn_userProfile
//                     // location.reload();
//                     $('#btnLoginModal').hide();
//                     $('#btn_userProfile').show();
//                 } else {
//                     // Đăng nhập thành công, ẩn modal đăng nhập, hiển thị nút btn_userProfile
//                     $('#btnLoginModal').show();
//                     $('#btn_userProfile').hide();
//                 }
//             } else {
//                 // ... (xử lý đăng nhập không thành công)
//             }
//         },
//         error: function (xhr, status, error) {
//             console.error('Mã trạng thái:', xhr.status);
//             console.error('Thông báo lỗi:', xhr.responseText);
//             console.error('Đã có lỗi xảy ra:', status, error);
//         }
//     });
// });
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