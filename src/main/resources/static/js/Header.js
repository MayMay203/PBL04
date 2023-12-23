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
                    if(location.pathname=="/trang-ca-nhan"){
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
