// $(document).ready(function() {
//   $('#btnDangNhap').on('click', function() {
//     $('#DangNhapModal').modal('show');
//   });
// });

document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("imageInputAdd").addEventListener("change", function (event) {
    previewImage(event);
  });

  function previewImage(event) {
    var selectedFile = event.target.files[0];
    var imagePreview = document.getElementById("userImage");

    if (selectedFile) {
      var reader = new FileReader();
      reader.onload = function (e) {
        imagePreview.src = e.target.result;
      };
      reader.readAsDataURL(selectedFile);
    }
  }
});
   

(function () {
      'use strict'
      var forms = document.querySelectorAll('.needs-validation')

      Array.prototype.slice.call(forms)
        .forEach(function (form) {
          form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
              event.preventDefault()
              event.stopPropagation()
            }

            form.classList.add('was-validated')
          }, false)
        })
    })()
    function resetFormFields() {
      document.getElementById("myForm").reset();

      // Hide all error messages
      // var formElements = document.getElementById("myForm").elements;
      // for (var i = 0; i < formElements.length; i++) {
      //   formElements[i].setCustomValidity('');
      // }
      document.getElementById("userImage").src = "../assets/images/default.webp";
}
// Thêm hoạt động
// document.addEventListener('DOMContentLoaded', function() {
//   document.getElementById('formLogin').addEventListener('submit', function (e) {
//     console.log('handleLogin called with loginSuccess:');
//     e.preventDefault();
//
//     // Lấy giá trị từ form
//     var tenChuDe = $('#txt_NameTopic').val();
//     var tenHD = $('#txt_NameActi').val();
//
//     // Gọi AJAX đến endpoint xử lý đăng nhập
//     $.ajax({
//       type: 'POST',
//       url: '/check-login',
//
//       data: { 'tenDN': username, 'matKhau': password },
//       success: function (data) {
//         console.log('data:', data);
//         if (!data.error) {
//           // Đăng nhập thành công
//           $('#DangNhapModal').modal('hide');
//           $('#btnLoginModal').hide();
//           $('#btn_userProfile').show();
//           $('body').removeClass('modal-open');
//           $('.modal-backdrop').remove();
//         } else {
//           // Đăng nhập thất bại, hiển thị thông báo lỗi
//           $('#error-message').text(data.error);
//           $('#DangNhapModal').modal('show');
//         }
//       },
//       error: function (error) {
//         console.error('Đã có lỗi xảy ra:', error);
//       }
//     });
//   });
// });
// function handleLogin(loginSuccess) {
//   // Xử lý đăng nhập ở đây. Nếu đăng nhập thành công, ẩn modal và trả về true.
//   // loginSuccess = true;
//   console.log('handleLogin called with loginSuccess:', loginSuccess);
//
//   // return false; // Ngăn chặn gửi form nếu đăng nhập không thành công
// }
// document.addEventListener('DOMContentLoaded', function() {
//   document.getElementById('checkShowPass').addEventListener('click', function (event) {
//     var password = document.getElementById("password");
//     var checkBox = document.getElementById("checkShowPass");
//     password.type = checkBox.checked ? "text" : "password";
//   });
// });
// function userLoggedIn() {
//   // Thay đổi giao diện header khi người dùng đã đăng nhập
//   document.getElementById('btnLoginModal').style.display = 'none';
//   document.getElementById('btn_userProfile').style.display = 'inline-block';
//   // Thêm các thay đổi khác nếu cần
// }
