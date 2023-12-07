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
$(document).ready(function() {
    $('#DangNhapModal').on('hidden.bs.modal', function () {
        // Xóa thuộc tính style của body
        document.body.removeAttribute('style');
    });
});

//Sử dụng sự kiện hidden.bs.modal của Bootstrap để xử lý việc xóa các thuộc tính style khi modal được ẩn đi:
$('#DangNhapModal').on('hidden.bs.modal', function () {
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
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
// document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('btnLogout').addEventListener('click', function (e) {
    $.ajax({
        type: 'GET',
        url: '/logout',
        success: function (data) {
            // Xử lý JSON response
            if (data.success) {
                // Chuyển hướng đến trang đăng nhập hoặc trang chính
                location.reload();
            }
        }
    });
    });
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

