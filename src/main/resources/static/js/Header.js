window.onscroll = function() {stickyHeader()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;
//set thuộc tính sticky cho header
function stickyHeader() {
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}
// //sự kiện xử lý đăng nhập
// document.addEventListener('DOMContentLoaded', function() {
//     // Sử dụng Ajax để lấy dữ liệu từ máy chủ
//     fetch('/check-login', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded',
//         },
//         body: new URLSearchParams({
//             'tenDN': 'your_username_value',
//             'matKhau': 'your_password_value',
//         }),
//     })
//     .then(response => response.json())
//     .then(data => {
//         var loginSuccess = data;
//         // Tiếp tục với xử lý sự kiện click ở đây
//         document.getElementById('btnLogin').addEventListener('click', function (event) {
//             if (loginSuccess) {
//                 $('#DangNhapModal').modal('hide');
//                 document.getElementById('btnLoginModal').style.display = 'none';
//                 document.getElementById('btn_userProfile').style.display = 'inline-block';
//                 return true;
//             } else {
//                 $('#DangNhapModal').modal('show');
//                 return false;
//             }
//         });
//     });
// });
// $(document).ready(function () {
//     $('#formLogin').submit(function (e) {
//         // console.log('handleLogin called with loginSuccess:', loginSuccess);
//         e.preventDefault();
//
//         // Lấy giá trị từ form
//         var username = $('#username').val();
//         var password = $('#password').val();
//
//         // Gọi AJAX đến endpoint xử lý đăng nhập
//         $.ajax({
//             type: 'POST',
//             url: '/check-login',
//             data: { 'tenDN': username, 'matKhau': password },
//             success: function (data) {
//                 if (!data.error) {
//                     // Đăng nhập thành công
//                     $('#DangNhapModal').modal('hide');
//                     $('#btnLoginModal').hide();
//                     $('#btn_userProfile').show();
//                 } else {
//                     // Đăng nhập thất bại, hiển thị thông báo lỗi
//                     $('#error-message').text(data.message);
//                     $('#DangNhapModal').modal('show');
//                 }
//             },
//             error: function (error) {
//                 console.error('Đã có lỗi xảy ra:', error);
//             }
//         });
//     });
// });

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formLogin').addEventListener('submit', function (e) {
        console.log('handleLogin called with loginSuccess:');
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
                console.log('data:', data);
                if (!data.error) {
                    // Đăng nhập thành công
                    $('#DangNhapModal').modal('hide');
                    $('#btnLoginModal').hide();
                    $('#btn_userProfile').show();
                    $('body').removeClass('modal-open');
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
function handleLogin(loginSuccess) {
    // Xử lý đăng nhập ở đây. Nếu đăng nhập thành công, ẩn modal và trả về true.
    // loginSuccess = true;
    console.log('handleLogin called with loginSuccess:', loginSuccess);

    // return false; // Ngăn chặn gửi form nếu đăng nhập không thành công
}
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('checkShowPass').addEventListener('click', function (event) {
        var password = document.getElementById("password");
        var checkBox = document.getElementById("checkShowPass");
        password.type = checkBox.checked ? "text" : "password";
    });
});
function userLoggedIn() {
    // Thay đổi giao diện header khi người dùng đã đăng nhập
    document.getElementById('btnLoginModal').style.display = 'none';
    document.getElementById('btn_userProfile').style.display = 'inline-block';
    // Thêm các thay đổi khác nếu cần
}